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
import com.juannarvaez.taskworkout.model.entily.Nutricion;

import java.util.ArrayList;

public class NutricionAdapter extends RecyclerView.Adapter <NutricionAdapter.NutricionViewHolder>{

    private ArrayList<Nutricion> listado;

    private OnItemClickListener onItemClickListener;

    public void setListado(ArrayList<Nutricion> listado){
        this.listado = listado;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public  NutricionAdapter(ArrayList<Nutricion> listado){
        this.listado=listado;

    }

    public class NutricionViewHolder extends RecyclerView.ViewHolder {
        private TextView tituloNutricion;
        private ImageView imagenNutricion;

        public NutricionViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloNutricion =itemView.findViewById(R.id.titulo_nutricion);

            imagenNutricion = itemView.findViewById(R.id.image_nutricion);
        }

        public void  onBind(Nutricion miNutricion){
            tituloNutricion.setText(miNutricion.getTituloNutricion());

            Glide.with(itemView.getContext()).load(miNutricion.getImagenNutricion()).into(imagenNutricion);

            if(onItemClickListener != null){
                imagenNutricion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickImagen(miNutricion, getAdapterPosition());
                        //Toast.makeText(itemView.getContext(), "Imagen "+miNutricion.getTituloNutricion(), Toast.LENGTH_SHORT).show();
                    }
                });

                tituloNutricion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClickNombre(miNutricion, getAdapterPosition());
                        //Toast.makeText(itemView.getContext(), "Imagen "+miNutricion.getTituloNutricion(), Toast.LENGTH_SHORT).show();
                    }
                });

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(miNutricion, getAdapterPosition());
                        //Toast.makeText(itemView.getContext(), "Card "+miNutricion.getTituloNutricion(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }
    }


    @NonNull
    @Override
    public NutricionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View miVista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nutricion, parent, false);
        return  new NutricionViewHolder(miVista);
    }

    @Override
    public void onBindViewHolder(@NonNull NutricionViewHolder holder, int position) {
        Nutricion miNutricion = listado.get(position);
        NutricionViewHolder miHolder = (NutricionViewHolder) holder;
        miHolder.onBind(miNutricion);
    }

    @Override
    public int getItemCount() {
        return listado.size();
    }

    //inicio interfaz
    public interface OnItemClickListener{
        void onItemClick(Nutricion miNutricion, int posicion);
        void onItemClickImagen(Nutricion miNutricion, int posicion);
        void onItemClickNombre(Nutricion miNutricion, int posicion);
    }


}