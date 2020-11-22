package com.juannarvaez.taskworkout.view.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.juannarvaez.taskworkout.view.Adapter.AdatadorPaginas;
import com.juannarvaez.taskworkout.R;

public class InicioActivity extends AppCompatActivity {
    TabLayout tabControl;
    ViewPager paginas;
    AdatadorPaginas adatadorPaginas;
    TextView cambio;
    public void onStart() {
        super.onStart();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null){
            Intent intent = new Intent(InicioActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        tabControl= (TabLayout)findViewById(R.id.tabControles);
        paginas = (ViewPager)findViewById(R.id.ViewPagerpaginas);
        cambio =(TextView)findViewById(R.id.textcambio);
        adatadorPaginas= new AdatadorPaginas(getSupportFragmentManager(), tabControl.getTabCount());
        paginas.setAdapter(adatadorPaginas);
        tabControl.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            paginas.setCurrentItem(tab.getPosition());
            if(tab.getPosition()==0){
                cambio.setText(R.string.Consejos);
                adatadorPaginas.notifyDataSetChanged();

            }
            if(tab.getPosition()==1){
                cambio.setText(R.string.Agenda);
              adatadorPaginas.notifyDataSetChanged();

             }
            if(tab.getPosition()==2){
                cambio.setText(R.string.Objetivos);
              adatadorPaginas.notifyDataSetChanged();

                }
                if(tab.getPosition()==3){
                    cambio.setText(R.string.ejercicios);
                    adatadorPaginas.notifyDataSetChanged();

                }
                if(tab.getPosition()==4){
                    cambio.setText(R.string.mas);
                    adatadorPaginas.notifyDataSetChanged();

                }
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        paginas.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabControl));

    }

}