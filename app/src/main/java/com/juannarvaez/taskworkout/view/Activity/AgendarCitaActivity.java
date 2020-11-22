package com.juannarvaez.taskworkout.view.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.Repositorios.AgendaRepository;
import com.juannarvaez.taskworkout.model.Repositorios.UsuarioReposytory;
import com.juannarvaez.taskworkout.model.entily.Usuario;
import com.juannarvaez.taskworkout.model.entily.agenda;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AgendarCitaActivity extends AppCompatActivity {
    private EditText et_fecha,et_Hora;

   private Usuario miUsario;
    int year ;
    int mes ;
    int dia;
    int horaDia;
    int min;
    agenda miAgenda;
    Date fedes;
    Calendar calendar;
    UsuarioReposytory usuarioReposytory;
   FirebaseAuth auth;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth= FirebaseAuth.getInstance();
        setContentView(R.layout.activity_agendar_cita);
        et_fecha= findViewById(R.id.et_Calendario);
        et_Hora= findViewById(R.id.et_hora);

        String id= auth.getUid();
        firestore = FirebaseFirestore.getInstance();
        Log.d("Prueba",id);

        firestore.collection("usuarios").document(id).get().addOnCompleteListener(
                new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful());
                        if(task.getResult() !=null){
                            miUsario = task.getResult().toObject(Usuario.class);
                            Log.e("Usuario", miUsario.getNombre()+miUsario.getId());
                        }

                    }
                }
        );


    }

    public void abrirCalentario(View view) {


       calendar =Calendar.getInstance();
        year =calendar.get(Calendar.YEAR);
        mes =calendar.get(Calendar.MONTH);
        dia =calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(AgendarCitaActivity.this,  new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
               // String fecha = dayOfMonth+"/"+(month+1)+"/"+year;
                calendar.set(year,month,dayOfMonth);
               fedes= calendar.getTime();
                String FPUED= DateFormat.getDateInstance(DateFormat.FULL).format(fedes);
                et_fecha.setText(FPUED);


            }
        },year,mes,dia);
        datePickerDialog.show();

    }

    public void abrirHora(View view) {
        Calendar horaymin = Calendar.getInstance();
         horaDia = horaymin.get(Calendar.HOUR_OF_DAY);
        min= horaymin.get(Calendar.MINUTE);
        TimePickerDialog  timerTimePickerDialog = new TimePickerDialog(AgendarCitaActivity.this,new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horaymin.set(Calendar.HOUR,hourOfDay);
                horaymin.set(Calendar.MINUTE,minute);

                SimpleDateFormat Hora= new SimpleDateFormat("hh:mm ");
                String hora = Hora.format(horaymin.getTime());

                et_Hora.setText(hora);

            }
        }, horaDia, min,false);
        timerTimePickerDialog.show();
    }

    public void agregarAgenda(View view) {
        String Nombre = miUsario.getNombre();
        String hora = et_Hora.getText().toString();
        String fecha = et_fecha.getText().toString();
        FirebaseAuth auth = FirebaseAuth.getInstance();

         miAgenda = new agenda(fecha,hora,auth.getUid(),Nombre,fedes);


        AgendaRepository agendaRepository = new AgendaRepository(AgendarCitaActivity.this);
        agendaRepository.agregarAgendaFS(miAgenda, new
                TaskWorkoutCallBack<Boolean>() {
                    @Override
                    public void tareaCorrecta(Boolean respuesta) {

                        Toast.makeText(AgendarCitaActivity.this, "Agregada", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void tareaError(Exception exception) {
                        Toast.makeText(AgendarCitaActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}