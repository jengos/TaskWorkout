package com.juannarvaez.taskworkout.view.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.FirebaseFirestore;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.entily.Usuario;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {
    //defining view objects
    private String nombre;
    private String contraseña;
    private String telefono;
    private double peso;
    private String email, genero;
    private double estatura;
    private String validarContraseña;
    private EditText Nombre;
    private EditText RegEmail;
    private EditText RegPassword;
    private EditText Regvalidar_password;
    private EditText Regtelefono,etgenero;;
    private EditText Regpeso;
    private EditText Regestatura;

    private Spinner listGenero;
    private Button RegCrearCuenta;
    private ProgressDialog RegprogressDialog;
     String vectorGenero[] = new String[]{"Hombre", "Mujer", "No quiero decirlo"};

    //Declaramos un objeto firebaseAuth
    private FirebaseAuth firebaseAuth;
    // databaseRealTime
    private FirebaseFirestore firestore;

    private void inacialize() {


        firestore = FirebaseFirestore.getInstance();
        Nombre = (EditText) findViewById(R.id.formulario_Nombre);
        RegEmail = (EditText) findViewById(R.id.formulario_Correo);
        RegPassword = (EditText) findViewById(R.id.formulario_Password);
        Regvalidar_password = (EditText) findViewById(R.id.formulario_VerificaPassword);
        Regtelefono = (EditText) findViewById(R.id.formulario_Telefono);
        Regpeso = (EditText) findViewById(R.id.formulario_Peso);
        Regestatura = (EditText) findViewById(R.id.formulario_Altura);
        listGenero=findViewById(R.id.lV_genero);
        etgenero = (EditText)findViewById(R.id.et_genero);
        RegCrearCuenta = (Button) findViewById(R.id.formulario_bottomRegistar);

        RegprogressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        RegCrearCuenta.setOnClickListener(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,vectorGenero);
        listGenero.setAdapter(adapter);

        listGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                etgenero.setText(""+parent.getItemAtPosition(position));
                if(parent.getItemAtPosition(position).equals("Hombre")){


                }else {
                    if(parent.getItemAtPosition(position).equals("Mujer")){

                    }else {

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                genero= "Hombre";
            }
        });

    }


    private void registrarUsuario() {
        //Obtenemos el email y la contraseña desde las cajas de texto
        email = RegEmail.getText().toString().trim();
        RegprogressDialog.setMessage("Realizando registro en linea...");
        RegprogressDialog.show();
        genero= etgenero.getText().toString().trim();
        nombre = Nombre.getText().toString().trim();
        contraseña = RegPassword.getText().toString().trim();
        telefono = Regtelefono.getText().toString().trim();
        peso = Double.parseDouble(Regpeso.getText().toString().trim());
        estatura = Double.parseDouble(Regestatura.getText().toString().trim());
        validarContraseña = Regvalidar_password.getText().toString().trim();


        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email) || !email.contains("@")) {//(precio.equals(""))
            showError(RegEmail, "Correo No Valido");
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(contraseña) || contraseña.length() < 8) {
            showError(RegPassword, "Contraseña no Valida");
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(validarContraseña)) {
            showError(Regvalidar_password, "Contraseña no Valida");
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(nombre)) {
            showError(Nombre, "No Puede estar en Blanco");
            Toast.makeText(this, "Falta ingresar el nombre", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(telefono) ) {
            showError(Regtelefono, "Telefono no valido");
            Toast.makeText(this, "Falta ingresar el telefono", Toast.LENGTH_LONG).show();
            return;
        }

        Usuario miusario = new Usuario();
        miusario.setNombre(nombre);
        miusario.setAltura(estatura);
        miusario.setCorreo(email);
        miusario.setGenero(genero);
        miusario.setTelefono(telefono);
        miusario.setPeso(peso);



        //registramos un nuevo usuario



        firebaseAuth.createUserWithEmailAndPassword(miusario.getCorreo(), contraseña)

                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            firestore.collection("usuarios").document(firebaseAuth.getUid()).set(miusario);
                            Toast.makeText(RegistroActivity.this, "Se ha registrado el usuario con el email: " + RegEmail.getText(), Toast.LENGTH_LONG).show();

                            Intent intencion = new Intent(RegistroActivity.this, MainActivity.class);
                            startActivity(intencion);
                            finish();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//si se presenta una colisión
                                Toast.makeText(RegistroActivity.this, "Ese usuario ya existe ", Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(RegistroActivity.this, "No se pudo registrar el usuario ", Toast.LENGTH_LONG).show();
                            }
                        }
                        RegprogressDialog.dismiss();
                    }
                });


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inacialize();



    }


   /* public void registrarFormularioDatabaseRealTime() {

        String nombre = Nombre.getText().toString().trim();
        String email = RegEmail.getText().toString().trim();
        String contraseña = RegPassword.getText().toString().trim();
        String telefono = Regtelefono.getText().toString().trim();
        int peso =Integer.parseInt( Regpeso.getText().toString().trim());
        int estatura = Integer.parseInt(Regestatura.getText().toString().trim());
        String validarContraseña = Regvalidar_password.getText().toString().trim();

        Usuario miusario = new Usuario(nombre,email,telefono,"NAN",peso,estatura);

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)|| !email.contains("@")) {//(precio.equals(""))
            showError(RegEmail,"Correo No Valido");
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(contraseña)||contraseña.length()<7) {
            showError(RegPassword,"Contraseña no Valida");
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(validarContraseña)) {
            showError(Regvalidar_password,"Contraseña no Valida");
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(nombre)) {
            showError(Nombre,"No Puede estar en Blanco");
            Toast.makeText(this, "Falta ingresar el nombre", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(telefono)||telefono.length()<10) {
            showError(Regtelefono,"Telefono no valido");
            Toast.makeText(this, "Falta ingresar el telefono", Toast.LENGTH_LONG).show();
            return;
        }

        if (peso>0) {
            Regpeso.setError("REQUERID");
            Toast.makeText(this, "Falta ingresar el peso", Toast.LENGTH_LONG).show();
            return;
        }

        if ((estatura>0)||(estatura<210)) {
            Regpeso.setError("REQUERID");
            Toast.makeText(this, "Falta ingresar la estatura", Toast.LENGTH_LONG).show();
            return;
        }

        if (!TextUtils.isEmpty(email)) {
            if (contraseña.equals(validarContraseña)) {
                String id = FormularioRegistro.push().getKey(); // genera un id automatico
               // FormularioRegistro Registro = new FormularioRegistro(nombre, email, contraseña, telefono, peso, estatura);
                //FormularioRegistro.child("Formulario Inscripcion").child(id).setValue(Registro);
                Toast.makeText(this, "Inscripcion Registrado con exito", Toast.LENGTH_LONG).show();

                registrarUsuario();
            } else {
                Toast.makeText(this, "ERROR, las contraseñas no son identicas", Toast.LENGTH_LONG).show();
            }

        }

    }*/

    @Override
    public void onClick(View v) {
        registrarUsuario();

    }

    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }


}




