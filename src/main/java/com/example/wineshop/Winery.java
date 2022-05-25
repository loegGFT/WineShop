package com.example.wineshop;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Winery {

    private @Id @GeneratedValue Integer id;
    private String name;

    public Winery(String name) {
        this.name = name;
    }

    public Winery() {

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}