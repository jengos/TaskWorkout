package com.juannarvaez.taskworkout.view.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.juannarvaez.taskworkout.view.Activity.CargarEjerciciosBrazoActivity;
import com.juannarvaez.taskworkout.view.Activity.CargarEjerciciosCalentamientoActivity;
import com.juannarvaez.taskworkout.view.Activity.CargarEjerciciosEspaldaActivity;
import com.juannarvaez.taskworkout.view.Activity.CargarEjerciciosPechoActivity;
import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.view.Activity.CargarEjerciciosNotequedesActivity;
import com.juannarvaez.taskworkout.view.Activity.CargarEjerciciosPiernasActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EjerciciosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EjerciciosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton ib_calentamiento;
    private  ImageButton ib_brazo;
    private  ImageButton ib_pierna;
    private  ImageButton ib_espalda;
    private  ImageButton ib_pecho;
    private  ImageButton ib_notequedes;


    public EjerciciosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EjerciciosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EjerciciosFragment newInstance(String param1, String param2) {
        EjerciciosFragment fragment = new EjerciciosFragment();
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
        View vista = inflater.inflate(R.layout.fragment_ejercicios, container, false);
        ib_calentamiento = vista.findViewById(R.id.imageCalent);
        ib_brazo = vista.findViewById(R.id.imageBrazo);
        ib_pierna = vista.findViewById(R.id.imagepiernas);
        ib_espalda = vista.findViewById(R.id.imageespalda);
        ib_pecho = vista.findViewById(R.id.imagePecho);
        ib_notequedes = vista.findViewById(R.id.imagenoquedes);

        ib_calentamiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CargarEjerciciosCalentamientoActivity.class);
                getActivity().startActivity(intent);
            }
        });

        ib_brazo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CargarEjerciciosBrazoActivity.class);
                getActivity().startActivity(intent);
            }
        });
        ib_pecho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CargarEjerciciosPechoActivity.class);
                getActivity().startActivity(intent);
            }
        });
        ib_espalda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CargarEjerciciosEspaldaActivity.class);
                getActivity().startActivity(intent);
            }
        });
        ib_pierna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CargarEjerciciosPiernasActivity.class);
                getActivity().startActivity(intent);
            }
        });
     ib_notequedes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), CargarEjerciciosNotequedesActivity.class);
            getActivity().startActivity(intent);
        }
    });
        return vista;
    }
}