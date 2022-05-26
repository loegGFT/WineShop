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
    // DO NOT TOUCH
    /**
     * Esta variable controla si se importa a la base de datos o no de un archivo CSV
     */
    boolean loadDatabase = false;
    // DO NOT TOUCH

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
                log.info("Loading database...");
                getInList();
                log.info("File parsed...");
                lineToObjects();
                log.info("Object lists constructed...");
                saveInDB(regionRepository,
                        typeRepository,
                        wineRepository,
                        wineryRepository);
                log.info("Saved in DB...");
            } else {
                log.info("Database loading skipped.");
            }
            log.info("Have fun!");
        };
    }

    private void saveInDB(
            RegionRepository regionRepository,
            TypeRepository typeRepository,
            WineRepository wineRepository,
            WineryRepository wineryRepository
    ) {
        wineryRepository.saveAll(wineryList);
        log.info("Wineries saved.");
        regionRepository.saveAll(regionList);
        log.info("Regions saved.");
        typeRepository.saveAll(typeList);
        log.info("Types saved.");
        wineRepository.saveAll(wineList);
        log.info("Wines saved.");
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

            newWine = compareWinery(newWinery, newWine);
            newWine = compareRegion(newRegion, newWine);
            newWine = compareType(newType, newWine);
            wineList.add(newWine);
        }
    }

    private Wine compareWinery(Winery winery, Wine currentWine) {
        int index = -1;
        for (Winery wineryLooped :
                wineryList) {
            if (winery.equals(wineryLooped))
                index = wineryList.indexOf(wineryLooped);
            if (index != -1)
                break;
        }
        if (index == -1) {
            wineryList.add(winery);
            currentWine.setWinery(winery);
        } else
            currentWine.setWinery(wineryList.get(index));
        return currentWine;
    }

    private Wine compareRegion(Region region, Wine currentWine) {
        int index = -1;
        for (Region regionLooped :
                regionList) {
            if (region.equals(regionLooped))
                index = regionList.indexOf(regionLooped);
            if (index != -1)
                break;
        }
        if (index == -1) {
            regionList.add(region);
            currentWine.setRegion(region);
        } else
            currentWine.setRegion(regionList.get(index));
        return currentWine;
    }

    private Wine compareType(Type type, Wine currentWine) {
        int index = -1;
        for (Type typeLooped :
                typeList) {
            if (type.equals(typeLooped))
                index = typeList.indexOf(typeLooped);
            if (index != -1)
                break;
        }
        if (index == -1) {
            typeList.add(type);
            currentWine.setType(type);
        } else
            currentWine.setType(typeList.get(index));
        return currentWine;
    }

    private String cleanString(String s, boolean detailed) {
        if (detailed)
            log.warn(s);
        String newer = s.replace("N.V.", "0");
        if (detailed)
            log.info("Remove NV: "+newer);
        newer = newer.replace("\"\"", "0");
        if (detailed)
            log.info("Remove double quotes: "+newer);
        newer = newer.replace("NA", "0");
        if (detailed)
            log.info("Remove NA: "+newer);
        newer = newer.replace("\"", "");
        if (detailed)
            log.info("Remove quotes: "+newer);
        return newer;
    }

    private String cleanString(String s) {
        return cleanString(s, false);
    }
}