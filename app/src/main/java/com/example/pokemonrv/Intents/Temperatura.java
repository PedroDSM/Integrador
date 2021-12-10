package com.example.pokemonrv.Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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

public class Temperatura extends AppCompatActivity implements View.OnClickListener{
        Double valor;
        TextView tx;
        GraphView graph;
        LineGraphSeries series;
        DataPoint[] d =new DataPoint[50];
        int i = 0;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_temperatura);
            findViewById(R.id.get).setOnClickListener(this);
            tx = findViewById(R.id.tx);

            graph = (GraphView) findViewById(R.id.graph);
        }

        @Override
        public void onClick(View view) {


            JSONObject datos = new JSONObject();
            if(view.getId() == R.id.get){
                peticion2(datos);
            }

        }
    public void peticion2(JSONObject datos){
        String url = "http://3.144.213.232/api/auth/dht/temperatura/last";
        JsonObjectRequest sendata = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        final Gson gson = new Gson();

                        try {
                            JSONArray temp_json = response.getJSONArray("datos");
                            final Type tipo_lista_temperaturas = new TypeToken<List<temperatura>>(){}.getType();
                            final List<temperatura> listatemperaturas = gson.fromJson(temp_json.toString(), tipo_lista_temperaturas);
                            int i = 0;

                            valor = Double.parseDouble(response.getString("value"));
                            tx.setText(valor.toString());
                            series = new LineGraphSeries<>(d[i] = // on below line we are adding
                                    // each point on our x and y axis.
                                    new DataPoint(valor,0),
                            i++);

                            graph.setTitle("My Graph View");

                            // on below line we are setting
                            // text color to our graph view.
                            graph.setTitleColor(R.color.purple_200);

                            // on below line we are setting
                            // our title text size.
                            graph.setTitleTextSize(18);

                            // on below line we are adding
                            // data series to our graph view.
                            graph.addSeries(series);
                            graph.getGridLabelRenderer().setNumHorizontalLabels(10); // only 4 because of the space
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
                headers.put("X-AIO-Key", "aio_fgla19M00DsojOe5wf65Di7CDTMI");
                return headers;
            }
        };

        Singleton.getInstance(this).addToRequestQue(sendata);

    }
}
