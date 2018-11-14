package com.example.soufiane.htbooking;

/**
 * Created by Soufiane on 06/11/2018.
 */

public class Voiture {
    private int id_voiture;
    private int id_type;
    private String matricule;
    private int disponible;

    public Voiture() {
    }

    public Voiture(int id_voiture, int id_type, String matricule, int disponible) {

        this.id_voiture = id_voiture;
        this.id_type = id_type;
        this.matricule = matricule;
        this.disponible = disponible;
    }

    public int getId_voiture() {

        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }
}
