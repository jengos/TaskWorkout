package com.juannarvaez.taskworkout.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.entily.SeguimientoPeso;
import com.juannarvaez.taskworkout.model.entily.Usuario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

public class PerfilActivity extends AppCompatActivity {
    private EditText et_nombre;
    private EditText et_correo;
    private EditText et_fechaCumple;
    private EditText et_telefono;
    private EditText et_peso;
    private EditText et_altura;
    private EditText et_imc;
    private EditText et_pesoIdeal;
    private EditText et_genero;

    Calendar calendar;
    String fecha;
    String rol;
    FirebaseAuth auth;
    private Usuario miUsuario;
    FirebaseFirestore firestore;
    DecimalFormat formater = new DecimalFormat("#.##");

    public void onStart() {
        super.onStart();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        asociarElememtos();
        editarCargarDatos();


    }

    private void editarCargarDatos() {

        firestore.collection("usuarios").document(auth.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    miUsuario.setNombre(documentSnapshot.getString("nombre"));
                    miUsuario.setCorreo(documentSnapshot.getString("correo"));
                    miUsuario.setGenero(documentSnapshot.getString("genero"));
                    miUsuario.setTelefono(documentSnapshot.getString("telefono"));
                    miUsuario.setAltura(documentSnapshot.getDouble("altura"));
                    miUsuario.setPeso(documentSnapshot.getDouble("peso"));
                    miUsuario.setFechaCumple(documentSnapshot.getString("fechaCumple"));
                    miUsuario.setRol(documentSnapshot.getString("rol"));
                    rol = miUsuario.getRol();

                    et_nombre.setText(miUsuario.getNombre());
                    et_correo.setText(miUsuario.getCorreo());
                    et_genero.setText(miUsuario.getGenero());
                    et_fechaCumple.setText(miUsuario.getFechaCumple());
                    if (miUsuario.getGenero().equalsIgnoreCase("")) {
                        et_genero.setEnabled(true);

                    }
                    et_telefono.setText(miUsuario.getTelefono());
                    et_altura.setText("" + miUsuario.getAltura());
                    et_peso.setText("" + miUsuario.getPeso());
                    if ((miUsuario.getAltura() != 0) && (miUsuario.getPeso() != 0)) {
                        double altura = (miUsuario.getAltura() / 100);
                        double a = (miUsuario.getPeso() / (Math.pow((miUsuario.getAltura() / 100), 2)));
                        BigDecimal bigDecimal = new BigDecimal(a).setScale(2, RoundingMode.UP);
                        et_imc.setText("" + bigDecimal.doubleValue());
                    } else {
                        et_imc.setText("");
                    }
                    if (miUsuario.getGenero() != null) {
                        if (miUsuario.getGenero().equalsIgnoreCase("Hombre")) {
                            et_pesoIdeal.setText("" + (0.75 * (miUsuario.getAltura()) - 62.5));
                        } else {
                            if (miUsuario.getGenero().equalsIgnoreCase("Mujer")) {
                                et_pesoIdeal.setText("" + (0.67 * (miUsuario.getAltura()) - 52));
                            } else {
                                if (miUsuario.getGenero().equalsIgnoreCase("")) {
                                    et_pesoIdeal.setText("");
                                }
                            }
                        }
                    } else {
                        et_pesoIdeal.setText("");
                    }
                }
            }
        });





    }

    private void asociarElememtos() {
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();


        miUsuario = new Usuario();
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        String fecha = dia + "/" + (mes + 1) + "/" + year;

        et_nombre = findViewById(R.id.et_nombre_Perfil);
        et_genero = findViewById(R.id.et_genero_Perfil);
        et_telefono = findViewById(R.id.et_telefono_Perfil);
        et_altura = findViewById(R.id.et_altura_Perfil);
        et_peso = findViewById(R.id.et_peso_Perfil);
        et_imc = findViewById(R.id.et_imcActual_Perfil);
        et_pesoIdeal = findViewById(R.id.et_pesoideal_Perfil);
        et_correo = findViewById(R.id.et_corre_Perfil);
        et_fechaCumple = findViewById(R.id.et_cumple_Perfil);
        et_fechaCumple.setText(fecha);


    }

    public void ingresarFechacumple(View view) {
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(PerfilActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String fecha = dayOfMonth + "/" + (month + 1) + "/" + year;
                et_fechaCumple.setText(fecha);
            }
        }, year, mes, dia);
        datePickerDialog.show();
    }

    public void EditarPerfil(View view) {
        SeguimientoPeso miSeguimientoPeso = new SeguimientoPeso();
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(year,mes,dia);
       Date fedes= calendar.getTime();
        String FPUED= DateFormat.getDateInstance(DateFormat.FULL).format(fedes);

        miUsuario.setNombre(et_nombre.getText().toString().trim());
        miUsuario.setGenero(et_genero.getText().toString().trim());
        miUsuario.setFechaCumple(et_fechaCumple.getText().toString().trim());
        miUsuario.setCorreo(et_correo.getText().toString().trim());
        miUsuario.setTelefono(et_telefono.getText().toString().trim());
        miUsuario.setPeso(Double.parseDouble(et_peso.getText().toString().trim()));
        miUsuario.setAltura(Double.parseDouble(et_altura.getText().toString().trim()));
        miUsuario.setPeso(Double.parseDouble(et_peso.getText().toString().trim()));
        miUsuario.setId(auth.getUid());
        miUsuario.setRol(rol);
        miSeguimientoPeso.setPeso(Double.parseDouble(et_peso.getText().toString().trim()));
        miSeguimientoPeso.setIMC(Double.parseDouble(et_imc.getText().toString().trim()));
        miSeguimientoPeso.setFecha(FPUED);
        miSeguimientoPeso.setAltura(Double.parseDouble(et_altura.getText().toString().trim()));
        miSeguimientoPeso.setFechaDate(fedes);
        firestore.collection("usuarios").document(auth.getUid()).set(miUsuario);

        firestore.collection("usuarios").document(auth.getUid()).collection("segimiento_peso").document(auth.getUid()).set(miSeguimientoPeso);

        finish();

    }
}