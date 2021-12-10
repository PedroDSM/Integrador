package com.example.pokemonrv.Intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pokemonrv.Adaptadores.DistAdapter;
import com.example.pokemonrv.MainActivity;
import com.example.pokemonrv.Modelos.Dist;
import com.example.pokemonrv.R;
import com.example.pokemonrv.Singleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Distancia extends AppCompatActivity{
    String token_sesion;
    RecyclerView r;
    TextView p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distancia);

            r = findViewById(R.id.Disto);

            p = findViewById(R.id.balor);
            JSONObject a = new JSONObject();

            try {
                a.put("token", "aio_uyWY65Zuh4BrYdtrGxi3STyc32KF");
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "error de conexion", Toast.LENGTH_SHORT);
            }

            //https://blog.nubecolectiva.com/ejecutar-una-funcion-cada-5-segundos-u-otra-cantidad-de-tiempo-en-android/
            Peticion(a);

        }
        public void Peticion(JSONObject t){
            String url = "http://3.144.213.232/api/auth/distancia/datos";
            JsonObjectRequest jsonRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    t,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                            Gson gson = new Gson();
                            try {
                                JSONArray json_distancia = response.getJSONArray("datos");

                                final Type tipoListadi = new TypeToken<List<Dist>>(){}.getType();
                                final List<Dist> did = gson.fromJson(json_distancia.toString(), tipoListadi);
                                p.setText(did.get(0).getValue()+ "Cm");
                                DistAdapter pa = new DistAdapter(did);

                                r.setHasFixedSize(true);
                                r.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                r.setAdapter(pa);


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT);

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
                    headers.put("Authorization","Bearer "+ MainActivity.getDefaultsPreference("token",getApplicationContext()));
                    return headers;
                }
            };

            Singleton.getInstance(this).addToRequestQue(jsonRequest);
    }
}