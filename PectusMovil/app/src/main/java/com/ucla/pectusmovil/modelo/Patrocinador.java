package com.ucla.pectusmovil.modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DarkGooz on 02-11-2014.
 */
public class Patrocinador implements Serializable {
    private Integer id;
    private String nombre;
    private String rif;
    private String direccion;
    private String telefonoCelular;
    private String telefonoFijo;
    private String nombreRepresentante;
    private String telefonoRepresentante;
    private String correoRepresentante;
    private List<PatrocinanteEvento> listaPatrocinantesEventos;

    //=====================================>>>> este
    private String actividadComercial;

    public Patrocinador() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getTelefonoRepresentante() {
        return telefonoRepresentante;
    }

    public void setTelefonoRepresentante(String telefonoRepresentante) {
        this.telefonoRepresentante = telefonoRepresentante;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public List<PatrocinanteEvento> getListaPatrocinantesEventos() {
        return listaPatrocinantesEventos;
    }

    public void setListaPatrocinantesEventos(List<PatrocinanteEvento> listaPatrocinantesEventos) {
        this.listaPatrocinantesEventos = listaPatrocinantesEventos;
    }

    public String getCorreoRepresentante() {
        return correoRepresentante;
    }

    public void setCorreoRepresentante(String correoRepresentante) {
        this.correoRepresentante = correoRepresentante;
    }

    public String getActividadComercial() {
        return actividadComercial;
    }

    public void setActividadComercial(String actividadComercial) {
        this.actividadComercial = actividadComercial;
    }
}
