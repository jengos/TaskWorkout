package com.juannarvaez.taskworkout.view.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.Repositorios.NutricionRepositorio;
import com.juannarvaez.taskworkout.model.entily.Nutricion;
import com.juannarvaez.taskworkout.view.Adapter.NutricionAdapter;

import java.util.ArrayList;

public class ListadoNutricionActivity extends AppCompatActivity {
    private RecyclerView listadoNutricion;
    private ArrayList<Nutricion> listaNutricion;
    private FirebaseFirestore firestore;
    private NutricionAdapter miAdaptar;
    private FirebaseAuth auth;
    private NutricionRepositorio nutricionRepositorio;
    private static final int CODIGO_DETALLE_NUTRICION = 111 ;


    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(ListadoNutricionActivity.this, InicioActivity.class);
            startActivity(intent);
            finish();
        }
        actualizarListado();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_nutricion);
        firestore= FirebaseFirestore.getInstance();
        listaNutricion=new ArrayList<>();
        nutricionRepositorio = new NutricionRepositorio(ListadoNutricionActivity.this);

        inizialice();
        cargarDatos();

        miAdaptar = new NutricionAdapter(listaNutricion);
        listadoNutricion.setAdapter(miAdaptar);
        configurarListado();
        // actualizarListado();
    }

    private void actualizarListado() {
        nutricionRepositorio = new NutricionRepositorio(ListadoNutricionActivity.this);
        nutricionRepositorio.obtenerTodosFS(new TaskWorkoutCallBack<ArrayList<Nutricion>>() {

            @Override
            public void tareaCorrecta(ArrayList<Nutricion> respuesta) {
                miAdaptar.setListado(respuesta);
            }

            @Override
            public void tareaError(Exception exception) {
                Toast.makeText(ListadoNutricionActivity.this, "ERROR al traer datos ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarListado() {
        listadoNutricion.setLayoutManager(new LinearLayoutManager(ListadoNutricionActivity.this));
        listadoNutricion.setHasFixedSize(true);
        miAdaptar.setOnItemClickListener(new NutricionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Nutricion miNutricion, int posicion) {
                Toast.makeText(ListadoNutricionActivity.this, miNutricion.getTituloNutricion(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListadoNutricionActivity.this,  DetalleNutricionActivity.class);
                intent.putExtra("nutricion", miNutricion);
                startActivityForResult(intent, CODIGO_DETALLE_NUTRICION);
            }

            @Override
            public void onItemClickImagen(Nutricion miNutricion, int posicion) {
                Toast.makeText(ListadoNutricionActivity.this, miNutricion.getTituloNutricion(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListadoNutricionActivity.this,  DetalleNutricionActivity.class);
                intent.putExtra("nutricion", miNutricion);
                startActivityForResult(intent, CODIGO_DETALLE_NUTRICION);
            }

            @Override
            public void onItemClickNombre(Nutricion miNutricion, int posicion) {
                Toast.makeText(ListadoNutricionActivity.this, miNutricion.getTituloNutricion(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListadoNutricionActivity.this,  DetalleNutricionActivity.class);
                intent.putExtra("nutricion", miNutricion);
                startActivityForResult(intent, CODIGO_DETALLE_NUTRICION);
            }
        });
    }

    private void cargarDatos(){
        firestore.collection("nutricion").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                listaNutricion.clear();
                if(task.isSuccessful()){
                    for(DocumentSnapshot item: task.getResult().getDocuments()){
                        Log.d("CARGAR DATOS...", item.getData().toString());
                        Nutricion miNutricion = item.toObject(Nutricion.class);
                        listaNutricion.add(miNutricion);
                    }
                    miAdaptar.setListado(listaNutricion);
                }else{
                    Toast.makeText(ListadoNutricionActivity.this, "ERROR al traer los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void inizialice() {
        listadoNutricion = (RecyclerView)findViewById(R.id.Recicle);
    }
}