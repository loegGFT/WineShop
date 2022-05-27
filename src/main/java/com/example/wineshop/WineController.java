package com.example.wineshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/wine")
public class WineController {
    @Autowired
    private WineRepository wineRepository;

    @Autowired
    private WineRepresentationModelAssembler assembler;


    @GetMapping("/{id}")
    Wine one(@PathVariable Integer id) throws Exception {
        return wineRepository.findById(id).orElseThrow(() -> new WineNotFoundException(id));
    }

    @GetMapping("/hal2/{id}")
    ResponseEntity<EntityModel<Wine>> hal2_one(@PathVariable Integer id) throws WineNotFoundException {
        return wineRepository.findById(id)
                .map(wine -> assembler.toModel(wine))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new WineNotFoundException(id));
    }

}