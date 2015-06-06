package com.ucla.pectusmovil.servicio;

import com.ucla.pectusmovil.controlador.calculos.Calculos;
import com.ucla.pectusmovil.modelo.Actividad;
import com.ucla.pectusmovil.modelo.Ciudad;
import com.ucla.pectusmovil.modelo.Comision;
import com.ucla.pectusmovil.modelo.Estado;
import com.ucla.pectusmovil.modelo.Lugar;
import com.ucla.pectusmovil.modelo.SolicitudActividad;
import com.ucla.pectusmovil.modelo.TipoActividad;
import com.ucla.pectusmovil.modelo.TipoLugar;
import com.ucla.pectusmovil.modelo.Voluntario;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ENOCK on 19/11/2014.
 */
public class ServicioActividad {

    RestClient rest;
    ArrayList<NameValuePair> campos;
    ArrayList<NameValuePair> valores;

    private String ip = "192.168.1.198:5000";

    public ServicioActividad(){
        super();
    }


    public List<Actividad> buscarActividad(){

        List<Actividad> listaActividad = new ArrayList<Actividad>();


        HttpClient httpclient = new DefaultHttpClient();

        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://"+ ip+"/actividad/todos");
        HttpResponse response;
        try {
            // Ejecutar el request
            response = httpclient.execute(httpget);
            // Obtener la entidad del response
            HttpEntity entity = response.getEntity();
            // Si el response no esta encerrado como una entity, no hay necesidad de preocuparse, liberar la conexion
            if (entity != null) {
                // el JSON Response es leido
                InputStream instream = entity.getContent();
                String resultado = convertStreamToString(instream);
                // Un objeto JSONObject se crea
                try{
                    JSONObject json = new JSONObject(resultado);
                    JSONArray serActividad = json.getJSONArray("actividades");
                    for(int i=0; i < serActividad.length(); i++){
                        Actividad actividad = new Actividad();
                        actividad.setDescripcion((String) serActividad.getJSONObject(i).get("descripcion"));
                        actividad.setDuracion((String) serActividad.getJSONObject(i).get("duracion")); //horas
                        actividad.setFechainicio( convertirFecha((String) serActividad.getJSONObject(i).get("fechainicio")));
                        actividad.setFechafin( convertirFecha((String) serActividad.getJSONObject(i).get("fechafin")));
                        actividad.setHora((String) serActividad.getJSONObject(i).get("hora"));
                        String estatus = (String)serActividad.getJSONObject(i).get("estatus");
                        char estatusChar = estatus.charAt(0);
                        actividad.setEstatus(estatusChar );
                        actividad.setId((Integer) serActividad.getJSONObject(i).get("id"));

                        String monto = (String.valueOf(serActividad.getJSONObject(i).get("monto")));
                        actividad.setMonto((Float.parseFloat(monto)));
                        String montoEsperado = (String.valueOf(serActividad.getJSONObject(i).get("montoesperado")));
                        actividad.setMontoesperado((Float.parseFloat(monto)));
                        actividad.setNroAsistentes((Integer) serActividad.getJSONObject(i).get("nroasistentes"));
                        actividad.setNroasistentesesperados((Integer) serActividad.getJSONObject(i).get("nroasistentesesperados"));
                        actividad.setObservaciones((String.valueOf(serActividad.getJSONObject(i).get("observaciones"))));
                        actividad.setRecursosUtilizados((String) serActividad.getJSONObject(i).get("recursosutilizados"));
                        actividad.setTitulo(String.valueOf(serActividad.getJSONObject(i).get("titulo")));

                        //=====================Datos de Lugar=======================================
                        Lugar lugar = new Lugar();
                        lugar.setId((Integer) serActividad.getJSONObject(i).getJSONObject("lugar").get("id"));
                        lugar.setNombre((String) serActividad.getJSONObject(i).getJSONObject("lugar").get("nombre"));
                        lugar.setDireccion((String) serActividad.getJSONObject(i).getJSONObject("lugar").get("direccion"));
                        lugar.setTelefonoFijo((String) serActividad.getJSONObject(i).getJSONObject("lugar").get("tlffijo"));

                        TipoLugar tipoLugar = new TipoLugar();
                        tipoLugar.setId((Integer) serActividad.getJSONObject(i).getJSONObject("lugar").getJSONObject("tipolugar").get("id"));
                        tipoLugar.setDescripcion((String) serActividad.getJSONObject(i).getJSONObject("lugar").getJSONObject("tipolugar").get("descripcion"));
                        tipoLugar.setNombre((String) serActividad.getJSONObject(i).getJSONObject("lugar").getJSONObject("tipolugar").get("nombre"));

                        Ciudad ciudad = new Ciudad();
                        ciudad.setId((Integer) serActividad.getJSONObject(i).getJSONObject("lugar").getJSONObject("ciudad").get("id"));
                        ciudad.setNombre((String) serActividad.getJSONObject(i).getJSONObject("lugar").getJSONObject("ciudad").get("nombre"));

                        Estado estado = new Estado();
                        estado.setId((Integer) serActividad.getJSONObject(i).getJSONObject("lugar").getJSONObject("ciudad").getJSONObject("estado").get("id"));
                        estado.setNombre((String) serActividad.getJSONObject(i).getJSONObject("lugar").getJSONObject("ciudad").getJSONObject("estado").get("nombre"));

                        ciudad.setEstado(estado);
                        lugar.setTipoLugar(tipoLugar);
                        lugar.setCiudad(ciudad);

                        //=====================Datos de Solicitud====================================

                        SolicitudActividad solicitudActividad = new SolicitudActividad();
                        solicitudActividad.setId((Integer) serActividad.getJSONObject(i).getJSONObject("solicitudactividad").get("id"));
                        solicitudActividad.setFecha((convertirFecha((String) serActividad.getJSONObject(i).getJSONObject("solicitudactividad").get("fecsolicitud"))));
                        solicitudActividad.setDescripcion((String) serActividad.getJSONObject(i).getJSONObject("solicitudactividad").get("descripcion"));
                        solicitudActividad.setNomsolicitante((String) serActividad.getJSONObject(i).getJSONObject("solicitudactividad").get("nombsolicitante"));
                        solicitudActividad.setNomsolicitante((String) serActividad.getJSONObject(i).getJSONObject("solicitudactividad").get("estatus"));

                        TipoActividad tipoActividad = new TipoActividad();
                        tipoActividad.setId((Integer) serActividad.getJSONObject(i).getJSONObject("solicitudactividad").getJSONObject("tipoactividad").get("id"));
                        tipoActividad.setDescripcion((String) serActividad.getJSONObject(i).getJSONObject("solicitudactividad").getJSONObject("tipoactividad").get("descripcion"));
                        tipoActividad.setNombre((String) serActividad.getJSONObject(i).getJSONObject("solicitudactividad").getJSONObject("tipoactividad").get("nombre"));

                        Comision comision = new Comision();
                        comision.setId((Integer) serActividad.getJSONObject(i).getJSONObject("solicitudactividad").getJSONObject("tipoactividad").getJSONObject("comision").get("id"));
                        comision.setDescripcion((String) serActividad.getJSONObject(i).getJSONObject("solicitudactividad").getJSONObject("tipoactividad").getJSONObject("comision").get("descripcion"));
                        comision.setNombre((String) serActividad.getJSONObject(i).getJSONObject("solicitudactividad").getJSONObject("tipoactividad").getJSONObject("comision").get("nombre"));

                        tipoActividad.setComision(comision);
                        solicitudActividad.setIdTipoActividad(tipoActividad);
                        actividad.setIdSolicitudActividad(solicitudActividad);
                        actividad.setLugar(lugar);

                        listaActividad.add(actividad);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaActividad;
    }




    public List<Actividad> buscarActividadesVoluntario(String dato){

        List<Actividad> listaActividadesVoluntario= new ArrayList<>();
        List<Actividad> listaActividades= new ArrayList<>();
        int posicion;

        List<Integer> idActividades = new ArrayList<>();
        Calculos filtro = new Calculos();
        //llamada a servicio que retorna los eventos
        listaActividades =  filtro.ordenarListaActividadFecha(buscarActividad(), "antes");

        //Buscamos el ID de cada actividad proxima a realizar
        for (int i=0;i<listaActividades.size();i++){
            idActividades.add(listaActividades.get(i).getId());

        }
        // Se genera la lista con los eventos pendientes para el voluntario
        for (int i =0;i<idActividades.size();i++){
            posicion = i;
            Voluntario voluntario = new Voluntario();
            HttpClient httpclient = new DefaultHttpClient();

            // Preparar un objeto request via method Get
            HttpGet httpget = new HttpGet("http://"+ip+"/actividad/voluntarios?id="+idActividades.get(i));
            HttpResponse response;
            try {
                // Ejecutar el request
                response = httpclient.execute(httpget);
                // Obtener la entidad del response
                HttpEntity entity = response.getEntity();
                // Si el response no esta encerrado como una entity, no hay necesidad de preocuparse, liberar la conexion
                if (entity != null) {
                    // el JSON Response es leido
                    InputStream instream = entity.getContent();
                    String resultado = convertStreamToString(instream);
                    // Un objeto JSONObject se crea
                    try{
                        JSONObject json = new JSONObject(resultado);
                        JSONArray serActividad = json.getJSONArray("voluntarios");
                        for(int j=0; j < serActividad.length(); j++){
                            if (dato.equalsIgnoreCase(String.valueOf(serActividad.getJSONObject(j).get("cedula")))){
                                listaActividadesVoluntario.add(listaActividades.get(posicion));
                            }

                        }
                    }catch(JSONException e){
                        e.printStackTrace();
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        return listaActividadesVoluntario;
    }



    public Date convertirFecha(String fecha){
        Calendar calendario = Calendar.getInstance();
        Calendar calendarioActual = Calendar.getInstance();
        String fechaFraccionada[] = fecha.split("-");
        calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.YEAR));
        calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
        calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.DAY_OF_MONTH));

        return calendario.getTime();
    }

    private String convertStreamToString(InputStream is) {
        /*
         * Para convertir un InputStream a String se usa el metodo BufferedReader.readLine()
         * Iteramos hasta que el BufferedReader retone null lo cual significa
         * que no hay mas datos para leer. Cada linea sera agregada al StringBuilder
         * y sera retornado como un String.
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return sb.toString();
    }
}
