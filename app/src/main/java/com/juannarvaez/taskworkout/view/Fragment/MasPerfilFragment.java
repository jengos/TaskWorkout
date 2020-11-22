package com.juannarvaez.taskworkout.view.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.juannarvaez.taskworkout.view.Activity.ListadoAgendaAdminActivity;
import com.juannarvaez.taskworkout.view.Activity.ListadoProtocolosActivity;
import com.juannarvaez.taskworkout.view.Activity.MainActivity;
import com.juannarvaez.taskworkout.view.Activity.PerfilActivity;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.entily.Usuario;

public class MasPerfilFragment extends Fragment {
    private static final int CODIG_EDITAR_PERFIL = 15;
    private TextView nombreUsuario;
    private Usuario miUsuario;
    private  View verAgendaHoy;
    private View cerrar_sesion;
    private View editar_usuario;
    private View irProtocolos;

    private FirebaseAuth auth;

    @Override
    public void onStart() {
        super.onStart();
        auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null){
            Intent intent = new Intent(getContext(), MainActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    public void cerrarSesion(){
        cerrar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
    public void editarUsuario(){
        editar_usuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PerfilActivity.class);
                startActivity(intent);
            }
        });
    }
    private void roles() {

        FirebaseFirestore registro = FirebaseFirestore.getInstance();
        miUsuario = new Usuario();
        auth=FirebaseAuth.getInstance();


        registro.collection("usuarios").document(auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    if(task.getResult()!=null){
                        miUsuario=task.getResult().toObject(Usuario.class);
                        nombreUsuario.setText(miUsuario.getNombre());


                    }
                }

            }
        });

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        roles();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mas_perfil, container, false);
        cerrar_sesion=view.findViewById(R.id.cerrar_sesion);
        editar_usuario=view.findViewById(R.id.editar_perfil);
        nombreUsuario=view.findViewById(R.id.tv_NombreUsuario);
        verAgendaHoy = view.findViewById(R.id.ver_agenda_entrenador);
        irProtocolos = view.findViewById(R.id.protocolos_bioseguridad);
        verAgendaHoy = view.findViewById(R.id.ver_agenda_entrenador);

        auth = FirebaseAuth.getInstance();
        cerrarSesion();
        editarUsuario();
        irProtocolos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListadoProtocolosActivity.class);
                startActivity(intent);
            }
        });

        verAgendaHoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListadoAgendaAdminActivity.class);
                startActivity(intent);
            }
        });

        nombreUsuario.setText(miUsuario.getNombre());
        if(miUsuario.getRol().equals("admin")){
            verAgendaHoy.setVisibility(View.VISIBLE);
        }

        return view;
    }


}