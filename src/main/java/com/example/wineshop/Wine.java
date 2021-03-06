package com.example.wineshop;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.server.core.Relation;
import org.springframework.lang.NonNull;

import javax.persistence.*;


@Entity
@Table(name = "wine")
@JsonIgnoreProperties(ignoreUnknown = true)
@Relation(collectionRelation = "wines", itemRelation = "wine")
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @NonNull
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "year")
    private Integer year;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "num_reviews")
    private Integer num_reviews;

    @Column(name = "price")
    private Float price;

    @Column(name = "body")
    private Integer body;

    @Column(name = "acidity")
    private Integer acidity;

    public Wine(String name, Integer year, Float rating, Integer num_reviews, Float price, Integer body, Integer acidity) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.num_reviews = num_reviews;
        this.price = price;
        this.body = body;
        this.acidity = acidity;
    }

    public Wine() {

    }

    @ManyToOne
    @JoinColumn(name = "winery_id")
    private Winery winery;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "region_id")
//    @JsonIgnore
    private Region region;

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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Integer getNum_reviews() {
        return num_reviews;
    }

    public void setNum_reviews(Integer num_reviews) {
        this.num_reviews = num_reviews;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getBody() {
        return body;
    }

    public void setBody(Integer body) {
        this.body = body;
    }

    public Integer getAcidity() {
        return acidity;
    }

    public void setAcidity(Integer acidity) {
        this.acidity = acidity;
    }

    public Winery getWinery() {
        return winery;
    }

    public void setWinery(Winery winery) {
        this.winery = winery;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
}
