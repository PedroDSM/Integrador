package com.example.pokemonrv.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonrv.Intents.Sonido;
import com.example.pokemonrv.Intents.Temperatura;
import com.example.pokemonrv.Modelos.Sonic;
import com.example.pokemonrv.Modelos.temperatura;
import com.example.pokemonrv.R;

import java.util.List;

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.ViewHolder>{
    List<Sonic> t;

    public SoundAdapter(List<Sonic> t) {
        this.t = t;
    }
    @NonNull
    @Override
    public SoundAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.son, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String valor = t.get(position).getvalue();
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
            val= itemView.findViewById(R.id.sonid);
        }

        public void llenar(String valor) {
            val.setText(valor);
        }
    }
}
