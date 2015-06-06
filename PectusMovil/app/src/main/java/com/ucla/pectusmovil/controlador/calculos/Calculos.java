package com.ucla.pectusmovil.controlador.calculos;

import com.ucla.pectusmovil.modelo.Actividad;
import com.ucla.pectusmovil.modelo.Evento;
import com.ucla.pectusmovil.modelo.SolicitudActividad;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by DarkGooz on 07-01-2015.
 */
public class Calculos implements Serializable {


    public List<Evento> ordenarListaEventoFecha(List<Evento> listaEvento, String tipo) {
        List<Evento> listaEventoOrdenado = new ArrayList<Evento>();
        SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date();
        try {
            date = formateadorFecha.parse(date.toString());
        } catch (ParseException e) {

        }
        for (Evento evento : listaEvento) {

            try {
                evento.setFecha(formateadorFecha.parse(evento.getFecha().toString()));
            } catch (ParseException e) {

            }
            if (tipo.compareToIgnoreCase("antes") == 0) {
                if (evento.getFecha().after(date)) {
                    listaEventoOrdenado.add(evento);
                }

            } else {
                if (evento.getFecha().before(date)) {
                    listaEventoOrdenado.add(evento);
                }
            }

        }
        return listaEventoOrdenado;
    }

    public List<Actividad> ordenarListaActividadFecha(List<Actividad> listaActividad, String tipo) {
        List<Actividad> ListaActividadOrdenado = new ArrayList<>();
        SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date();
        try {
            date = formateadorFecha.parse(date.toString());
        } catch (ParseException e) {

        }
        for (Actividad actividad : listaActividad) {

            /*
            try {
                actividad.getFechainicio(formateadorFecha.parse(actividad.getFechainicio().toString()));
            } catch (ParseException e) {
            }*/

            actividad.getFechainicio();
            if (tipo.compareToIgnoreCase("antes") == 0) {
                if (actividad.getFechainicio().after(date)) {
                    ListaActividadOrdenado.add(actividad);
                }

            } else {
                if (actividad.getFechainicio().before(date)) {
                    ListaActividadOrdenado.add(actividad);
                }
            }
        }

            return ListaActividadOrdenado;

    }


    public List<Actividad> ordenarListaCharlas(List<Actividad> lista, int dato) {
        List<Actividad> listaCharlas = new ArrayList<>();
        //metodo que filtra las actividades segun el tipo de actividad
        for (int i = 0; i < lista.size(); i++) {
            //Log.e("tamaño de lista", String.valueOf(lista.size()));
            if (lista.get(i).getIdSolicitudActividad().getIdTipoActividad().getId().equals(dato)){
                //Log.e("tipo de actividad", String.valueOf(lista.get(i).getIdSolicitudActividad().getIdTipoActividad().getId()));

                //Log.e("i", String.valueOf(i));
                listaCharlas.add(lista.get(i));
            }
        }
        return listaCharlas;
    }

    public List<Evento> ordenarEventosVoluntario(List<Evento> lista, String dato) {
        List<Evento> listaEventos = new ArrayList<>();
        // Se genera la lista con los eventos pendientes para el voluntario
        for (int i =0;i<lista.size();i++){
            for(int j=0;j<lista.get(i).getVoluntarios().size();j++){
                if (lista.get(i).getVoluntarios().get(j).getCedula().equalsIgnoreCase(dato)){
                    listaEventos.add(lista.get(i));
                }
            }
        }
        return listaEventos;
    }

    public List<SolicitudActividad> ordenarSolicitudActividad(List<SolicitudActividad> lista,int tipo, String dato){
        List<SolicitudActividad> listaSolicitudActividad = new ArrayList<>();
        List<SolicitudActividad> listaOrdenadaSolicitudActividad = new ArrayList<>();

        SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date();

        try {
            date = formateadorFecha.parse(date.toString());
        } catch (ParseException e) {

        }
        for (SolicitudActividad solicitudActividad : lista) {

            try {
                solicitudActividad.setFecha(formateadorFecha.parse(solicitudActividad.getFecha().toString()));
            } catch (ParseException e) {

            }
            if (dato.compareToIgnoreCase("antes") == 0) {
                if (solicitudActividad.getFecha().after(date)) {
                    listaOrdenadaSolicitudActividad.add(solicitudActividad);
                }

            } else {
                if (solicitudActividad.getFecha().before(date)) {
                    listaOrdenadaSolicitudActividad.add(solicitudActividad);
                }
            }

        }

        // Se genera la lista con los eventos pendientes para el voluntario
        for (int i =0;i<listaOrdenadaSolicitudActividad.size();i++){
                if (listaOrdenadaSolicitudActividad.get(i).getIdTipoActividad().getId()==tipo){
                    listaSolicitudActividad.add(listaOrdenadaSolicitudActividad.get(i));
                    //Log.e("Generando la lista ordenada",listaOrdenadaSolicitudActividad.get(i).getNomsolicitante());
                }
        }
        return listaSolicitudActividad;
    }

}
