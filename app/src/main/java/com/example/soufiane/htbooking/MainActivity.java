package com.example.soufiane.htbooking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Déclaration des vues
    TextView textView;
    EditText login,mdp;
    Button Signin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initialisation de la base de données
        final BDD db=new BDD(this);
        db.getWritableDatabase();
        // Récuperation des vues
        login=findViewById(R.id.editText3);
        mdp=findViewById(R.id.editText4);
        textView=findViewById(R.id.textView);
        Signin=findViewById(R.id.button);
        switch (db.getType()){
            case 0: Type_voiture tv=new Type_voiture();tv.setId_type(1);tv.setLibele("Berline");db.addType(tv);tv=new Type_voiture();tv.setId_type(2);tv.setLibele("SUV");db.addType(tv);break;
            case 1: tv=new Type_voiture();tv.setId_type(2);tv.setLibele("SUV");db.addType(tv);break;
            default : break;
        }
        switch (db.getCar()){
            case 0 : Voiture voiture=new Voiture(1,1,"458785",1);db.addCar(voiture);
                             voiture=new Voiture(2,1,"458887",1);db.addCar(voiture);
                             voiture=new Voiture(3,1,"471414",1);db.addCar(voiture);
                             voiture=new Voiture(4,2,"448822",1);db.addCar(voiture);
                             voiture=new Voiture(5,2,"968875",1);db.addCar(voiture);
                             break;
            default : break;
        }
        //Traitement
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
                            }
        });
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Adherent adherent=db.getAdherentWithlogin(login.getText().toString(),mdp.getText().toString());
                if(adherent!=null){
                    Intent intent=new Intent(MainActivity.this, MenuActivity.class);
                    //Définition des arguments à passer vers SignupActivity
                    intent.putExtra("id_adherent",adherent.getId_adherent());
                    intent.putExtra("login",adherent.getLogin());
                    intent.putExtra("email",adherent.getEmail());
                    intent.putExtra("telephone",adherent.getTelephone());
                    startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Connecté avec succès!",
                                Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "Combinaison Login/Password erronée!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
