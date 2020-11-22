package com.juannarvaez.taskworkout.view.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.Repositorios.AgendaRepository;
import com.juannarvaez.taskworkout.model.entily.agenda;
import com.juannarvaez.taskworkout.view.Adapter.AgendaAdapter_Admin;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ListadoAgendaAdminActivity extends AppCompatActivity {
    private TextView mostrarFecha;
    private Button buscarFechadia;
    private RecyclerView listadoAdmin;
    private ArrayList<agenda> listaAgenda;
    private FirebaseFirestore firestore;
    private AgendaAdapter_Admin miAdaptar;
    private FirebaseAuth auth;
    private AgendaRepository agendaRepository;
    private static final int CODIGO_DETALLE_ADMIN = 111 ;
    agenda agen;
    Date fedes;
    String fechaNueva ;
    @Override
    protected void onStart() {
        super.onStart();
        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(ListadoAgendaAdminActivity.this, InicioActivity.class);
            startActivity(intent);
            finish();
        }
        actualizarListado();
    }

    private void actualizarListado() {

        agendaRepository= new AgendaRepository(ListadoAgendaAdminActivity.this);
        agendaRepository.obtenerTodosAgendadosFS(new TaskWorkoutCallBack<ArrayList<agenda>>() {

            @Override
            public void tareaCorrecta(ArrayList<agenda> respuesta) {
                miAdaptar.setListado(respuesta);
            }

            @Override
            public void tareaError(Exception exception) {
                Toast.makeText(ListadoAgendaAdminActivity.this, "ERROR al traer datos ", Toast.LENGTH_SHORT).show();
            }
        });


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_agenda);
        mostrarFecha = findViewById(R.id.mostrar_fecha_agenda);
        buscarFechadia=findViewById(R.id.buscar_dia_agenda);
        firestore= FirebaseFirestore.getInstance();
        listaAgenda=new ArrayList<>();
        agendaRepository = new AgendaRepository(ListadoAgendaAdminActivity.this);

        inizialice();
        cargarDatos();

        buscarFechaDia();

        miAdaptar = new AgendaAdapter_Admin(listaAgenda);
        listadoAdmin.setAdapter(miAdaptar);
        configurarListado();



    }

    public void buscarFechaDia(){
        buscarFechadia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fechaMostrar = mostrarFecha.getText().toString();
                firestore.collection("agenda").whereEqualTo("fecha", fechaMostrar).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            listaAgenda.clear();
                            for(DocumentSnapshot item: task.getResult().getDocuments()){
                                Log.d("CARGAR DATOS...", item.getData().toString());
                                agenda miAgenda = item.toObject(agenda.class);
                                listaAgenda.add(miAgenda);
                            }
                            miAdaptar.setListado(listaAgenda);
                        }
                    }
                });

            }
        });
    }

    private void configurarListado() {
        listadoAdmin.setLayoutManager(new LinearLayoutManager(ListadoAgendaAdminActivity.this));
        listadoAdmin.setHasFixedSize(true);
        miAdaptar.setOnItemClickListener(new AgendaAdapter_Admin.OnItemClickListener() {
            @Override
            public void onItemClick(agenda miAgenda, int posicion) {
                Toast.makeText(ListadoAgendaAdminActivity.this, miAgenda.getNombreUsuario(), Toast.LENGTH_SHORT).show();
               /* Intent intent = new Intent(ListadoAgendaAdminActivity.this, getClass());
                intent.putExtra("agenda", miAgenda);
                startActivityForResult(intent, CODIGO_DETALLE_ADMIN);*/
            }

            @Override
            public void onItemClickNombre(agenda miAgenda, int posicion) {
                Toast.makeText(ListadoAgendaAdminActivity.this, miAgenda.getNombreUsuario(), Toast.LENGTH_SHORT).show();
                /*Intent intent = new Intent(ListadoAgendaAdminActivity.this,  getClass());
                intent.putExtra("agenda", miAgenda);
                startActivityForResult(intent, CODIGO_DETALLE_ADMIN);*/
            }

            @Override
            public void onItemClickHora(agenda miAgenda, int posicion) {
               /* Toast.makeText(ListadoAgendaAdminActivity.this, miAgenda.getHora(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListadoAgendaAdminActivity.this,  getClass());
                intent.putExtra("agenda", miAgenda);
                startActivityForResult(intent, CODIGO_DETALLE_ADMIN);*/
            }

            @Override
            public void onItemClickfecha(agenda miAgenda, int posicion) {
                Toast.makeText(ListadoAgendaAdminActivity.this, miAgenda.getFecha(), Toast.LENGTH_SHORT).show();
               /* Intent intent = new Intent(ListadoAgendaAdminActivity.this,  DetalleProtocoloActivity.class);
                intent.putExtra("agenda", miAgenda);
                startActivityForResult(intent, CODIGO_DETALLE_ADMIN);*/
            }
        });
    }

    private void cargarDatos() {
        auth = FirebaseAuth.getInstance();

        firestore.collection("agenda").whereEqualTo("fechaTime", fechaNueva).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error==null){
                    for(DocumentSnapshot item: value.getDocuments()){
                        Log.d("CARGAR DATOS...", item.getData().toString());
                        agenda miAgenda = item.toObject(agenda.class);
                        listaAgenda.add(miAgenda);
                    }
                    miAdaptar.setListado(listaAgenda);
                }
            }
        });
    }

    private void inizialice() {
        listadoAdmin = (RecyclerView)findViewById(R.id.RecicleAgendaAdmin);
    }

    public void fecha(){
        Calendar calendar =Calendar.getInstance();
        int year =calendar.get(Calendar.YEAR);
        int mes =calendar.get(Calendar.MONTH);
        int dia =calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(ListadoAgendaAdminActivity.this,  new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth+"/"+month+"/"+year;
                calendar.set(year, month, dayOfMonth);
                fedes=calendar.getTime();
                fechaNueva = DateFormat.getDateInstance(DateFormat.FULL).format(fedes);
                mostrarFecha.setText(fechaNueva);
            }
        },year,mes,dia);
        datePickerDialog.show();

    }

    public void mostrarDetalleAgenda(View view) {

        fecha();
    }
}