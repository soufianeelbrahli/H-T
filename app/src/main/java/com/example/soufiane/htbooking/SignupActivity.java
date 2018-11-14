package com.example.soufiane.htbooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;

public class SignupActivity extends AppCompatActivity {
    //Déclaration des vues
    EditText login,password,repassword,tel,email;
    Button Creer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Récupération des vues
        login=findViewById(R.id.editText);
        password=findViewById(R.id.editText6);
        repassword=findViewById(R.id.editText8);
        tel=findViewById(R.id.editText2);
        email=findViewById(R.id.editText5);
        Creer=findViewById(R.id.button2);
        final BDD db=new BDD(this);
        db.getWritableDatabase();
        Creer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(db.getAdherentWithloginOnly(login.getText().toString())==true){
                    try {
                        int id_adherent=db.getAdherents();
                        Adherent adherent=new Adherent();adherent.setId_adherent(id_adherent);adherent.setLogin(login.getText().toString());adherent.setPassword(password.getText().toString());adherent.setTelephone(tel.getText().toString());
                        adherent.setEmail(email.getText().toString());
                        db.addAdherent(adherent);
                        Toast.makeText(getApplicationContext(), "Bienvenue à bord!",
                                Toast.LENGTH_LONG).show();
                        finish();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(), "Login déjà existant",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
