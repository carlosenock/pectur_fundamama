package com.ucla.pectusmovil.modelo;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

/**
 * Created by DarkGooz on 02-11-2014.
 */
public class SolicitudEvento implements Serializable {

    private Integer id;
    private Voluntario voluntario;
    private String descripcion;
    private Date fecha;
    private Time hora;
    private Character status;

    public SolicitudEvento() {
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
