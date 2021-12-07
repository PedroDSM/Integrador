package com.example.pokemonrv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;

public class Registro extends AppCompatActivity{
    Button registrar;
    Button Login;
    EditText Usuario;
    EditText Contarseña;
    EditText Correo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Usuario =findViewById(R.id.user);
        Correo = findViewById(R.id.correoR);
        Contarseña = findViewById(R.id.Passw);
        registrar = findViewById(R.id.btnRegis);
        Login=findViewById(R.id.login2);

        RequestQueue requestQueue = Singleton.getInstance(this).getPeticion();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postUrl = "http://3.144.213.232:80/api/auth/signup";
                JSONObject postData = new JSONObject();
                try {
                    postData.put("name",Usuario.getText().toString());
                    postData.put("email", Correo.getText().toString());
                    postData.put("password", Contarseña.getText().toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(Registro.this, "Registrado con Exito...", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                requestQueue.add(jsonObjectRequest);
                startActivity(new Intent(Registro.this, MainActivity.class));

            }
        });
    }
}