package com.juannarvaez.taskworkout.view.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.juannarvaez.taskworkout.R;

public class OlvideContrasena extends AppCompatActivity {
    private Button Rec_verificar_email;
    private EditText Rec_email;
    private View mostrarContraseñas;

    private String email="";

    private FirebaseAuth auth;

    private ProgressDialog progressDialog;

    private void inizializar(){
        Rec_verificar_email = (Button)findViewById(R.id.olvidecontaseña_botonverificarCorreo);
        Rec_email = (EditText)findViewById(R.id.olvidecontaseña_Correo);
        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        mostrarContraseñas = (View)findViewById(R.id.constraincContaseña);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olvide_contrasena);
        inizializar();
        Rec_verificar_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email= Rec_email.getText().toString();
                if(!email.isEmpty()){
                    progressDialog.setMessage("Espere...");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    resetPassword();
                }else{
                    Toast.makeText(OlvideContrasena.this, "Ingresar Email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void resetPassword() {
        auth.setLanguageCode("es");
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(OlvideContrasena.this, "¡¡¡EURECA!!!, Se ha enviado un correo para restablecer la contraseña", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(OlvideContrasena.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(OlvideContrasena.this, "NO se envio el correo para restablecer contraseña", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

}