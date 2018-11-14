package com.example.soufiane.htbooking;

/**
 * Created by Soufiane on 06/11/2018.
 */

public class Reservation {
    private int id_reservation;
    private int id_adherent;
    private int id_voiture;
    private int date_debut;
    private int date_fin;

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_adherent() {
        return id_adherent;
    }

    public void setId_adherent(int id_adherent) {
        this.id_adherent = id_adherent;
    }

    public int getId_voiture() {
        return id_voiture;
    }

    public void setId_voiture(int id_voiture) {
        this.id_voiture = id_voiture;
    }

    public int getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(int date_debut) {
        this.date_debut = date_debut;
    }

    public int getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(int date_fin) {
        this.date_fin = date_fin;
    }

    public Reservation() {

    }

    public Reservation(int id_reservation, int id_adherent, int id_voiture, int date_debut, int date_fin) {

        this.id_reservation = id_reservation;
        this.id_adherent = id_adherent;
        this.id_voiture = id_voiture;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
}
