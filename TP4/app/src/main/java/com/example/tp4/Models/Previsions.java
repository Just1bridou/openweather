package com.example.tp4.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Previsions implements Serializable {

    private List<Prevision> list;
    private City city;

    public Previsions(List<Prevision> list, City city) {
        this.list = list;
        this.city = city;
    }

    public List<Prevision> getList() {

        return list;
    }

    public void setList(List<Prevision> list) {

        this.list = list;
    }

    public City getCity() {

        return city;
    }

    public void setCity(City city) {

        this.city = city;
    }

    @Override
    public String toString() {
        return "Previsions{" +
                "list=" + list +
                ", city='" + city + '\'' +
                '}';
    }
}