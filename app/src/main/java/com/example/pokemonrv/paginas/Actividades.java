package com.example.pokemonrv.paginas;

import android.content.Intent;

import com.example.pokemonrv.Intents.Distancia;
import com.example.pokemonrv.Intents.Movimiento;
import com.example.pokemonrv.Intents.Puerta;
import com.example.pokemonrv.Intents.Sonido;
import com.example.pokemonrv.Intents.Temperatura;
import com.example.pokemonrv.MainActivity;
import com.example.pokemonrv.R;

public class Actividades {
    public String[] titulo= {
            "TEMPERATURA",
            "PUERTA",
            "PROXIMIDAD",
            "MOVIMIENTO",
            "SONIDO",
            "LOG OUT"
    };
    public Class[] clase= {
            Temperatura.class,
            Puerta.class,
            Distancia.class,
            Movimiento.class,
            Sonido.class,
            MainActivity.class

    };
    public int[] foto={
            R.drawable.temperatura,
            R.drawable.puerta,
            R.drawable.distancia,
            R.drawable.movimiento,
            R.drawable.sonido,
            R.drawable.exit
    };

    public Actividades() {
    }

    public String[] getTitulo() {
        return titulo;
    }
    public void setTitulo(String[] titulo) {
        this.titulo = titulo;
    }
    public Class[] getClase() {
        return clase;
    }
}
