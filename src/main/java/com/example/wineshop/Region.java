package com.example.wineshop;

import org.springframework.hateoas.server.core.Relation;

import javax.persistence.*;

@Entity
@Table(name = "region")
@Relation(collectionRelation = "regions", itemRelation = "region")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "country")
    private String country;

    public Region(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Region() {

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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (name != null ? !name.equals(region.name) : region.name != null) return false;
        return country != null ? country.equals(region.country) : region.country == null;
    }
}