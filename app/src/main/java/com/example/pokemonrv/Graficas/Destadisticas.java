package com.example.pokemonrv.Graficas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pokemonrv.Intents.Distancia;
import com.example.pokemonrv.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Destadisticas extends AppCompatActivity implements View.OnClickListener {

    Button back;
    LineGraphSeries<DataPoint> series;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destadisticas);
        back=findViewById(R.id.back);
        back.setOnClickListener(this);

        double y, x;
        x = -5.0;

        GraphView graph = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        for (int i = 0; i < 200; i++) {
            x = x + 0.1;
            y = Math.sin(x);
            series.appendData(new DataPoint(x, y), true, 200);
        }
        graph.addSeries(series);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.back){
            startActivity(new Intent(Destadisticas.this, Distancia.class));
        }
    }
}