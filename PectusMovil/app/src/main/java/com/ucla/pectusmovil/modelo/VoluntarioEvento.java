package com.ucla.pectusmovil.modelo;

import java.io.Serializable;

/**
 * Created by DarkGooz on 02-11-2014.
 */
public class VoluntarioEvento implements Serializable {

    private Integer id;
    private Evento evento;
    private Voluntario voluntario;
    private Character status;

    public VoluntarioEvento() {
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

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
