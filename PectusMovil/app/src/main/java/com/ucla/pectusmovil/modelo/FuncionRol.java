package com.ucla.pectusmovil.modelo;

import java.io.Serializable;

/**
 * Created by DarkGooz on 27-10-2014.
 */
public class FuncionRol implements Serializable {

    private Integer id;
    private Character status;
    private Rol rol;
    private Funcion funcion;

    public FuncionRol(Funcion funcion, Rol rol, Character status, Integer id) {
        this.funcion = funcion;
        this.rol = rol;
        this.status = status;
        this.id = id;
    }

    public FuncionRol() {
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
