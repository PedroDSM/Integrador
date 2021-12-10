package com.example.pokemonrv.Adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonrv.Modelos.Dist;
import com.example.pokemonrv.R;

import java.util.List;

public class DistAdapter extends RecyclerView.Adapter<DistAdapter.ViewHolder> {

    List<Dist> d;

    public DistAdapter(List<Dist> d) {
        this.d = d;
    }

    @NonNull
    @Override
    public DistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.distancia, parent, false);
        return new DistAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DistAdapter.ViewHolder holder, int position) {
        String valor = d.get(position).getValue();
        holder.llenar(valor);
    }

    @Override
    public int getItemCount() {
        return d.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView val;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            val= itemView.findViewById(R.id.distancia);
        }

        public void llenar(String valor) {
            val.setText(valor);
        }
    }
}
