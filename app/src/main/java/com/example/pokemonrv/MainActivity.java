package com.example.pokemonrv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button Accept;
    Button Register;
    EditText Mail;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mail = findViewById(R.id.correo);
        Password = findViewById(R.id.pass);
        Accept = findViewById(R.id.btnLogin);
        Accept.setOnClickListener(this);
        Register = findViewById(R.id.registrarse);
        Register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnLogin){
            startActivity(new Intent(MainActivity.this,Inicio.class));
        }else if(v.getId()==R.id.registrarse){
            startActivity(new Intent(MainActivity.this,Registro.class));
        }
    }
}