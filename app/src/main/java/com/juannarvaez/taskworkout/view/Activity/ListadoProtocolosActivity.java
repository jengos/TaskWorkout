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
import com.juannarvaez.taskworkout.model.Repositorios.ProcolosRepositorio;
import com.juannarvaez.taskworkout.model.entily.Protocolos;
import com.juannarvaez.taskworkout.view.Adapter.ProtocolosAdapter;

import java.util.ArrayList;

public class ListadoProtocolosActivity extends AppCompatActivity {
    private RecyclerView listadoProtocolos;
    private ArrayList<Protocolos> listaProtocolos;
    private FirebaseFirestore firestore;
    private ProtocolosAdapter miAdaptar;
    private FirebaseAuth auth;
    private ProcolosRepositorio procolosRepositorio;
    private static final int CODIGO_DETALLE_PROTOCOLO = 111 ;

    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(ListadoProtocolosActivity.this, InicioActivity.class);
            startActivity(intent);
            finish();
        }
        actualizarListado();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_protocolos);
        firestore= FirebaseFirestore.getInstance();
        listaProtocolos=new ArrayList<>();
        procolosRepositorio = new ProcolosRepositorio(ListadoProtocolosActivity.this);

        inizialice();
        cargarDatos();

        miAdaptar = new ProtocolosAdapter(listaProtocolos);
        listadoProtocolos.setAdapter(miAdaptar);
        configurarListado();

    }

    private void actualizarListado() {
        procolosRepositorio= new ProcolosRepositorio(ListadoProtocolosActivity.this);
        procolosRepositorio.obtenerTodosProtocolosFS(new TaskWorkoutCallBack<ArrayList<Protocolos>>() {

            @Override
            public void tareaCorrecta(ArrayList<Protocolos> respuesta) {
                miAdaptar.setListado(respuesta);
            }

            @Override
            public void tareaError(Exception exception) {
                Toast.makeText(ListadoProtocolosActivity.this, "ERROR al traer datos ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarListado() {
        listadoProtocolos.setLayoutManager(new LinearLayoutManager(ListadoProtocolosActivity.this));
        listadoProtocolos.setHasFixedSize(true);
        miAdaptar.setOnItemClickListener(new ProtocolosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Protocolos miProtocolo, int posicion) {
                Toast.makeText(ListadoProtocolosActivity.this, miProtocolo.getTituloProtocolo(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListadoProtocolosActivity.this,  DetalleProtocoloActivity.class);
                intent.putExtra("protocolo", miProtocolo);
                startActivityForResult(intent, CODIGO_DETALLE_PROTOCOLO);
            }

            @Override
            public void onItemClickNombre(Protocolos miProtocolo, int posicion) {
                Toast.makeText(ListadoProtocolosActivity.this, miProtocolo.getTituloProtocolo(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListadoProtocolosActivity.this,  DetalleProtocoloActivity.class);
                intent.putExtra("protocolo", miProtocolo);
                startActivityForResult(intent, CODIGO_DETALLE_PROTOCOLO);
            }
        });
    }

    private void cargarDatos() {
        firestore.collection("protocolo").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                listaProtocolos.clear();
                if(task.isSuccessful()){
                    for(DocumentSnapshot item: task.getResult().getDocuments()){
                        Log.d("CARGAR DATOS...", item.getData().toString());
                        Protocolos miProtocolo = item.toObject(Protocolos.class);
                        listaProtocolos.add(miProtocolo);
                    }
                    miAdaptar.setListado(listaProtocolos);
                }else{
                    Toast.makeText(ListadoProtocolosActivity.this, "ERROR al traer los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void inizialice() {
        listadoProtocolos = (RecyclerView)findViewById(R.id.RecicleProtocolo);
    }
}