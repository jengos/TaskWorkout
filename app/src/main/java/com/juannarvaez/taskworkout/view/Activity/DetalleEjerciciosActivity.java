package com.juannarvaez.taskworkout.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.Repositorios.EjercicioRepository;
import com.juannarvaez.taskworkout.model.entily.Ejercicio;

public class DetalleEjerciciosActivity extends AppCompatActivity {
    private TextView tv_nomEjercicio,tv_despEjercicio ;
    private ImageView iv_imgEjercicio;
    private Ejercicio miEjercicio;
    private EjercicioRepository ejercicioRepository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_ejercicios);
        asociarElementos();
        ejercicioRepository = new EjercicioRepository(DetalleEjerciciosActivity.this);
        miEjercicio=(Ejercicio) getIntent().getSerializableExtra("ejercicios");
        CargarDatos(miEjercicio);

    }

    private void CargarDatos(Ejercicio miEjercicio) {
        tv_despEjercicio.setText(miEjercicio.getDescripcionEjercicio());
        tv_nomEjercicio.setText(miEjercicio.getTituloEjercicio());
        Glide.with(DetalleEjerciciosActivity.this).load(miEjercicio.getImagenEjercicio()).into(iv_imgEjercicio);
    }

    private void asociarElementos() {
        tv_nomEjercicio=findViewById(R.id.tv_Titulo_ejercicios_detalle);
        tv_despEjercicio=findViewById(R.id.tv_descripcion);
        iv_imgEjercicio = findViewById(R.id.iv_ejercicio_imagen_descrip);
    }
}