package com.juannarvaez.taskworkout.view.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.Repositorios.ProcolosRepositorio;
import com.juannarvaez.taskworkout.model.entily.Protocolos;

public class DetalleProtocoloActivity extends AppCompatActivity {
    private TextView tituloProtocolo;
    private  TextView descripcionProtocolo;
    private TextView requerimientosProtocolos;
    private  TextView adicionalProtocolos;
    private  TextView urlProtocolo;
    private Protocolos miProtocolo;
    private ProcolosRepositorio procolosRepositorio;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_protocolo);
        procolosRepositorio= new ProcolosRepositorio(DetalleProtocoloActivity.this);
        miProtocolo = (Protocolos) getIntent().getSerializableExtra("protocolo");
        asociarElementos();
        cargarDatos(miProtocolo);

    }

    private void cargarDatos(Protocolos miProtocolo) {
        tituloProtocolo.setText(miProtocolo.getTituloProtocolo());
        descripcionProtocolo.setText(miProtocolo.getDescripcionProtocolo());
        requerimientosProtocolos.setText(miProtocolo.getRequerimientosProtocolo());
        adicionalProtocolos.setText(miProtocolo.getAdicionalProtocolo());
        urlProtocolo.setText(miProtocolo.getUrlProtocolo());
    }

    private void asociarElementos() {
        tituloProtocolo = findViewById(R.id.nombre_protocolo_detalle);
        descripcionProtocolo= findViewById(R.id.descripcion_protocolo_detalle);
        requerimientosProtocolos= findViewById(R.id.requerimientos_protocolo_detalle);
        adicionalProtocolos= findViewById(R.id.adicional_protocolo_detalle);
        urlProtocolo = findViewById(R.id.url_protocolo_detalle);

    }
}