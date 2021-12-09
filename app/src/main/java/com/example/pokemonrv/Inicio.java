package com.example.pokemonrv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pokemonrv.Actividades.Actividades;
import com.example.pokemonrv.Adaptadores.BabyAdapter;
import com.example.pokemonrv.Intents.Distancia;
import com.example.pokemonrv.Intents.Movimiento;
import com.example.pokemonrv.Intents.Puerta;
import com.example.pokemonrv.Intents.Sonido;
import com.example.pokemonrv.Intents.Temperatura;
import com.example.pokemonrv.Modelos.Funciones;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inicio extends AppCompatActivity {
    public RecyclerView Recycler;


    Switch aSwitch;
    TextView titulo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        Recycler = (RecyclerView) findViewById(R.id.Inicio);
        Recycler.setLayoutManager(new GridLayoutManager(this, 2));
        aSwitch = findViewById(R.id.door);
        titulo = findViewById(R.id.Title);

        SharedPreferences preferences = getPreferences(MODE_PRIVATE);

        String   nombre =preferences.getString("Nombre","");
        int   edad =preferences.getInt("Edad",0);
        titulo.setText("El nombre es: "+nombre);






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
        Actividades a= new Actividades();
        for(int i = 0; i< a.titulo.length;i++){
            menu.add(new Funciones(a.titulo[i], new Intent(this, a.clase[i]), a.foto[i]));
        }

        BabyAdapter BA = new BabyAdapter(menu);
        Recycler.setAdapter(BA);
    }
    public void verificarPuerta(){
        String postUrl = "http://3.144.213.232/api/auth/signup";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                postUrl,
                null,
                new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
                Log.i("Mi error", error.toString());
                error.printStackTrace();
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String,String>();
                headers.put("X-Requested-With","XMLHttpRequest");
                headers.put("Content-Type","application/json");
                return headers;
            }
        };
        Singleton.getInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
//                startActivity(new Intent(Registro.this, MainActivity.class));

    }
}
