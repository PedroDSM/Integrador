package com.example.pokemonrv.Adaptadores;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonrv.Modelos.temperatura;

import java.util.List;

public class temperaturaAdapter extends RecyclerView.Adapter<temperaturaAdapter.ViewHolder> {
    List<temperatura> t;
    @NonNull
    @Override
    public temperaturaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate();
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull temperaturaAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
