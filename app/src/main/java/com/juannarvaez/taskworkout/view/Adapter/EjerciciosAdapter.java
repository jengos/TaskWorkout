package com.juannarvaez.taskworkout.view.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.entily.Ejercicio;

import java.util.ArrayList;

public class EjerciciosAdapter extends RecyclerView.Adapter<EjerciciosAdapter.EjerciciosViewHolder> {

    private ArrayList<Ejercicio> listado;
    private OnItemClickListener onItemClickListener;



    public void setListado(ArrayList<Ejercicio> listado) {
        this.listado = listado;
        notifyDataSetChanged();
    }
    public EjerciciosAdapter(ArrayList<Ejercicio> listado){
        this.listado= listado;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener= onItemClickListener;
    }

    class EjerciciosViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivEjercicios;
        private  TextView tvTitulo;
        public EjerciciosViewHolder(@NonNull View itemView){
            super(itemView);
            ivEjercicios = itemView.findViewById(R.id.iv_fotoEjercicios);
            tvTitulo = itemView.findViewById(R.id.tv_Titulo_ejercicios);

        }

        public void onBind(Ejercicio miEjercicio){
            tvTitulo.setText(miEjercicio.getTituloEjercicio());
            Glide .with(itemView.getContext()).load(miEjercicio.getImagenEjercicio()).into(ivEjercicios);

            if(onItemClickListener != null){
                ivEjercicios.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(miEjercicio, getAdapterPosition());
                    }
                });
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(miEjercicio,getAdapterPosition());
                    }
                });

            }
        }


    }
    @NonNull
    @Override
    public EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ejercicios,parent,false);
        return new EjerciciosViewHolder(miVista);
    }

    public void onBindViewHolder(@NonNull EjerciciosViewHolder holder, int position){
        Ejercicio miEjercicio = listado.get(position);
        holder.onBind(miEjercicio);
    }
    public int getItemCount(){
        return listado.size();
    }
    public  interface OnItemClickListener{
        void onItemClick (Ejercicio miEjercicio, int posicion);
        void onItemClickImagen (Ejercicio miEjercicio, int posicion);
    }
}