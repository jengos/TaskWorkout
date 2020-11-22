package com.juannarvaez.taskworkout.model.entily;

import java.io.Serializable;

public class  Nutricion implements Serializable {

    private  String descripcionNutricion;
    private String tituloNutricion;
    private  String urlNutricion;
    private String imagenNutricion;
    private  String id;

    public Nutricion(String tituloNutricion,String descripcionNutricion,  String urlNutricion, String imagenNutricion) {
        this.tituloNutricion = tituloNutricion;
        this.descripcionNutricion = descripcionNutricion;
        this.urlNutricion = urlNutricion;
        this.imagenNutricion = imagenNutricion;
        this.id="";

    }

    public Nutricion() {
        this.descripcionNutricion = "";
        this.tituloNutricion = "";
        this.urlNutricion = "";
        this.imagenNutricion = "";
        this.id="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcionNutricion() {
        return descripcionNutricion;
    }

    public void setDescripcionNutricion(String descripcionNutricion) {
        this.descripcionNutricion = descripcionNutricion;
    }

    public String getTituloNutricion() {
        return tituloNutricion;
    }

    public void setTituloNutricion(String tituloNutricion) {
        this.tituloNutricion = tituloNutricion;
    }

    public String getUrlNutricion() {
        return urlNutricion;
    }

    public void setUrlNutricion(String urlNutricion) {
        this.urlNutricion = urlNutricion;
    }

    public String getImagenNutricion() {
        return imagenNutricion;
    }

    public void setImagenNutricion(String imagenNutricion) {
        this.imagenNutricion = imagenNutricion;
    }
}
