package com.juannarvaez.taskworkout.view.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.entily.Protocolos;

import java.util.ArrayList;


public class ProtocolosAdapter extends RecyclerView.Adapter <ProtocolosAdapter.ProtocolosViewHolder> {
    private ArrayList<Protocolos> listado;
    private OnItemClickListener onItemClickListener;

    public void setListado(ArrayList<Protocolos> listado){
        this.listado = listado;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(ProtocolosAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public  ProtocolosAdapter(ArrayList<Protocolos> listado){
        this.listado=listado;
    }

    public class ProtocolosViewHolder extends RecyclerView.ViewHolder {
        private TextView tituloProtocolo;

        public ProtocolosViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloProtocolo =itemView.findViewById(R.id.titulo_protocolos);
        }


        public void onBind(Protocolos miProtocolo) {
            tituloProtocolo.setText(miProtocolo.getTituloProtocolo());
            if(onItemClickListener != null){
                tituloProtocolo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickNombre(miProtocolo, getAdapterPosition());
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(miProtocolo, getAdapterPosition());
                        //Toast.makeText(itemView.getContext(), "Card "+miNutricion.getTituloNutricion(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    @NonNull
    @Override
    public ProtocolosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_protocolos, parent, false);
        return  new ProtocolosViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull ProtocolosViewHolder holder, int position) {
        Protocolos miProtocolo = listado.get(position);
        ProtocolosViewHolder miHolder = (ProtocolosViewHolder) holder;
        miHolder.onBind(miProtocolo);
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    //inicio interfaz
    public interface OnItemClickListener{
        void onItemClick(Protocolos miProtocolo, int posicion);
        void onItemClickNombre(Protocolos miProtocolo, int posicion);
    }


}