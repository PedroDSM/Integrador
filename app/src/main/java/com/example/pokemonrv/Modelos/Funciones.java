package com.example.pokemonrv.Modelos;

import android.content.Intent;

public class Funciones {
    private String Nombre;
    private int imagen;
    private Intent intent;


    public Funciones(String nombre, Intent intent, int imagen) {
        Nombre = nombre;
        this.intent = intent;
        this.imagen = imagen;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
