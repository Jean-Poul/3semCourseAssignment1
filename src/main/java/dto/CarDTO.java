/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Car;

/**
 *
 * @author jplm
 */
public class CarDTO {

    // Private variables
    private Long id;
    private int year;
    private String maker;
    private String model;
    private int price;

    // Empty constructor
    public CarDTO() {
    }

    // Constructor with a Car object
    public CarDTO(Car car) {
        this.id = car.getId();
        this.year = car.getYear();
        this.maker = car.getMaker();
        this.model = car.getModel();
        this.price = car.getPrice();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
