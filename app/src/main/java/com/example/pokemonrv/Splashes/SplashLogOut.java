package com.example.pokemonrv.Splashes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.pokemonrv.Inicio;
import com.example.pokemonrv.MainActivity;
import com.example.pokemonrv.R;

public class SplashLogOut extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_log_out);

        new CountDownTimer(1800, 1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                startActivity(new Intent(SplashLogOut.this, MainActivity.class));

            }
        }.start();
    }
}