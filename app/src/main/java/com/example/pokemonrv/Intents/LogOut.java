package com.example.pokemonrv.Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pokemonrv.Inicio;
import com.example.pokemonrv.MainActivity;
import com.example.pokemonrv.R;
import com.example.pokemonrv.Registro;
import com.example.pokemonrv.Singleton;
import com.example.pokemonrv.Splashes.SplashLogOut;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LogOut extends AppCompatActivity {
    Button salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_out);

        salir = findViewById(R.id.logout);
        salir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String postUrl = "http://3.144.213.232/api/auth/logout";
                    JSONObject postData = new JSONObject();
                    try {
                        postData.put("remember_me", true);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(getApplicationContext(), "LogOut Succesfully... See You Later!", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Cannot LogOut User", Toast.LENGTH_SHORT).show();
                            Log.i("Mi error", error.toString());
                            error.printStackTrace();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() throws AuthFailureError {
                            HashMap<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "application/json");
                            return headers;
                        }
                    };
                    Singleton.getInstance(getApplicationContext()).addToRequestQue(jsonObjectRequest);
                }
            });
    }
}