package com.example.soufiane.htbooking;

/**
 * Created by Soufiane on 06/11/2018.
 */

public class Adherent {
    private int id_adherent;
    private String login;
    private String password;
    private String telephone;
    private String email;

    public Adherent(int id_adherent, String login, String password, String telephone, String email) {
        this.id_adherent = id_adherent;
        this.login = login;
        this.password = password;
        this.telephone = telephone;
        this.email = email;
    }

    public String getLogin() {

        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_adherent() {
        return id_adherent;
    }

    public void setId_adherent(int id_adherent) {
        this.id_adherent = id_adherent;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Adherent() {

    }
}
