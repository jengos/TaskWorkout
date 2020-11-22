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
import com.juannarvaez.taskworkout.model.entily.Publicacion;
import com.juannarvaez.taskworkout.model.entily.agenda;


import java.io.Serializable;
import java.util.ArrayList;

public class PublicacionRespositorio implements Serializable {
    private static final String COLECCION_CONSEJOS = "consejos";
    private Context miContext;
    private FirebaseFirestore firestore;
    private ArrayList<Publicacion> listadoPubli;
    private FirebaseAuth auth;

    public PublicacionRespositorio(Context miContext) {
        this.miContext = miContext;
        this.firestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
        this.listadoPubli = new ArrayList<>();
    }
    public void agregarpublicFS(Publicacion miPublicacion , TaskWorkoutCallBack<Boolean> respuesta){
        firestore.collection(COLECCION_CONSEJOS).add(miPublicacion).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                respuesta.tareaCorrecta(true);
            }
        });
    }

    public void escucharTodoPublicacionfs(TaskWorkoutCallBack<ArrayList<Publicacion>> callBack){
        firestore.collection(COLECCION_CONSEJOS).orderBy("fechaActualDate", Query.Direction.DESCENDING).addSnapshotListener(
                new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error==null){
                           listadoPubli.clear();
                            for (DocumentSnapshot item:value.getDocuments()) {
                                Publicacion miPublicacion= item.toObject(Publicacion.class);

                                listadoPubli.add(miPublicacion);

                            }
                            callBack.tareaCorrecta(listadoPubli);
                        }else{
                            callBack.tareaError(error);
                        }
                    }
                }
        );
    }
}
