package com.juannarvaez.taskworkout.view.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.entily.Publicacion;
import com.juannarvaez.taskworkout.model.entily.agenda;

import java.util.ArrayList;

public class ConsejosAdapter extends RecyclerView.Adapter<ConsejosAdapter.EjerciciosViewHolder> {

    private ArrayList<Publicacion> listado;
    private OnItemClickListener onItemClickListener;



    public void setListado(ArrayList<Publicacion> listado) {
        this.listado = listado;
        notifyDataSetChanged();
    }
    public ConsejosAdapter(ArrayList<Publicacion> listado){
        this.listado= listado;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener= onItemClickListener;
    }

    class EjerciciosViewHolder extends RecyclerView.ViewHolder{
        private TextView NombreUsuario;
        private TextView ContenidoPublicacion;
        private  TextView fechaPublicacion;
        public EjerciciosViewHolder(@NonNull View itemView){
            super(itemView);
            NombreUsuario= itemView.findViewById(R.id.NombreUsuario_consejo);
            ContenidoPublicacion= itemView.findViewById(R.id.contenido_consejo);
            fechaPublicacion = itemView.findViewById(R.id.fecha_publica);



        }

        public void onBind(Publicacion miPublicacion){
            NombreUsuario.setText(miPublicacion.getNombre_usuario());
            ContenidoPublicacion.setText(miPublicacion.getCuerpo());
            fechaPublicacion.setText(miPublicacion.getFechaActual());
            if(onItemClickListener != null){
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(miPublicacion,getAdapterPosition());
                    }
                });
            }



            }
        }



    @NonNull
    @Override
    public EjerciciosViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType){
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consejo,parent,false);
        return new EjerciciosViewHolder(miVista);
    }

    public void onBindViewHolder(@NonNull EjerciciosViewHolder holder, int position){
        Publicacion miPublicacion = listado.get(position);
        holder.onBind(miPublicacion);
    }
    public int getItemCount(){
        return listado.size();
    }
    public  interface OnItemClickListener{
        void onItemClick (Publicacion miPublicacion, int posicion);


    }
}