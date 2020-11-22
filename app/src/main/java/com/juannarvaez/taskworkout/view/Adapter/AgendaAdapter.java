package com.juannarvaez.taskworkout.view.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.entily.agenda;

import java.util.ArrayList;

public class AgendaAdapter extends RecyclerView.Adapter<AgendaAdapter.EjerciciosViewHolder> {

    private ArrayList<agenda> listado;
    private OnItemClickListener onItemClickListener;



    public void setListado(ArrayList<agenda> listado) {
        this.listado = listado;
        notifyDataSetChanged();
    }
    public AgendaAdapter(ArrayList<agenda> listado){
        this.listado= listado;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener= onItemClickListener;
    }

    class EjerciciosViewHolder extends RecyclerView.ViewHolder{
        private ImageButton bto_eliminar;

        private  TextView fechaAgenda, horaAgenda;
        public EjerciciosViewHolder(@NonNull View itemView){
            super(itemView);
            bto_eliminar = itemView.findViewById(R.id.eliminar);
            horaAgenda = itemView.findViewById(R.id.hora_Agenda);
            fechaAgenda = itemView.findViewById(R.id.fecha_agenda);


        }

        public void onBind(agenda miAgenda){
            horaAgenda.setText(miAgenda.getHora());
            fechaAgenda.setText(miAgenda.getFecha());


            if(onItemClickListener != null){
                horaAgenda.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickHora(miAgenda,getAdapterPosition());
                    }
                });
                fechaAgenda.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickhFecha(miAgenda,getAdapterPosition());
                    }
                });
                bto_eliminar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickhEliminar(miAgenda,getAdapterPosition());
                    }
                });
               itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       onItemClickListener.onItemClick(miAgenda,getAdapterPosition());
                   }
               });

            }
        }


    }
    @NonNull
    @Override
    public EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda,parent,false);
        return new EjerciciosViewHolder(miVista);
    }

    public void onBindViewHolder(@NonNull EjerciciosViewHolder holder, int position){
        agenda miagenda = listado.get(position);
        holder.onBind(miagenda);
    }
    public int getItemCount(){
        return listado.size();
    }
    public  interface OnItemClickListener{
        void onItemClick (agenda miAgenda, int posicion);
        void onItemClickHora (agenda miAgenda, int posicion);
        void onItemClickhFecha (agenda miAgenda, int posicion);
        void onItemClickhEliminar (agenda miAgenda, int posicion);
    }
}