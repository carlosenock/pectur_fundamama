package com.ucla.pectusmovil.modelo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by DarkGooz on 02-11-2014.
 */
public class PatrocinanteEvento implements Serializable {

    private Integer id;
    private Patrocinador patrocinador;
    private Evento evento;
    private TipoContribucion tipoContribucion;
    private String cantidad;
    private Date fecha;
    private Character status;

    public PatrocinanteEvento() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Patrocinador getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(Patrocinador patrocinador) {
        this.patrocinador = patrocinador;
    }

    public TipoContribucion getTipoContribucion() {

        return tipoContribucion;
    }

    public void setTipoContribucion(TipoContribucion tipoContribucion) {
        this.tipoContribucion = tipoContribucion;
    }
}
