package com.ucla.pectusmovil.modelo;

import java.io.Serializable;

/**
 * Created by ENOCK on 07/02/2015.
 */
public class TipoColaboracion implements Serializable {

    private Integer id;
    private String nombre;

    public TipoColaboracion(){
        // TODO Auto-generated constructor stub
    }

    public TipoColaboracion(Integer id, String nombre) {
        super();
        this.id = id;
        this.nombre = nombre;

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
