package com.example.pokemonrv.Adaptadores;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonrv.Modelos.temperatura;
import com.example.pokemonrv.R;

import java.util.List;

public class temperaturaAdapter extends RecyclerView.Adapter<temperaturaAdapter.ViewHolder> {
    List<temperatura> t;

    public temperaturaAdapter(List<temperatura> t) {
        this.t = t;
    }

    @NonNull
    @Override
    public temperaturaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull temperaturaAdapter.ViewHolder holder, int position) {
        String valor = t.get(position).getValue();
        holder.llenar(valor);
    }

    @Override
    public int getItemCount() {
        return t.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView val;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            val= itemView.findViewById(R.id.temperatura);
        }

        public void llenar(String valor) {
            val.setText(valor);
        }
    }
}
