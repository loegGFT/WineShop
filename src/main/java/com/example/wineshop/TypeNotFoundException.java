package com.example.wineshop;

public class TypeNotFoundException extends RuntimeException {

    TypeNotFoundException(int id){
        super("Could not find type "+ id);
    }
}
