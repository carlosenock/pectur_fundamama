package com.ucla.pectusmovil.modelo;


public class ListaPatrocinador {

    private String nombre;
    private String actividadComercial;


    public ListaPatrocinador(String nombre, String actividadComercial)
    {
        this.nombre = nombre;
        this.actividadComercial = actividadComercial;
    }
    public String getNombre() {
        return nombre;
    }

    public String getActividadComercial() {
        return actividadComercial;
    }


}
