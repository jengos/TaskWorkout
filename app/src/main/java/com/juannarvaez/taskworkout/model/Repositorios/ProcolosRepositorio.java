package com.juannarvaez.taskworkout.model.Repositorios;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.entily.Protocolos;

import java.util.ArrayList;

public class ProcolosRepositorio {

        private static final  String COLECCION_PROTOCOLOS="protocolo";
        private Context etContexto;
        private FirebaseFirestore firestore;
        private ArrayList<Protocolos> listado;

        public ProcolosRepositorio(Context etContexto) {
            this.etContexto = etContexto;
            this.firestore=FirebaseFirestore.getInstance();
            this.listado=new ArrayList<>();
        }

        public void obtenerTodosProtocolosFS(TaskWorkoutCallBack<ArrayList <Protocolos>> respuesta){
            firestore.collection(COLECCION_PROTOCOLOS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        listado.clear();
                        for (DocumentSnapshot item:task.getResult().getDocuments()){
                            Protocolos miProtocolo= item.toObject(Protocolos.class);
                            miProtocolo.setIdProtocolo(item.getId());
                            listado.add(miProtocolo);
                        }
                        respuesta.tareaCorrecta(listado);
                    }else{
                        respuesta.tareaError(task.getException());
                    }
                }
            });
        }

    }


