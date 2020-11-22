package com.juannarvaez.taskworkout.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.juannarvaez.taskworkout.view.Adapter.EjerciciosAdapter;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.Repositorios.EjercicioRepository;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.entily.Ejercicio;

import java.util.ArrayList;

public class CargarEjerciciosCalentamientoActivity extends AppCompatActivity {
      private ArrayList<Ejercicio> ListaEjer;
    private EjercicioRepository ejercicioRepository;
    private EjerciciosAdapter miAdapter;
    private RecyclerView rvlistadoEjercicios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar_ejercicios_calentamiento);

        ListaEjer = new ArrayList<>();
        asociarElementos();
        ejercicioRepository = new EjercicioRepository(CargarEjerciciosCalentamientoActivity.this);
        miAdapter=new EjerciciosAdapter(ListaEjer);
        configuarListado();
        actualizarListado();


    }

    private void configuarListado() {
        rvlistadoEjercicios .setLayoutManager(new LinearLayoutManager(CargarEjerciciosCalentamientoActivity.this));
        rvlistadoEjercicios.setHasFixedSize(true);
        miAdapter.setOnItemClickListener(new EjerciciosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Ejercicio miEjercicio, int posicion) {
                Toast.makeText(CargarEjerciciosCalentamientoActivity.this, "Car"+ miEjercicio.getTituloEjercicio(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CargarEjerciciosCalentamientoActivity.this, DetalleEjerciciosActivity.class);
                intent.putExtra("ejercicios",miEjercicio);
                startActivity(intent);
            }

            @Override
            public void onItemClickImagen(Ejercicio miEjercicio, int posicion) {
                Toast.makeText(CargarEjerciciosCalentamientoActivity.this, "Imagen "+miEjercicio.getTituloEjercicio(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void asociarElementos() {
        rvlistadoEjercicios = findViewById(R.id.rv_ejercicios);
    }
    private  void actualizarListado(){
        ejercicioRepository.obtenerTodosFSCalentamiento(new TaskWorkoutCallBack<ArrayList<Ejercicio>>() {
            @Override
            public void tareaCorrecta(ArrayList<Ejercicio> respuesta) {
                miAdapter.setListado(respuesta);
            }

            @Override
            public void tareaError(Exception exception) {
                Toast.makeText(CargarEjerciciosCalentamientoActivity.this, "ERROR ", Toast.LENGTH_SHORT).show();
            }
        });
        rvlistadoEjercicios.setAdapter(miAdapter);
    }
}