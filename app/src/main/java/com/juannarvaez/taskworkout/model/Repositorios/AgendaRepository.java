package com.juannarvaez.taskworkout.model.Repositorios;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.entily.Usuario;
import com.juannarvaez.taskworkout.model.entily.agenda;

import java.util.ArrayList;

public class AgendaRepository {
    private static final String COLECCION_Agenda = "agenda";
    private Context miContext;
    private FirebaseFirestore firestore;
    private ArrayList<agenda> listado;
    private FirebaseAuth auth;

    public AgendaRepository(Context miContext) {
        this.miContext = miContext;
        this.firestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
        this.listado = new ArrayList<>();
    }
    public void agregarAgendaFS(agenda miAgenda , TaskWorkoutCallBack<Boolean> respuesta){
        firestore.collection(COLECCION_Agenda).add(miAgenda).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                respuesta.tareaCorrecta(true);
            }
        });
    }
    public void eliminarAgendarFS(String id, TaskWorkoutCallBack<Boolean> respuesta){
        firestore.collection(COLECCION_Agenda).document(id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    respuesta.tareaCorrecta(true);
                }
            }
        });

    }
    public void obtenerAgendarFS(TaskWorkoutCallBack<ArrayList<agenda>> callBack){
        firestore.collection(COLECCION_Agenda).whereEqualTo("idUsuario", auth.getUid()).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    listado.clear();
                    for (DocumentSnapshot item: task.getResult().getDocuments()){
                       agenda miAgenda = item.toObject(agenda.class);
                        miAgenda.setId(item.getId());
                       listado.add(miAgenda);
                    }
                    callBack.tareaCorrecta(listado);
                }else {
                    callBack.tareaError(task.getException());
                }

            }
        });
    }

    public void escucharTodofs(TaskWorkoutCallBack<ArrayList<agenda>> callBack){
        firestore.collection(COLECCION_Agenda).whereEqualTo("idUsuario", auth.getUid()).orderBy("fechaTime", Query.Direction.DESCENDING).addSnapshotListener(
                new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                      if(error==null){
                          listado.clear();
                          for (DocumentSnapshot item:value.getDocuments()) {
                              agenda miAgenda= item.toObject(agenda.class);
                              miAgenda.setId(item.getId());
                              listado.add(miAgenda);

                          }
                          callBack.tareaCorrecta(listado);
                      }else{
                          callBack.tareaError(error);
                      }
                    }
                }
        );
    }
    public void obtenerTodosAgendadosFS(TaskWorkoutCallBack<ArrayList <agenda>> respuesta){


        firestore.collection(COLECCION_Agenda).orderBy("fechaTime").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    listado.clear();

                    for (DocumentSnapshot item:task.getResult().getDocuments()){
                        agenda miAgenda= item.toObject(agenda.class);
                        miAgenda.setIdUsuario(item.getId());
                        listado.add(miAgenda);
                    }
                    respuesta.tareaCorrecta(listado);
                }else{
                    respuesta.tareaError(task.getException());
                }
            }
        });
    }


}
