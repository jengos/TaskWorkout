package com.juannarvaez.taskworkout.view.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.Repositorios.PublicacionRespositorio;
import com.juannarvaez.taskworkout.model.Repositorios.UsuarioReposytory;
import com.juannarvaez.taskworkout.model.entily.Publicacion;
import com.juannarvaez.taskworkout.model.entily.Usuario;
import com.juannarvaez.taskworkout.view.Activity.PublicacionActivity;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.view.Adapter.ConsejosAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsejosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsejosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int CODIGO_PUBLICA =15 ;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FloatingActionButton bto_agregarPublicacion;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    private Usuario miUsario;
    private ArrayList<Publicacion> listaPublicacion;
    private RecyclerView RV_publicacion;
    private ConsejosAdapter miAdapter;
    PublicacionRespositorio publicacionRespositorio;

    public ConsejosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsejosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsejosFragment newInstance(String param1, String param2) {
        ConsejosFragment fragment = new ConsejosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
        auth= FirebaseAuth.getInstance();
        String id= auth.getUid();

        firestore = FirebaseFirestore.getInstance();
        Log.d("Prueba",id);


        firestore.collection("usuarios").document(id).get().addOnCompleteListener(
                new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful());
                        if(task.getResult() !=null){
                            miUsario = task.getResult().toObject(Usuario.class);
                            Log.e("Usuario", miUsario.getNombre()+miUsario.getId());
                        }

                    }
                }
        );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_consejos, container, false);
        RV_publicacion = vista.findViewById(R.id.rv_publicar);
        listaPublicacion =new ArrayList<>();
        publicacionRespositorio = new PublicacionRespositorio(getContext());
        miAdapter= new ConsejosAdapter(listaPublicacion);

        bto_agregarPublicacion = vista.findViewById(R.id.fA_publicarConsejos);
        bto_agregarPublicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PublicacionActivity.class);
                intent.putExtra("Usuario" ,miUsario);
                getActivity().startActivityForResult(intent,CODIGO_PUBLICA);
            }
        });

        configurarListado();
        escucharListados();

        return vista;
    }

    private void configurarListado() {
        RV_publicacion.setLayoutManager(new LinearLayoutManager(getContext()));
        RV_publicacion.setHasFixedSize(true);
        miAdapter.setOnItemClickListener(new ConsejosAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Publicacion miPublicacion, int posicion) {
                Toast.makeText(getContext(), "Publicado "+miPublicacion.getFechaActualDate(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void escucharListados() {
    publicacionRespositorio.escucharTodoPublicacionfs(new TaskWorkoutCallBack<ArrayList<Publicacion>>() {
        @Override
        public void tareaCorrecta(ArrayList<Publicacion> respuesta) {
            miAdapter.setListado(respuesta);
        }

        @Override
        public void tareaError(Exception exception) {

        }
    });
    RV_publicacion.setAdapter(miAdapter);
    }
}