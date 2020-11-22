package com.juannarvaez.taskworkout.view.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import android.graphics.Color;
import android.widget.Button;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.juannarvaez.taskworkout.R;
import com.juannarvaez.taskworkout.model.Internet.TaskWorkoutCallBack;
import com.juannarvaez.taskworkout.model.Repositorios.UsuarioReposytory;
import com.juannarvaez.taskworkout.model.entily.SeguimientoPeso;
import com.juannarvaez.taskworkout.view.Activity.ListadoNutricionActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ObjetivosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ObjetivosFragment extends Fragment {
    LineChartView lineChartView;
    private Button mostrarNutricion;
 ArrayList<SeguimientoPeso> miSeguimientoPesos;
    Iterator<SeguimientoPeso> iteratorPeso;

    UsuarioReposytory usuarioReposytory;
    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
            "Oct", "Nov", "Dec"};
    int[] yAxisData = {50, 28, 15, 30, 20, 60, 15, 80, 45, 10, 90, 18};


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ObjetivosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ObjetivosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ObjetivosFragment newInstance(String param1, String param2) {
        ObjetivosFragment fragment = new ObjetivosFragment();
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
        miSeguimientoPesos= new ArrayList<>();
        usuarioReposytory = new UsuarioReposytory( getContext());
        usuarioReposytory.obtenerDatosUsuariosPeso(new TaskWorkoutCallBack<ArrayList<SeguimientoPeso>>() {
            @Override

            public void tareaCorrecta(ArrayList<SeguimientoPeso> respuesta) {

                Log.d("peso",respuesta.toString());
            }

            @Override
            public void tareaError(Exception exception) {

            }
        });




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View vista =inflater.inflate(R.layout.fragment_objetivos, container, false);
        mostrarNutricion=vista.findViewById(R.id.ver_nutriciones);

        lineChartView = vista.findViewById(R.id.chart);
        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#9C27B0"));

        for (int i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (int i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, yAxisData[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(12);
        axis.setTextColor(Color.parseColor("#03A9F4"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("IMC");
        yAxis.setTextColor(Color.parseColor("#03A9F4"));
        yAxis.setTextSize(12);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = 100;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);
        mostrarNutricion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ListadoNutricionActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return vista;
    }
}