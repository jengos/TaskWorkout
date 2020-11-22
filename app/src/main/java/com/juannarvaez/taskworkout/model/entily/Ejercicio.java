package com.juannarvaez.taskworkout.model.entily;

import java.io.Serializable;

public class Ejercicio implements Serializable {
    private String descripcionEjercicio;
    private  String tituloEjercicio;
    private  String tipoEjercicio;
    private String imagenEjercicio;
    private  String id;


    public Ejercicio(String descripcionEjercicio, String tituloEjercicio, String tipoEjercicio, String imagenEjercicio) {
        this.descripcionEjercicio = descripcionEjercicio;
        this.tituloEjercicio = tituloEjercicio;
        this.tipoEjercicio = tipoEjercicio;
        this.imagenEjercicio = imagenEjercicio;
        this.id="";

    }

    public Ejercicio(String tituloEjercicio, String tipoEjercicio, String imagenEjercicio) {
        this.tituloEjercicio = tituloEjercicio;
        this.tipoEjercicio = tipoEjercicio;
        this.imagenEjercicio = imagenEjercicio;
        this.descripcionEjercicio="";
        this.id="";

    }

    public Ejercicio() {
        this.tituloEjercicio = "";
        this.tipoEjercicio = "";
        this.imagenEjercicio = "";
        this.descripcionEjercicio="";
        this.id="";

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcionEjercicio() {
        return descripcionEjercicio;
    }

    public void setDescripcionEjercicio(String descripcionEjercicio) {
        this.descripcionEjercicio = descripcionEjercicio;
    }

    public String getTituloEjercicio() {
        return tituloEjercicio;
    }

    public void setTituloEjercicio(String tituloEjercicio) {
        this.tituloEjercicio = tituloEjercicio;
    }

    public String getTipoEjercicio() {
        return tipoEjercicio;
    }

    public void setTipoEjercicio(String tipoEjercicio) {
        this.tipoEjercicio = tipoEjercicio;
    }

    public String getImagenEjercicio() {
        return imagenEjercicio;
    }

    public void setImagenEjercicio(String imagenEjercicio) {
        this.imagenEjercicio = imagenEjercicio;
    }


}
