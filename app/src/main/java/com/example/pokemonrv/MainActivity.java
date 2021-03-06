package com.example.pokemonrv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button Accept;
    Button Register;
    EditText Mail;
    EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mail = findViewById(R.id.correo);
        Password = findViewById(R.id.pass);
        Accept = findViewById(R.id.btnLogin);
        Register = findViewById(R.id.registrarse);
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.registrarse) {
                    startActivity(new Intent(MainActivity.this, Registro.class));
                }
            }
        });

        Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postUrl = "http://3.144.213.232/api/auth/login";
                JSONObject postData = new JSONObject();
                try {
                    postData.put("email", Mail.getText().toString());
                    postData.put("password", Password.getText().toString());
                    postData.put("remember_me", true);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                        postUrl,
                        postData,
                        new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, "Login Succesfully", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(MainActivity.this, Inicio.class);
                        try {
                            MainActivity.setDefaultsPreference("token",response.getString("access_token"),view.getContext());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        finish();
                        startActivity(in);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(Mail.getText().toString().isEmpty() && Password.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this, "Error Username or Password are Incorrect", Toast.LENGTH_SHORT).show();
                        }
                        else if(Mail.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this, "Email Field Empty", Toast.LENGTH_SHORT).show();
                        }else if(Password.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this, "Password field Empty", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Error de conexi??n", Toast.LENGTH_SHORT).show();
                        }
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

    private static void setDefaultsPreference(String token, String t, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(token, t);
        editor.apply();
    }

    public static String getDefaultsPreference(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}
