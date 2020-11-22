package com.juannarvaez.taskworkout.view.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.entily.agenda;


import java.util.ArrayList;

public class AgendaAdapter_Admin extends RecyclerView.Adapter <AgendaAdapter_Admin.agendaViewHolder> {
    private ArrayList<agenda> listado;
    private OnItemClickListener onItemClickListener;

    public void setListado(ArrayList<agenda> listado){
        this.listado = listado;
        notifyDataSetChanged();
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public  AgendaAdapter_Admin(ArrayList<agenda> listado){
        this.listado=listado;
    }



    public class agendaViewHolder extends RecyclerView.ViewHolder {
        private TextView nombreAdmin;
        private  TextView fechaAdmin;
        private TextView horaAdmin;


        public agendaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreAdmin=itemView.findViewById(R.id.nombre_adm);
            fechaAdmin=itemView.findViewById(R.id.fecha_agenda_adm);
            horaAdmin=itemView.findViewById(R.id.hora_Agenda_adm);
        }

        public void onBind(agenda miAgenda) {
            nombreAdmin.setText(miAgenda.getNombreUsuario());
            fechaAdmin.setText(miAgenda.getFecha());
            horaAdmin.setText(miAgenda.getHora());
            if(onItemClickListener != null){
                nombreAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickNombre(miAgenda, getAdapterPosition());
                    }
                });

                fechaAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickfecha(miAgenda, getAdapterPosition());
                    }
                });

                horaAdmin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickHora(miAgenda, getAdapterPosition());
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(miAgenda, getAdapterPosition());
                    }
                });


            }
        }

    }

    @NonNull
    @Override
    public agendaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_agenda_admin, parent, false);
        return  new agendaViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull agendaViewHolder holder, int position) {

        agenda miAgenda = listado.get(position);
        agendaViewHolder miHolder = (agendaViewHolder) holder;
        miHolder.onBind(miAgenda);
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    public interface OnItemClickListener{
        void onItemClick(agenda miAgenda, int posicion);
        void onItemClickNombre(agenda miAgenda, int posicion);
        void onItemClickfecha(agenda miAgenda, int posicion);
        void onItemClickHora(agenda miAgenda, int posicion);
    }


}
