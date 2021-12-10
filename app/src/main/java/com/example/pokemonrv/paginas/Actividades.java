package com.example.pokemonrv.paginas;

import com.example.pokemonrv.Intents.Distancia;
import com.example.pokemonrv.Intents.Puerta;
import com.example.pokemonrv.Intents.Sonido;
import com.example.pokemonrv.Intents.Temperatura;
import com.example.pokemonrv.R;
import com.example.pokemonrv.Splashes.SplashLogOut;

public class Actividades {
    public String[] titulo= {
            "TEMPERATURA",
            "PUERTA",
            "PROXIMIDAD",
            "SONIDO",
            "LOG OUT"
    };
    public Class[] clase= {
            Temperatura.class,
            Puerta.class,
            Distancia.class,
            Sonido.class,
            SplashLogOut.class

    };
    public int[] foto={
            R.drawable.temperatura,
            R.drawable.puerta,
            R.drawable.distancia,
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
