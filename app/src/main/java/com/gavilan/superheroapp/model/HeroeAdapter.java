package com.gavilan.superheroapp.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gavilan.superheroapp.R;

import java.util.ArrayList;

public class HeroeAdapter extends RecyclerView.Adapter<HeroeAdapter.ViewHolderDatos>  {

    ArrayList<Heroe> ListaHeroes; // lista que queremos cargar
    public HeroeAdapter(ArrayList<Heroe> heroes){
        ListaHeroes =  heroes;
    }

    @NonNull
    @Override
    public HeroeAdapter.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_heroe,null,false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroeAdapter.ViewHolderDatos holder, int position) {
        holder.CargarHeroe(ListaHeroes.get(position));

    }

    @Override
    public int getItemCount() {
        return ListaHeroes.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView txtNombreHeroe;
        ImageView imgHeroe;
        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            txtNombreHeroe = itemView.findViewById(R.id.txtNombreHeroe);
            imgHeroe = itemView.findViewById(R.id.imgHeroe);
        }
        void CargarHeroe(Heroe heroe){
            txtNombreHeroe.setText(heroe.getNombreHeroe());

            switch ( heroe.getMundo() ){
                case "Marvel":
                    imgHeroe.setImageResource(R.drawable.marvel);
                    break;
                case "DC":
                    imgHeroe.setImageResource(R.drawable.dc);
                    break;
            }

        }


    }
}
