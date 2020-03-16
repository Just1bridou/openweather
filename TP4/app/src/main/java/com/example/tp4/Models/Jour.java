package com.example.tp4.Models;

import java.util.ArrayList;

public class Jour {

    private City city;
    private String jour;
    private ArrayList<Prevision> listPrevisions = new ArrayList<Prevision>();

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public Jour(String jour, City city) {
        this.jour = jour;
        this.city = city;
    }

    public ArrayList<Prevision> getListPrevisions() {
        return listPrevisions;
    }

    public void setPrevisions(ArrayList<Prevision> previsions) {
        this.listPrevisions = previsions;
    }

    public void addPrevision(Prevision prevision) {
        this.listPrevisions.add(prevision);
    }

    public Prevision GetOnePrevision(int index) {
        return this.listPrevisions.get(index);
    }
}
