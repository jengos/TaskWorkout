package com.juannarvaez.taskworkout.model.entily;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Publicacion {
    private String idUsuario;
    private String cuerpo;
    private String  fechaActual ;
    private String nombre_usuario;

    public Date getFechaActualDate() {
        return fechaActualDate;
    }

    public void setFechaActualDate(Date fechaActualDate) {
        this.fechaActualDate = fechaActualDate;
    }

    private Date fechaActualDate;

    public Publicacion() {
        this.idUsuario = "";
        this.cuerpo = "";
        this.fechaActual ="";
        this.nombre_usuario = "";
        this.fechaActualDate =fechaActualDate;
    }

    public Publicacion(String idUsuario, String cuerpo, String fechaActual, String nombre_usuario, Date fechaActualDate) {
        this.idUsuario = idUsuario;
        this.cuerpo = cuerpo;
        this.fechaActual = fechaActual;
        this.nombre_usuario = nombre_usuario;
        this.fechaActualDate =fechaActualDate;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String  fechaActual) {
        this.fechaActual = fechaActual;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
}
