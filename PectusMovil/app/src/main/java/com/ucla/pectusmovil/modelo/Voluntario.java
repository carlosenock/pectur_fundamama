package com.ucla.pectusmovil.modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DarkGooz on 02-11-2014.
 */
public class Voluntario extends Persona implements Serializable {
    private Integer id;
    private String profesion;
    private Character estadoCivil;
    private Usuario usuario;
    private String lugarTrabajo;
    private String cargo;
    private String dirTrabajo;
    private String telefonoOficina;
    private String referidoPor;
    private Character status;
    private List<VoluntarioEvento> listaVoluntarioEventos;
    private List<SolicitudEvento> listaSolicitudEventos;


    public Voluntario() {
        super();
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getReferidoPor() {
        return referidoPor;
    }

    public void setReferidoPor(String referidoPor) {
        this.referidoPor = referidoPor;
    }

    public String getTelefonoOficina() {
        return telefonoOficina;
    }

    public void setTelefonoOficina(String telefonoOficina) {
        this.telefonoOficina = telefonoOficina;
    }

    public String getDirTrabajo() {
        return dirTrabajo;
    }

    public void setDirTrabajo(String dirTrabajo) {
        this.dirTrabajo = dirTrabajo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public char getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Character estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<VoluntarioEvento> getListaVoluntarioEventos() {
        return listaVoluntarioEventos;
    }

    public void setListaVoluntarioEventos(List<VoluntarioEvento> listaVoluntarioEventos) {
        this.listaVoluntarioEventos = listaVoluntarioEventos;
    }

    public List<SolicitudEvento> getListaSolicitudEventos() {
        return listaSolicitudEventos;
    }

    public void setListaSolicitudEventos(List<SolicitudEvento> listaSolicitudEventos) {
        this.listaSolicitudEventos = listaSolicitudEventos;
    }
}
