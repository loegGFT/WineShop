package com.example.wineshop;

public class RegionNotFoundException extends RuntimeException {

    RegionNotFoundException(int id){
        super("Could not find region "+ id);
    }
}
