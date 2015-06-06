package com.ucla.pectusmovil.modelo;

import java.io.Serializable;

/**
 * Created by DarkGooz on 27-10-2014.
 */
public class Usuario implements Serializable {
    private Integer id;
    private String nombre;
    private String clave;
    private Rol rol;
    private Character status;


    public Usuario() {
    }

    public Usuario(Character status, String clave, Rol rol, String nombre, Integer id) {
        this.status = status;
        this.clave = clave;
        this.rol = rol;
        this.nombre = nombre;
        this.id = id;
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

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
}
