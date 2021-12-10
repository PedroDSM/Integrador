package com.example.pokemonrv;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.example.pokemonrv.Adaptadores.BabyAdapter;
import com.example.pokemonrv.Modelos.Funciones;
import  com.example.pokemonrv.paginas.Actividades;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inicio extends AppCompatActivity implements View.OnClickListener {
    public RecyclerView Recycler;

    TextView tit;
    String token_sesion;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
        Recycler = findViewById(R.id.Inicio);
        Recycler.setLayoutManager(new GridLayoutManager(this, 2));
        tit = findViewById(R.id.tituloInicio);
        b =  findViewById(R.id.puerta_btn);
        b.setOnClickListener(this);
        JSONObject a = new JSONObject();
        token_sesion = MainActivity.getDefaultsPreference("token",getApplicationContext());

        try {
            a.put("token", "aio_DekL07V1JeTRdd8gGEK2dui9ZPs6");
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "error de conexion", Toast.LENGTH_SHORT);

        }

        peticion(a);

        List<Funciones> menu = new ArrayList<>();
        Actividades datos= new Actividades();
        for(int i = 0; i< datos.titulo.length;i++){
            menu.add(new Funciones(datos.titulo[i], new Intent(this, datos.clase[i]), datos.foto[i]));
        }
        BabyAdapter BA = new BabyAdapter(menu);
        Recycler.setAdapter(BA);
    }

    public void peticion(JSONObject token){
        String url = "http://3.144.213.232/api/auth/puerta/last";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                token,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            b.setText(response.getString("value") );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error de conexion", Toast.LENGTH_SHORT);

                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("X-Requested-With", "XMLHttpRequest");
                headers.put("Authorization","Bearer "+ token_sesion );
                return headers;
            }
        };

        Singleton.getInstance(this).addToRequestQue(jsonRequest);

    }
    public void mandar_peticion(JSONObject token){
        String url = "http://3.144.213.232/api/auth/puerta/controller";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                token,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            b.setText(response.getString("value"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "error de conexion", Toast.LENGTH_SHORT);

                    }
                }
        ){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                headers.put("X-Requested-With", "XMLHttpRequest");
                headers.put("Authorization","Bearer "+ token_sesion );
                return headers;
            }
        };

        Singleton.getInstance(this).addToRequestQue(jsonRequest);


    }

    @Override
    public void onClick(View v) {

        JSONObject a = new JSONObject();
        token_sesion = MainActivity.getDefaultsPreference("token",getApplicationContext());

        Button bt= (Button) v;
        if(b.getId()==R.id.puerta_btn && b.getText()=="ON"){

            try {
                a.put("token", "aio_nbFm10xdsvsK4crCrV7gbtbfT5a6");
                a.put("value", "OFF");
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "error de conexion", Toast.LENGTH_SHORT);

            }

        }
        else /*(b.getId()==R.id.puerta_btn && b.getText()=="OFF")*/{

            try {
                a.put("token", "aio_nbFm10xdsvsK4crCrV7gbtbfT5a6");
                a.put("value", "ON");
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "error de conexion", Toast.LENGTH_SHORT);

            }

        }

        mandar_peticion(a);
    }
}
