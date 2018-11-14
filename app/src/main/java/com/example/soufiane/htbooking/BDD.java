package com.example.soufiane.htbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Soufiane on 06/11/2018.
 */

public class BDD extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    //Version de la base de données
    private static final int DATABASE_VERSION = 6;
    //Nom de la base de données
    private static final String DATABASE_NAME = "HTBooking";
    //Noms de tables
    private static final String TABLE_ADHERENT_i = "adherent";
    private static final String TABLE_TYPE_VOITURE_i = "type_voiture";
    private static final String TABLE_VOITURE_i = "voiture";
    private static final String TABLE_RESERVATION_i = "reservation";
    // Noms des colonnes de la table adherent (Utile pour appel aux fonctions)
    private String id_adherent="id_adherent";
    private String login="login";
    private String password="password";
    private String email="email";
    private String telephone="telephone";
    // Noms des colonnes de la table type_voiture
    private String id_type="id_type";
    private String libelle="libelle";
    // Noms des colonnes de la table voiture
    private String id_voiture="id_voiture";
    private String id_typev="id_type";
    private String matricule="matricule";
    private String disponible="disponible";
    // Noms des colonnes de la table reservation
    private String id_reservation="id_reservation";
    private String id_adherentr="id_adherent";
    private String id_voiturer="id_voiture";
    private String date_debut="date_debut";
    private String date_fin="date_fin";
    //Constructeur
    public BDD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //Création de la BDD
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Table adherent
        String CREATE_TABLE_ADHERENT = "CREATE TABLE IF NOT EXISTS " + TABLE_ADHERENT_i + "("
                + id_adherent + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + login + " VARCHAR(150) ,"
                + password + " VARCHAR(150) ,"
                + email + " VARCHAR(200) ,"
                + telephone + " VARCHAR(250) NOT NULL"
                +");";

        db.execSQL(CREATE_TABLE_ADHERENT);
        Log.i("DATABASE","CREATED DATABASE Adherent");
        System.out.println("CREATED");
        //Table Type_voiture
        String CREATE_TABLE_TYPEVOITURE = "CREATE TABLE IF NOT EXISTS " + TABLE_TYPE_VOITURE_i + "("
                + id_type + " INTEGER PRIMARY KEY NOT NULL,"
                + libelle + " VARCHAR(50)"
                +");";

        db.execSQL(CREATE_TABLE_TYPEVOITURE);
        Log.i("DATABASE","CREATED DATABASE TV");
        System.out.println("CREATED");
        //Table voiture
        String CREATE_TABLE_VOITURE = "CREATE TABLE IF NOT EXISTS " + TABLE_VOITURE_i + "("
                + id_voiture + " INTEGER PRIMARY KEY  NOT NULL,"
                + id_typev + " INTEGER ,"
                + matricule + " VARCHAR(50) ,"
                + disponible + " INTEGER,"
                +" FOREIGN KEY (ID_TYPE) REFERENCES type_voiture(ID_TYPE))";

        db.execSQL(CREATE_TABLE_VOITURE);
        Log.i("DATABASE","CREATED DATABASE Voiture");
        System.out.println("CREATED");
        //Table reservation

        String CREATE_TABLE_RESERVATION = "CREATE TABLE IF NOT EXISTS " + TABLE_RESERVATION_i + "("
                + id_reservation + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + id_adherentr + " INTEGER ,"
                + id_voiturer + " INTEGER ,"
                + date_debut + " INT(8),"
                + date_fin + " INT(8),"
                +" FOREIGN KEY (ID_adherent) REFERENCES adherent(ID_adherent), FOREIGN KEY (ID_voiture) REFERENCES voiture(ID_voiture))";

        db.execSQL(CREATE_TABLE_RESERVATION);
        Log.i("DATABASE","CREATED DATABASE Reservation");
        System.out.println("CREATED");

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADHERENT_i);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VOITURE_i);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESERVATION_i);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TYPE_VOITURE_i);
        onCreate(db);
    }
    public Boolean addAdherent(Adherent adherent) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
            values.put(id_adherent, adherent.getId_adherent());
            values.put(email, adherent.getEmail());
            values.put(telephone, adherent.getTelephone());
            values.put(login, adherent.getLogin());
            values.put(password, adherent.getPassword());
            // Inserting Row
            db.insert(TABLE_ADHERENT_i, null, values);
            System.out.println("Adherent ajouté");
            return true;

    }
    public Adherent getAdherentWithlogin(String log, String Pass){
        db=this.getWritableDatabase();
        String[] selectionArgs = {log, Pass};
        String whereClause = login + "=?" + " and " + password +"=?" ;

        Cursor c = db.query(TABLE_ADHERENT_i, new String[] {id_adherent, login,
                password,email,telephone},whereClause , selectionArgs,null, null, null);
        return cursorToAdherent(c);
    }

    //CURSOR DE LA RECHERCHE AVEC LOGIN ET MDP
    private Adherent cursorToAdherent(Cursor c){

        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        Adherent adherent = new Adherent();
        adherent.setId_adherent(c.getInt(0));
        adherent.setLogin(c.getString(1));
        adherent.setPassword(c.getString(2));
        adherent.setEmail(c.getString(3));
        adherent.setTelephone(c.getString(3));

        c.close();
        return adherent;
    }
    public Boolean getAdherentWithloginOnly(String log){
        db=this.getWritableDatabase();
        String[] selectionArgs = {log};
        String whereClause = login + "=?";

        Cursor c = db.query(TABLE_ADHERENT_i, new String[] {},whereClause , selectionArgs,null, null, null);
        return cursorToAdherentcreation(c);
    }

    //CURSOR DE LA RECHERCHE AVEC LOGIN ET MDP
    private Boolean cursorToAdherentcreation(Cursor c){

        if (c.getCount() == 0)
            return true;

        else{
            return false;
        }

    }
    public int getAdherents() throws ParseException {
        ArrayList<Adherent> adherents = new ArrayList<Adherent>();
        db = this.getWritableDatabase();
        int counter=0;
        String query = "SELECT * FROM ADHERENT ";

        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {         counter++;
            } while (c.moveToNext());
        } else {
            System.out.println("Vide");
        }
        c.close();
        return counter;


    }
    public Boolean addType(Type_voiture type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id_type, type.getId_type());
        values.put(libelle, type.getLibele());
        // Inserting Row
        db.insert(TABLE_TYPE_VOITURE_i, null, values);
        System.out.println("Type ajouté");
        return true;
    }
    public int getType(){
        ArrayList<Type_voiture> Type = new ArrayList<Type_voiture>();
        db = this.getWritableDatabase();
        int counter=0;
        String query = "SELECT * FROM Type_voiture ";

        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {         counter++;
            } while (c.moveToNext());
        } else {
            System.out.println("Vide");
        }
        c.close();
        return counter;
    }
    public Boolean addCar(Voiture voiture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id_voiture, voiture.getId_voiture());
        values.put(matricule, voiture.getMatricule());
        values.put(disponible, voiture.getDisponible());
        values.put(id_type, voiture.getId_type());
        // Inserting Row
        db.insert(TABLE_VOITURE_i, null, values);
        System.out.println("Voiture ajouté");
        return true;
    }
    public int getCar(){
        db = this.getWritableDatabase();
        int counter=0;
        String query = "SELECT * FROM Voiture ";

        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {         counter++;
            } while (c.moveToNext());
        } else {
            System.out.println("Vide");
        }
        c.close();
        return counter;
    }
    public Voiture getCarsWithIdOnly(String Id_voiture){
        db=this.getWritableDatabase();
        String[] selectionArgs = {Id_voiture};
        String whereClause = id_voiture + "=?";

        Cursor c = db.query(TABLE_VOITURE_i, new String[] {id_voiture,id_typev,matricule,disponible},whereClause , selectionArgs,null, null, null);
        return cursorToCar(c);
    }

    //CURSOR DE LA RECHERCHE AVEC LOGIN ET MDP
    private Voiture cursorToCar(Cursor c){

        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        Voiture voiture=new Voiture();
        voiture.setId_voiture(c.getInt(0));
        voiture.setId_type(c.getInt(1));
        voiture.setMatricule(c.getString(2));
        voiture.setDisponible(c.getInt(3));
        c.close();
        return voiture;
        }
    public Voiture getCarsWithMatriculeOnly(String Matricule){
        db=this.getWritableDatabase();
        String[] selectionArgs = {Matricule};
        String whereClause = matricule + "=?";

        Cursor c = db.query(TABLE_VOITURE_i, new String[] {id_voiture,id_typev,matricule,disponible},whereClause , selectionArgs,null, null, null);
        return cursorToCarMatricule(c);
    }

    //CURSOR DE LA RECHERCHE AVEC LOGIN ET MDP
    private Voiture cursorToCarMatricule(Cursor c){

        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        Voiture voiture=new Voiture();
        voiture.setId_voiture(c.getInt(0));
        voiture.setId_type(c.getInt(1));
        voiture.setMatricule(c.getString(2));
        voiture.setDisponible(c.getInt(3));
        c.close();
        return voiture;
    }
    public Type_voiture getTypeWithIdOnly(String Type){
        db=this.getWritableDatabase();
        String[] selectionArgs = {Type};
        String whereClause = id_type + "=?";

        Cursor c = db.query(TABLE_TYPE_VOITURE_i, new String[] {id_type,libelle},whereClause , selectionArgs,null, null, null);
        return cursorToTypeCar(c);
    }

    //CURSOR DE LA RECHERCHE AVEC LOGIN ET MDP
    private Type_voiture cursorToTypeCar(Cursor c){

        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        Type_voiture Tvoiture=new Type_voiture();
        Tvoiture.setId_type(c.getInt(0));
        Tvoiture.setLibele(c.getString(1));
        c.close();
        return Tvoiture;
    }
    public ArrayList<Voiture> getAllCars(){
        ArrayList<Voiture> Cars = new ArrayList<Voiture>();
        db = this.getWritableDatabase();
        String query = "SELECT * FROM VOITURE ";

        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                Voiture Car = new Voiture(c.getInt(0), c.getInt(1), c.getString(2), c.getInt(3));
                Cars.add(Car);
            } while (c.moveToNext());
        }
        else{
            System.out.println("No data inserted");
        }
        c.close();
        db.close();
        return Cars;
    }
    public Boolean addRes(Reservation reservation) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id_reservation, reservation.getId_reservation());
        values.put(id_adherentr, reservation.getId_adherent());
        values.put(id_voiturer, reservation.getId_voiture());
        // Inserting Row
        db.insert(TABLE_RESERVATION_i, null, values);
        System.out.println("Reservation ajoutée");
        return true;
    }
    public long UpdateVoiture(Voiture voiture, int dispo){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(disponible, String.valueOf(dispo));

        String whereClause = id_voiturer + "=?"  ;
        String[] whereArguments = {String.valueOf(voiture.getId_voiture())};


        return db.update(TABLE_VOITURE_i, values, whereClause, whereArguments);
    }
    public ArrayList<Reservation> getAllReservations(){
        ArrayList<Reservation> Reservations = new ArrayList<Reservation>();
        db = this.getWritableDatabase();
        String query = "SELECT * FROM Reservation ";

        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {
                Reservation reservation = new Reservation(c.getInt(0), c.getInt(1), c.getInt(2), 0,0);
                Reservations.add(reservation);
            } while (c.moveToNext());
        }
        else{
            System.out.println("No data inserted");
        }
        c.close();
        db.close();
        return Reservations;
    }
    public Reservation getReservationWithIdOnly(String id_adherent){
        db=this.getWritableDatabase();
        String[] selectionArgs = {id_adherent};
        String whereClause = id_adherentr + "=?";

        Cursor c = db.query(TABLE_RESERVATION_i, new String[] {id_reservation,id_adherentr,id_voiturer},whereClause , selectionArgs,null, null, null);
        return cursorToReservation(c);
    }

    private Reservation cursorToReservation(Cursor c){

        if (c.getCount() == 0)
            return null;

        c.moveToFirst();
        Reservation resa=new Reservation();
        resa.setId_reservation(c.getInt(0));

        resa.setId_adherent(c.getInt(1));

        resa.setId_voiture(c.getInt(2));
        c.close();
        return resa;
    }
    public int getResa(){
        db = this.getWritableDatabase();
        int counter=0;
        String query = "SELECT * FROM Reservation ";

        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()) {
            do {         counter++;
            } while (c.moveToNext());
        } else {
            System.out.println("Vide");
        }
        c.close();
        return counter;
    }
    public long supprimerReservationById(int idr){
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String whereClause = id_reservation  + "=?"  ;
        String[] whereArguments = {String.valueOf(idr)};
        System.out.println("Suppression réussie");
        return db.delete(TABLE_RESERVATION_i, whereClause, whereArguments);
    }


}


