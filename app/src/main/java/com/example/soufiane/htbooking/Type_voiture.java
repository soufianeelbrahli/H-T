package com.example.soufiane.htbooking;

/**
 * Created by Soufiane on 06/11/2018.
 */

public class Type_voiture {
    private int id_type;
    private String libele;

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public Type_voiture() {

    }

    public Type_voiture(int id_type, String libele) {

        this.id_type = id_type;
        this.libele = libele;
    }
}
