package com.juannarvaez.taskworkout.model.entily;

import java.io.Serializable;

public class Usuario  implements Serializable {
    private String id;
    private String nombre, correo, telefono, genero,fechaCumple,rol;



    private double peso, altura;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Usuario() {
        this.nombre = "";
        this.correo = "";
        this.telefono = "";
        this.genero = "";
        this.peso = 0;
        this.altura = 0;
        this.fechaCumple="";
        this.id = "";
        this.rol= "";
    }

    public String getFechaCumple() {
        return fechaCumple;
    }

    public void setFechaCumple(String fechaCumple) {
        this.fechaCumple = fechaCumple;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario(String nombre, String correo, String telefono, String genero, double peso, double altura) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.genero = genero;
        this.peso = peso;
        this.altura = altura;
        this.fechaCumple="";
        this.id = "";
        this.rol= rol;

    }

    public Usuario(String nombre, String correo,String telefono) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.genero = "";
        this.peso = 0;
        this.altura = 0;
        this.fechaCumple="";
        this.id = "";

    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }





    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
}
