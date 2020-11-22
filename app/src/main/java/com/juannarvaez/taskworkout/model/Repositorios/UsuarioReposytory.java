package com.juannarvaez.taskworkout.model.Repositorios;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.entily.SeguimientoPeso;
import com.juannarvaez.taskworkout.model.entily.Usuario;
import com.juannarvaez.taskworkout.model.entily.agenda;

import java.util.ArrayList;

public class UsuarioReposytory {
    private final String COLLECION_USUARIO = "usuarios";
    private final String COLLECION_SEGUIMIENTO = "segimiento_peso";
    private FirebaseFirestore firestore;
private FirebaseAuth auth;
   private Context context;
    private ArrayList<SeguimientoPeso> listado;
   private Usuario miUsuario;

    public UsuarioReposytory(Context context) {
        this.firestore = FirebaseFirestore.getInstance();
        this.auth = FirebaseAuth.getInstance();
        this.listado = new ArrayList<>();
        this.context = context;
    }
    public void crearFS(Usuario miUsuario, TaskWorkoutCallBack<Boolean> respuesta){
        firestore.collection(COLLECION_USUARIO).document(auth.getUid()).set(miUsuario).addOnCompleteListener(
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            respuesta.tareaCorrecta(true);
                        }else {
                            respuesta.tareaError(task.getException());
                        }
                    }
                }
        );
    }

    public void editarUsuarioFS (Usuario miUsuario, TaskWorkoutCallBack<Boolean> respuesta){
      firestore .collection(COLLECION_USUARIO).document(auth.getUid()).set(miUsuario)
              .addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                  public void onComplete(@NonNull Task<Void> task) {
                      if (task.isSuccessful()){
                          respuesta.tareaCorrecta(true);
                      }else {
                          respuesta.tareaError(task.getException());
                      }
                  }
              });


    }

    public void obtenerByIdFS(String id, TaskWorkoutCallBack<Usuario> respuesta){
        firestore .collection(COLLECION_USUARIO).document(id).get().addOnCompleteListener(
                new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            miUsuario= task.getResult().toObject(Usuario.class);
                            miUsuario.setId(id);
                            respuesta.tareaCorrecta(miUsuario);
                        }else {
                            respuesta.tareaError(task.getException());
                        }
                    }
                }

        );
    }
    public  void obtenerDatosUsuarios (String id, TaskWorkoutCallBack<Usuario> respuesta){
        firestore .collection(COLLECION_USUARIO).document(id).get().addOnCompleteListener(
                new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            if (task.getResult()!=null){
                                miUsuario= task.getResult().toObject(Usuario.class);
                                respuesta.tareaCorrecta(miUsuario);

                            }
                        } else {
                            respuesta.tareaError(task.getException());
                        }

                    }
                }
        );
    }

    public  void obtenerDatosUsuariosPeso ( TaskWorkoutCallBack<ArrayList<SeguimientoPeso>> respuesta){
        firestore.collection(COLLECION_USUARIO).document(auth.getUid()).collection(COLLECION_SEGUIMIENTO).orderBy("fechaDate").get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            listado.clear();
                            for (DocumentSnapshot item :task.getResult().getDocuments()){
                                SeguimientoPeso miSeguimientoPeso = item.toObject(SeguimientoPeso.class);
                                listado.add(miSeguimientoPeso );

                            }
                            respuesta.tareaCorrecta(listado);
                        }
                        else {
                            respuesta.tareaError(task.getException());
                        }
                    }
                }
        );



    }
}
