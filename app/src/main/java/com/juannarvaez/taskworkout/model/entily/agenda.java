package com.juannarvaez.taskworkout.model.entily;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.Exclude;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;


public class agenda implements Serializable {

    private String fecha;
    private String hora;
    private String nombreUsuario;
    private String id;
    private Date fechaTime;

    public Date getFechaTime() {
        return fechaTime;
    }

    public void setFechaTime(Date fechaTime) {
        this.fechaTime = fechaTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getConfirmacion() {
        return Confirmacion;
    }

    public void setConfirmacion(Boolean confirmacion) {
        Confirmacion = confirmacion;
    }

    private Boolean Confirmacion;
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    private String idUsuario;

    public agenda(String fecha, String hora, String idUsuario, String nombreUsuario,Date fechaTime) {
        this.fecha = fecha;
        this.hora = hora;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.Confirmacion=false;
        this.id ="";
        this.fechaTime=fechaTime;
    }

    public agenda() {
        this.fecha = "";
        this.hora = "";
        this.idUsuario = "";
        this.nombreUsuario = "";
        this.Confirmacion=false;
        this.id ="";
        this.fechaTime=fechaTime;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }



}
