package com.example.pokemonrv;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singleton {
    private static Singleton singleton;
    private RequestQueue requestQueue;
    private static Context ct;

    private Singleton(Context context){
        this.ct=context;
        this.requestQueue=getRequestQue();
    }
    public RequestQueue getRequestQue(){
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(ct.getApplicationContext());
        }
        return requestQueue;
    }
    public static synchronized Singleton getInstance(Context context)
    {
        if(singleton==null)
        {
            singleton = new Singleton(context);
        }
        return singleton;
    }
    public<T> void addToRequestQue(Request<T> request){
        requestQueue.add(request);
    }
}
