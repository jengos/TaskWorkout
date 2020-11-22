package com.juannarvaez.taskworkout.model.entily;

import java.io.Serializable;
import java.util.Date;

public class SeguimientoPeso implements Serializable {
    double IMC ;
    double peso;
    String fecha;
    double altura;
    Date fechaDate;

    public Date getFechaDate() {
        return fechaDate;
    }

    public void setFechaDate(Date fechaDate) {
        this.fechaDate = fechaDate;
    }

    public SeguimientoPeso(double IMC, double peso, String fecha, double altura,Date fechaDate) {
        this.IMC = IMC;
        this.peso = peso;
        this.fecha = fecha;
        this.altura = altura;
        this.fechaDate=fechaDate;
    }

    public SeguimientoPeso() {
        this.IMC = IMC;
        this.peso = peso;
        this.fecha = fecha;
        this.altura = altura;
        this.fechaDate=fechaDate;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }
    public String toString() {
        return " "+getPeso()+ " "+getIMC() ;
    }
}
