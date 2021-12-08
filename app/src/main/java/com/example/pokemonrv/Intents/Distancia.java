package com.example.pokemonrv.Intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pokemonrv.Graficas.Destadisticas;
import com.example.pokemonrv.R;

public class Distancia extends AppCompatActivity implements View.OnClickListener {
Button estadisticas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distancia);

        estadisticas=findViewById(R.id.esta);
        estadisticas.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
if (view.getId()==R.id.esta){
    startActivity(new Intent(Distancia.this, Destadisticas.class));
}
    }
}