package com.ucla.pectusmovil.modelo;

/**
 * Created by ENOCK on 07/02/2015.
 */
public class Colaboracion {
    private int id;
    private Evento evento;
    private Patrocinador patrocinador;
    private TipoColaboracion tipoColaboracion;
    private Double cantidad;

    public Colaboracion(){

    }

    public Colaboracion(int id, Evento evento, Patrocinador patrocinado,
                        TipoColaboracion tipoColaboracion, Double cantidad) {
        super();
        this.id = id;
        this.evento = evento;
        this.patrocinador = patrocinado;
        this.tipoColaboracion = tipoColaboracion;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Patrocinador getPatrocinado() {
        return patrocinador;
    }

    public void setPatrocinado(Patrocinador patrocinado) {
        this.patrocinador = patrocinado;
    }

    public TipoColaboracion getTipoColaboracion() {
        return tipoColaboracion;
    }

    public void setTipoColaboracion(TipoColaboracion tipoColaboracion) {
        this.tipoColaboracion = tipoColaboracion;
    }

    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }


}
