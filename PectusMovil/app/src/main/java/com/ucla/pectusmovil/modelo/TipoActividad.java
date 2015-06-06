package com.ucla.pectusmovil.modelo;

import java.io.Serializable;

public class TipoActividad implements Serializable {

    private Integer id;
    private Comision comision;
    private String nombre;
    private String descripcion;

    public TipoActividad(Integer id, String nombre, String descripcion) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public TipoActividad() {
        // TODO Auto-generated constructor stub
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Comision getComision() {
        return comision;
    }

    public void setComision(Comision comision) {
        this.comision = comision;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
