package com.example.wineshop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Configuration
public class CSVImporter {
    boolean loadDatabase = true;
    private static final Logger log = LoggerFactory.getLogger(CSVImporter.class);
    List<String> lines = new ArrayList<>();
    List<Wine> wineList = new ArrayList<>();
    List<Winery> wineryList = new ArrayList<>();
    List<Type> typeList = new ArrayList<>();
    List<Region> regionList = new ArrayList<>();

    @Bean
    CommandLineRunner initDatabase(
            RegionRepository regionRepository,
            TypeRepository typeRepository,
            WineRepository wineRepository,
            WineryRepository wineryRepository
    ) {
        return args -> {
            if (loadDatabase) {
                getInList();
                lineToObjects();
                // TODO: Guardar en Base de Datos
            }
        };
    }

    private void getInList() {
        String fileName = "wines_SPA.csv";
        
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(lines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void lineToObjects() {
        // 0 winery, 1 wine, 2 year, 3 rating, 4 num_reviews, 5 country, 6 region, 7 price, 8 type, 9 body, 10 acidity
        for (String line :
                lines) {
            String[] array = line.split(",");
            for (int i = 0; i < array.length; i++) {
                array[i] = cleanString(array[i]);
            }
            Wine newWine = new Wine(
                    array[1],
                    Integer.parseInt(array[2]),
                    Float.parseFloat(array[3]),
                    Integer.parseInt(array[4]),
                    Float.parseFloat(array[7]),
                    Integer.parseInt(array[9]),
                    Integer.parseInt(array[10]));
            Winery newWinery = new Winery(array[0]);
            Region newRegion = new Region(array[6], array[5]);
            Type newType = new Type(array[8]);
            wineList.add(newWine);
            if (!wineryList.contains(newWinery))
                wineryList.add(newWinery);
            if (!regionList.contains(newRegion))
                regionList.add(newRegion);
            if (!typeList.contains(newType))
                typeList.add(newType);
        }
    }

    private String cleanString(String s) {
        log.warn(s);
        String newer = s.replace("N.V.", "0");
        log.info("Remove NV: "+newer);
        newer = newer.replace("\"\"", "0");
        log.info("Remove double quotes: "+newer);
        newer = newer.replace("NA", "0");
        log.info("Remove NA: "+newer);
        newer = newer.replace("\"", "");
        log.info("Remove quotes: "+newer);
        return newer;
    }
}