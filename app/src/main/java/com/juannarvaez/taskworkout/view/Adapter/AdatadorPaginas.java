package com.juannarvaez.taskworkout.view.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.juannarvaez.taskworkout.view.Fragment.AgendaFragment;
import com.juannarvaez.taskworkout.view.Fragment.ConsejosFragment;
import com.juannarvaez.taskworkout.view.Fragment.EjerciciosFragment;
import com.juannarvaez.taskworkout.view.Fragment.MasPerfilFragment;
import com.juannarvaez.taskworkout.view.Fragment.ObjetivosFragment;

public class AdatadorPaginas extends FragmentPagerAdapter {
    int numeroPaginas;
    public AdatadorPaginas (@NonNull FragmentManager fm , int numPaginas){
        super(fm, numPaginas);
        this.numeroPaginas=numPaginas;
    }

    @NonNull
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                return new ConsejosFragment();
            case 1:
                return new AgendaFragment();
            case 2:
                return new ObjetivosFragment();
            case 3:
                return new EjerciciosFragment();
            case 4:
                return new MasPerfilFragment();
            default:return null;
        }
    }
    @Override
    public int getCount(){
        return numeroPaginas;
    }
}
