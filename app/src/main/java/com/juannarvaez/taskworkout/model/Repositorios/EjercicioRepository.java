package com.juannarvaez.taskworkout.model.Repositorios;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.entily.Ejercicio;

import java.util.ArrayList;

public class EjercicioRepository {


        private static final  String COLECCION_EJERCICIO="ejercicios";

        private Context etContexto;
        private FirebaseFirestore firestore;
        private ArrayList<Ejercicio> listado;

        public EjercicioRepository(Context etContexto) {
            this.etContexto = etContexto;
            this.firestore=FirebaseFirestore.getInstance();
            this.listado=new ArrayList<>();
        }

        public void obtenerTodosFSCalentamiento(TaskWorkoutCallBack<ArrayList <Ejercicio>> respuesta){
            firestore.collection(COLECCION_EJERCICIO).whereEqualTo("tipoEjercicio", "Calentamiento").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        listado.clear();
                        for (DocumentSnapshot item:task.getResult().getDocuments()){
                            Ejercicio miEjercicio = item.toObject(Ejercicio.class);
                            miEjercicio.setId(item.getId());
                            listado.add(miEjercicio);
                        }
                        respuesta.tareaCorrecta(listado);
                    }else{
                        respuesta.tareaError(task.getException());
                    }
                }
            });
        }
    public void obtenerTodosFSBrazos(TaskWorkoutCallBack<ArrayList <Ejercicio>> respuesta){
        firestore.collection(COLECCION_EJERCICIO).whereEqualTo("tipoEjercicio", "Brazos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    listado.clear();
                    for (DocumentSnapshot item:task.getResult().getDocuments()){
                        Ejercicio miEjercicio = item.toObject(Ejercicio.class);
                        miEjercicio.setId(item.getId());
                        listado.add(miEjercicio);
                    }
                    respuesta.tareaCorrecta(listado);
                }else{
                    respuesta.tareaError(task.getException());
                }
            }
        });
    }
    public void obtenerTodosFSBPecho(TaskWorkoutCallBack<ArrayList <Ejercicio>> respuesta){
        firestore.collection(COLECCION_EJERCICIO).whereEqualTo("tipoEjercicio", "Pecho").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    listado.clear();
                    for (DocumentSnapshot item:task.getResult().getDocuments()){
                        Ejercicio miEjercicio = item.toObject(Ejercicio.class);
                        miEjercicio.setId(item.getId());
                        listado.add(miEjercicio);
                    }
                    respuesta.tareaCorrecta(listado);
                }else{
                    respuesta.tareaError(task.getException());
                }
            }
        });
    }

    public void obtenerTodosFSEspalda(TaskWorkoutCallBack<ArrayList <Ejercicio>> respuesta){
        firestore.collection(COLECCION_EJERCICIO).whereEqualTo("tipoEjercicio", "Espalda").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    listado.clear();
                    for (DocumentSnapshot item:task.getResult().getDocuments()){
                        Ejercicio miEjercicio = item.toObject(Ejercicio.class);
                        miEjercicio.setId(item.getId());
                        listado.add(miEjercicio);
                    }
                    respuesta.tareaCorrecta(listado);
                }else{
                    respuesta.tareaError(task.getException());
                }
            }
        });
    }


    public void obtenerTodosFSNotequedes(TaskWorkoutCallBack<ArrayList <Ejercicio>> respuesta){
        firestore.collection(COLECCION_EJERCICIO).whereEqualTo("tipoEjercicio", "Retos").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    listado.clear();
                    for (DocumentSnapshot item:task.getResult().getDocuments()){
                        Ejercicio miEjercicio = item.toObject(Ejercicio.class);
                        miEjercicio.setId(item.getId());
                        listado.add(miEjercicio);
                    }
                    respuesta.tareaCorrecta(listado);
                }else{
                    respuesta.tareaError(task.getException());
                }
            }
        });
    }
    public void obtenerTodosFSPiernas(TaskWorkoutCallBack<ArrayList <Ejercicio>> respuesta){
        firestore.collection(COLECCION_EJERCICIO).whereEqualTo("tipoEjercicio", "Piernas").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    listado.clear();
                    for (DocumentSnapshot item:task.getResult().getDocuments()){
                        Ejercicio miEjercicio = item.toObject(Ejercicio.class);
                        miEjercicio.setId(item.getId());
                        listado.add(miEjercicio);
                    }
                    respuesta.tareaCorrecta(listado);
                }else{
                    respuesta.tareaError(task.getException());
                }
            }
        });
    }
    }

