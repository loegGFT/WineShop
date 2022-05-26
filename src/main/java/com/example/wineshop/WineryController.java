package com.example.wineshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/winery")
public class WineryController {
    @Autowired
    private WineryRepository wineryRepository;

    @GetMapping("/{id}")
    Winery one(@PathVariable Integer id) throws WineryNotFoundException {
        return wineryRepository.findById(id).orElseThrow(() -> new WineryNotFoundException(id));
    }

    @GetMapping("/hal/{id}")
    EntityModel<Winery> hal_one(@PathVariable Integer id) throws WineryNotFoundException {
        Winery winery = wineryRepository.findById(id).orElseThrow(() -> new WineryNotFoundException(id));
        return EntityModel.of(winery,
                linkTo(methodOn(WineryController.class).hal_one(id)).withSelfRel()
        );
    }
}