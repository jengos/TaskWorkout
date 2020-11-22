package com.juannarvaez.taskworkout.model.Repositorios;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.entily.Nutricion;

import java.util.ArrayList;

public class NutricionRepositorio {
    private static final  String COLECCION_NUTRICION="nutricion";
    private Context etContexto;
    private FirebaseFirestore firestore;
    private ArrayList<Nutricion> listado;

    public NutricionRepositorio(Context etContexto) {
        this.etContexto = etContexto;
        this.firestore=FirebaseFirestore.getInstance();
        this.listado=new ArrayList<>();
    }

    public void obtenerTodosFS(TaskWorkoutCallBack<ArrayList <Nutricion>> respuesta){
        firestore.collection(COLECCION_NUTRICION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    listado.clear();
                    for (DocumentSnapshot item:task.getResult().getDocuments()){
                        Nutricion miNutricion = item.toObject(Nutricion.class);
                        miNutricion.setId(item.getId());
                        listado.add(miNutricion);
                    }
                    respuesta.tareaCorrecta(listado);
                }else{
                    respuesta.tareaError(task.getException());
                }
            }
        });
    }
}

