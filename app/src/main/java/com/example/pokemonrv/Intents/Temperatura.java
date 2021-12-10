package com.example.pokemonrv.Intents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.pokemonrv.Adaptadores.temperaturaAdapter;
import com.example.pokemonrv.MainActivity;
import com.example.pokemonrv.Modelos.temperatura;
import com.example.pokemonrv.R;
import com.example.pokemonrv.Singleton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Temperatura extends AppCompatActivity{
    String token_sesion;
    RecyclerView r;
    TextView p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        r = findViewById(R.id.temperaturas);

        p = findViewById(R.id.tx);
        JSONObject a = new JSONObject();

        try {
            a.put("token", "aio_DekL07V1JeTRdd8gGEK2dui9ZPs6");
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "error de conexion", Toast.LENGTH_SHORT);
        }

        //https://blog.nubecolectiva.com/ejecutar-una-funcion-cada-5-segundos-u-otra-cantidad-de-tiempo-en-android/
        Peticion(a);

    }
    public void Peticion(JSONObject t){
        String url = "http://3.144.213.232/api/auth/dht/temperatura/datos";
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                t,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        Gson gson = new Gson();
                        try {
                            JSONArray json_temperaturas = response.getJSONArray("datos");

                            final Type tipoListatemperaturas = new TypeToken<List<temperatura>>(){}.getType();
                            final List<temperatura> temperaturas = gson.fromJson(json_temperaturas.toString(), tipoListatemperaturas);
                            p.setText(temperaturas.get(0).getValue()+ "C°");
                            temperaturaAdapter pa = new temperaturaAdapter(temperaturas);

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
