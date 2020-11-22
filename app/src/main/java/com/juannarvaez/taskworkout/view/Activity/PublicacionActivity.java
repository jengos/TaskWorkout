package com.juannarvaez.taskworkout.view.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.Repositorios.PublicacionRespositorio;
import com.juannarvaez.taskworkout.model.entily.Publicacion;
import com.juannarvaez.taskworkout.model.entily.Usuario;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PublicacionActivity extends AppCompatActivity {
private Usuario miUsuario;
private TextView nombrePublicacion;
private EditText DescripconPublicacion;
    String nombre;
    String fDate;
    Date fechaActual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publicacion);
        nombrePublicacion= findViewById(R.id.tv_nombreuser_publicacion);
        DescripconPublicacion=findViewById(R.id.multiTextViewPublicacion);
        miUsuario=(Usuario)getIntent().getSerializableExtra("Usuario");
        Log.d("paso",miUsuario.getNombre());
        nombre =miUsuario.getNombre();

        nombrePublicacion.setText(nombre);
      fechaActual = new Date();

        fDate = new SimpleDateFormat("dd'/'MMM ',' hh:mm aa", new Locale("es_ES")).format(fechaActual);
        Log.d("fecha",fDate);
    }


    public void publicarConsejo(View view) {
        String contenido = DescripconPublicacion.getText().toString();
        Publicacion miPublicacion = new Publicacion(miUsuario.getId(),contenido,fDate,miUsuario.getNombre(),fechaActual);
        PublicacionRespositorio publicacionRespositorio =new PublicacionRespositorio(PublicacionActivity.this);
        publicacionRespositorio.agregarpublicFS(miPublicacion, new TaskWorkoutCallBack<Boolean>() {
            @Override
            public void tareaCorrecta(Boolean respuesta) {
                Toast.makeText(PublicacionActivity.this, "Agregado", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void tareaError(Exception exception) {

            }
        });

    }
}