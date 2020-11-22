package com.juannarvaez.taskworkout.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.Repositorios.NutricionRepositorio;
import com.juannarvaez.taskworkout.model.entily.Nutricion;

public class DetalleNutricionActivity extends AppCompatActivity {

    private TextView nombreNutricion;
    private ImageView imagenNutricion;
    private  TextView descripcionNutricion;
    private  TextView urlNutricion;
    private Nutricion miNutricion;
    private NutricionRepositorio nutricionRepositorio;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_nutricion);
        nutricionRepositorio= new NutricionRepositorio(DetalleNutricionActivity.this);
        miNutricion = (Nutricion)getIntent().getSerializableExtra("nutricion");
        asociarElementos();
        cargarDatos(miNutricion);
    }

    private void cargarDatos(Nutricion miNutricion) {

        nombreNutricion.setText(miNutricion.getTituloNutricion());
        descripcionNutricion.setText(miNutricion.getDescripcionNutricion());
        urlNutricion.setText(miNutricion.getUrlNutricion());
        Glide.with(DetalleNutricionActivity.this).load(miNutricion.getImagenNutricion()).into(imagenNutricion);

    }

    private void asociarElementos() {
        nombreNutricion=  findViewById(R.id.nombre_nutricion_detalle);
        descripcionNutricion = findViewById(R.id.descripcion_nutricion_detalle);
        urlNutricion =  findViewById(R.id.url_nutricion_detalle);
        imagenNutricion = findViewById(R.id.img_nutricion_detalle);
    }
}