package com.example.pokemonrv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonrv.Adaptadores.BabyAdapter;
import com.example.pokemonrv.Intents.Distancia;
import com.example.pokemonrv.Intents.Movimiento;
import com.example.pokemonrv.Intents.Puerta;
import com.example.pokemonrv.Intents.Sonido;
import com.example.pokemonrv.Intents.Temperatura;
import com.example.pokemonrv.Modelos.Funciones;

import java.util.ArrayList;
import java.util.List;

public class Inicio extends AppCompatActivity {
    public RecyclerView Recycler;

    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        Recycler = (RecyclerView) findViewById(R.id.Inicio);
        Recycler.setLayoutManager(new GridLayoutManager(this, 2));
        aSwitch = findViewById(R.id.door);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    Toast.makeText(getApplicationContext(), "Abriendo Puerta", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Cerrando Puerta", Toast.LENGTH_SHORT).show();
                }
            }
        });

        List<Funciones> menu = new ArrayList<>();
        menu.add(new Funciones("TEMPERATURA", new Intent(this, Temperatura.class), R.drawable.temperatura));
        menu.add(new Funciones("PUERTA", new Intent(this, Puerta.class), R.drawable.puerta));
        menu.add(new Funciones("PROXIMIDAD", new Intent(this, Distancia.class), R.drawable.distancia));
        menu.add(new Funciones("MOVIMIENTO", new Intent(this, Movimiento.class), R.drawable.movimiento));
        menu.add(new Funciones("SONIDO", new Intent(this, Sonido.class), R.drawable.sonido));
        menu.add(new Funciones("LOG OUT", new Intent(this, MainActivity.class), R.drawable.exit));

        BabyAdapter BA = new BabyAdapter(menu);
        Recycler.setAdapter(BA);
    }
}
