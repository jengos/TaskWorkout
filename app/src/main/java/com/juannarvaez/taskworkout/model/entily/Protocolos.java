package com.juannarvaez.taskworkout.model.entily;

import java.io.Serializable;

public class Protocolos implements Serializable {

    private String tituloProtocolo;
    private  String descripcionProtocolo;
    private String requerimientosProtocolo;
    private  String adicionalProtocolo;
    private String urlProtocolo;
    private String idProtocolo;

    public Protocolos(String tituloProtocolo, String descripcionProtocolo, String requerimientoProtocolos, String adicionalProtocolo, String urlProtocolo) {
        this.tituloProtocolo = tituloProtocolo;
        this.descripcionProtocolo = descripcionProtocolo;
        this.requerimientosProtocolo = requerimientosProtocolo;
        this.adicionalProtocolo = adicionalProtocolo;
        this.urlProtocolo = urlProtocolo;
        this.idProtocolo = "";
    }

    public Protocolos() {
        this.tituloProtocolo = "";
        this.descripcionProtocolo = "";
        this.requerimientosProtocolo = "";
        this.adicionalProtocolo = "";
        this.urlProtocolo = "";
        this.idProtocolo = "";
    }

    public String getTituloProtocolo() {
        return tituloProtocolo;
    }

    public void setTituloProtocolo(String tituloProtocolo) {
        this.tituloProtocolo = tituloProtocolo;
    }

    public String getDescripcionProtocolo() {
        return descripcionProtocolo;
    }

    public void setDescripcionProtocolo(String descripcionProtocolo) {
        this.descripcionProtocolo = descripcionProtocolo;
    }

    public String getRequerimientosProtocolo() {
        return requerimientosProtocolo;
    }

    public void setRequerimientosProtocolo(String requerimientosProtocolo) {
        this.requerimientosProtocolo = requerimientosProtocolo;
    }

    public String getAdicionalProtocolo() {
        return adicionalProtocolo;
    }

    public void setAdicionalProtocolo(String adicionalProtocolo) {
        this.adicionalProtocolo = adicionalProtocolo;
    }

    public String getUrlProtocolo() {
        return urlProtocolo;
    }

    public void setUrlProtocolo(String urlProtocolo) {
        this.urlProtocolo = urlProtocolo;
    }

    public String getIdProtocolo() {
        return idProtocolo;
    }

    public void setIdProtocolo(String idProtocolo) {
        this.idProtocolo = idProtocolo;
    }
}
