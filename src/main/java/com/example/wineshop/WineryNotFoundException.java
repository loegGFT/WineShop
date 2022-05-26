package com.example.wineshop;

public class WineryNotFoundException extends RuntimeException {

    WineryNotFoundException(int id){
        super("Could not find winery "+ id);
    }
}
