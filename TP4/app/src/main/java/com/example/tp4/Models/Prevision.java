package com.example.tp4.Models;

import java.io.Serializable;

public class Prevision implements Serializable {

    private String dt_txt;
    private Main main;

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public Main getMain() {

        return main;
    }

    public void setMain(Main main) {

        this.main = main;
    }

    public Prevision(Main main, String dt_txt) {

        this.main = main;
        this.dt_txt = dt_txt;
    }

    @Override
    public String toString() {
        return "Prevision{" +
                "dt_txt='" + dt_txt + '\'' +
                ", main=" + main +
                '}';
    }
}