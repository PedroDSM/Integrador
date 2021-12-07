package com.example.pokemonrv;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singleton {
    private static Singleton singleton;
    private RequestQueue peticion;

    private Singleton(Context context){ //obtiene el contexto de la aplicacion
        peticion= Volley.newRequestQueue(context.getApplicationContext());
    }

    public RequestQueue getPeticion() // Este obtendra la peticion que hagamos a una api
    {
        return peticion;
    }

    public static synchronized Singleton getInstance(Context context)
    {
        if(singleton==null)
        {
            singleton = new Singleton(context);
        }
        return singleton;
    }
}
