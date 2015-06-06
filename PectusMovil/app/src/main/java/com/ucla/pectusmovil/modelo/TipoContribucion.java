package com.ucla.pectusmovil.modelo;

import java.io.Serializable;

/**
 * Created by DarkGooz on 25-11-2014.
 */
public class TipoContribucion implements Serializable{
    private Integer id;
    private String nombre;

    public TipoContribucion() {
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
}
