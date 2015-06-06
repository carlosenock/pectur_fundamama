package com.ucla.pectusmovil.modelo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DarkGooz on 27-10-2014.
 */
public class Rol implements Serializable {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Character status;
    private List<Usuario> listaUsuarios;
    private List<FuncionRol> listaFuncionRoles;

    public Rol(Integer id, String nombre, String descripcion, Character status) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.status = status;
        this.listaUsuarios = listaUsuarios;
    }

    public Rol() {
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

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<FuncionRol> getListaFuncionRoles() {
        return listaFuncionRoles;
    }

    public void setListaFuncionRoles(List<FuncionRol> listaFuncionRoles) {
        this.listaFuncionRoles = listaFuncionRoles;
    }
}
