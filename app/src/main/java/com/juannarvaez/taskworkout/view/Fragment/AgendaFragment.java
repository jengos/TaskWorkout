package com.juannarvaez.taskworkout.view.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.Repositorios.AgendaRepository;
import com.juannarvaez.taskworkout.model.Repositorios.UsuarioReposytory;
import com.juannarvaez.taskworkout.model.entily.Usuario;
import com.juannarvaez.taskworkout.model.entily.agenda;
import com.juannarvaez.taskworkout.view.Activity.AgendarCitaActivity;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.view.Adapter.AgendaAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btoAgendaCita;
    private RecyclerView  rv_agenda;
    private static final int CODIGO_AGENDA= 48;
    private ArrayList<agenda> listAgenda;
    AgendaAdapter miAdapter;
    private AgendaRepository agendaRepository;
    public AgendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AgendaFragment newInstance(String param1, String param2) {
        AgendaFragment fragment = new AgendaFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        listAgenda = new ArrayList<>();

        View vista = inflater.inflate(R.layout.fragment_agenda, container, false);
        btoAgendaCita = vista.findViewById(R.id.bto_calendario);
        rv_agenda= vista.findViewById(R.id.rv_agenda);
        agendaRepository= new AgendaRepository(getContext());
        miAdapter = new AgendaAdapter(listAgenda);


        btoAgendaCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AgendarCitaActivity.class);
                getActivity().startActivity(intent);
            }
        });
        configuarListado();
       // actualizarListado();
        escucharListados();
        return vista;
    }

    private void configuarListado() {
        rv_agenda.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_agenda.setHasFixedSize(true);
        miAdapter.setOnItemClickListener(new AgendaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(agenda miAgenda, int posicion) {
                Toast.makeText(getContext(), "Hora: "+miAgenda.getHora()+" Fecha: "+miAgenda.getFecha(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClickHora(agenda miAgenda, int posicion) {

            }

            @Override
            public void onItemClickhFecha(agenda miAgenda, int posicion) {

            }

            @Override
            public void onItemClickhEliminar(agenda miAgenda, int posicion) {
                agendaRepository.eliminarAgendarFS(miAgenda.getId(), new TaskWorkoutCallBack<Boolean>() {
                    @Override
                    public void tareaCorrecta(Boolean respuesta) {
                        escucharListados();
                    }

                    @Override
                    public void tareaError(Exception exception) {

                    }
                });

            }
        });

    }
    private void actualizarListado() {
        agendaRepository.obtenerAgendarFS(new TaskWorkoutCallBack<ArrayList<agenda>>() {
            @Override
            public void tareaCorrecta(ArrayList<agenda> respuesta) {
                miAdapter.setListado(respuesta);
            }

            @Override
            public void tareaError(Exception exception) {

            }
        });
        rv_agenda.setAdapter(miAdapter);
    }
    private void escucharListados(){
        agendaRepository.escucharTodofs(new TaskWorkoutCallBack<ArrayList<agenda>>() {
            @Override
            public void tareaCorrecta(ArrayList<agenda> respuesta) {
                miAdapter.setListado(respuesta);
            }

            @Override
            public void tareaError(Exception exception) {

            }
        });
        rv_agenda.setAdapter(miAdapter);
    }

}