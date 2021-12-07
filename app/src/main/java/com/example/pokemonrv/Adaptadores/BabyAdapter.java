package com.example.pokemonrv.Adaptadores;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonrv.Modelos.Funciones;
import com.example.pokemonrv.R;

import java.util.List;

public class BabyAdapter extends RecyclerView.Adapter<BabyAdapter.BabyH>{
    public BabyAdapter(List<Funciones> menu) {
        this.menu = menu;
    }

    public List<Funciones> menu;

    @NonNull
    @Override
    public BabyH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler,parent,false);
        BabyH Baby=new BabyH(v);
        return Baby;
    }

    @Override
    public void onBindViewHolder(@NonNull BabyH holder, int position) {
        holder.Nombre.setText(menu.get(position).getNombre());
        holder.imagen.setImageResource(menu.get(position).getImagen());
        holder.lista(menu.get(position));
    }

    @Override
    public int getItemCount() {
        return menu.size();
    }

    public class BabyH extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Nombre;
        ImageView imagen;
        Intent I;
        public BabyH(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            Nombre = (TextView) itemView.findViewById(R.id.Nombre);
            imagen = (ImageView) itemView.findViewById(R.id.imagen);
        }

        public void lista(Funciones intent) {

            Nombre.setText(intent.getNombre());
            I=intent.getIntent();
        }

        @Override
        public void onClick(View v) {
            v.getContext().startActivity(I);
        }
    }
}
