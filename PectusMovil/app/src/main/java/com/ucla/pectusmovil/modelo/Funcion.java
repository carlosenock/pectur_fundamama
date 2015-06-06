package com.ucla.pectusmovil.modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DarkGooz on 27-10-2014.
 */
public class Funcion implements Serializable {

    private Integer id;
    private String nombre;
    private String Descripcion;
    private Character status;
    private List<FuncionRol> listaFuncionRol;

    public Funcion(List<FuncionRol> listaFuncionRol, Character status, String nombre, String descripcion, Integer id) {
        this.listaFuncionRol = listaFuncionRol;
        this.status = status;
        this.nombre = nombre;
        Descripcion = descripcion;
        this.id = id;
    }

    public Funcion() {
    }

    public List<FuncionRol> getListaFuncionRol() {
        return listaFuncionRol;
    }

    public void setListaFuncionRol(List<FuncionRol> listaFuncionRol) {
        this.listaFuncionRol = listaFuncionRol;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
