package com.example.wineshop;

class WineNotFoundException extends RuntimeException {

    WineNotFoundException(int id){
        super("Could not find wine "+ id);
    }
}
