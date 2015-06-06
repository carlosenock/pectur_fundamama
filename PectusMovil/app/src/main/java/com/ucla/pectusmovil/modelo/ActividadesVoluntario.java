package com.ucla.pectusmovil.modelo;

import java.io.Serializable;

/**
 * Created by ENOCK on 24/10/2014.
 */
public class ActividadesVoluntario implements Serializable {

    private int img;
    private String id;
    private Actividad actividad;
    private String fecha;
    private String descripcion;
    private Voluntario voluntario;





    public ActividadesVoluntario() {
        super();
    }

    public int getImg() {
        return img;
    }
    public void setImg(int img) {
        this.img = img;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Actividad getActividad() {
        return actividad;
    }
    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
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
}
