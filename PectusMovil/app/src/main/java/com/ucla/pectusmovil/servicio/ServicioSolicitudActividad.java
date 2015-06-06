package com.ucla.pectusmovil.servicio;

import com.ucla.pectusmovil.modelo.Comision;
import com.ucla.pectusmovil.modelo.SolicitudActividad;
import com.ucla.pectusmovil.modelo.TipoActividad;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
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
import java.util.Vector;

/**
 * Created by ENOCK on 08/02/2015.
 */
public class ServicioSolicitudActividad {

    RestClient rest;
    ArrayList<NameValuePair> campos;
    ArrayList<NameValuePair> valores;

    private String ip = "192.168.1.198:5000";


    public List<SolicitudActividad> buscarSolicitudCharla(){

        List<SolicitudActividad> listaSolicitudActividad = new ArrayList<SolicitudActividad>();


        HttpClient httpclient = new DefaultHttpClient();

        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://"+ ip+"/solicitud-actividad/todos");
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
                    JSONArray serActividad = json.getJSONArray("solicitudactividad");
                    for(int i=0; i < serActividad.length(); i++){
                        SolicitudActividad solicitudActividad = new SolicitudActividad();
                        solicitudActividad.setDescripcion((String) serActividad.getJSONObject(i).get("descripcion"));
                        solicitudActividad.setFecha(convertirFecha((String) serActividad.getJSONObject(i).get("fecsolicitud")));
                        solicitudActividad.setNomsolicitante((String) serActividad.getJSONObject(i).get("nombsolicitante"));
                        String estatus = (String)serActividad.getJSONObject(i).get("estatus");
                        char estatusChar = estatus.charAt(0);
                        solicitudActividad.setEstatus(estatusChar );
                        solicitudActividad.setId((Integer) serActividad.getJSONObject(i).get("id"));

                        TipoActividad tipoActividad = new TipoActividad();
                        tipoActividad.setId((Integer) serActividad.getJSONObject(i).getJSONObject("tipoactividad").get("id"));
                        tipoActividad.setDescripcion((String) serActividad.getJSONObject(i).getJSONObject("tipoactividad").get("descripcion"));
                        tipoActividad.setNombre((String) serActividad.getJSONObject(i).getJSONObject("tipoactividad").get("nombre"));

                        Comision comision = new Comision();
                        comision.setId((Integer) serActividad.getJSONObject(i).getJSONObject("tipoactividad").getJSONObject("comision").get("id"));
                        comision.setDescripcion((String) serActividad.getJSONObject(i).getJSONObject("tipoactividad").getJSONObject("comision").get("descripcion"));
                        comision.setNombre((String) serActividad.getJSONObject(i).getJSONObject("tipoactividad").getJSONObject("comision").get("nombre"));

                        tipoActividad.setComision(comision);
                        solicitudActividad.setIdTipoActividad(tipoActividad);

                        listaSolicitudActividad.add(solicitudActividad);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaSolicitudActividad;
    }
    public Object agregarCharla(Vector<Object> datos){

        Object ok=false;

        String url = "http://"+ip+"/solicitud-actividad/agregar";

        rest = new RestClient();
        campos = new ArrayList<NameValuePair>();
        valores = new ArrayList<NameValuePair>();

        valores.add(new BasicNameValuePair("idtipoactividad", String.valueOf(datos.elementAt(0))));
        valores.add(new BasicNameValuePair("descripcion", String.valueOf(datos.elementAt(1))));
        valores.add(new BasicNameValuePair("nombsolicitante", String.valueOf(datos.elementAt(2))));
        valores.add(new BasicNameValuePair("telfsolicitante", String.valueOf(datos.elementAt(3))));
        valores.add(new BasicNameValuePair("fecsolicitud", String.valueOf(datos.elementAt(4))));
        valores.add(new BasicNameValuePair("estatus", String.valueOf(datos.elementAt(5))));
        valores.add(new BasicNameValuePair("codigo", String.valueOf(datos.elementAt(6))));


        campos.add(new BasicNameValuePair("idtipoactividad", String.valueOf(datos.elementAt(0))));
        campos.add(new BasicNameValuePair("descripcion", String.valueOf(datos.elementAt(1))));
        campos.add(new BasicNameValuePair("nombsolicitante", String.valueOf(datos.elementAt(2))));
        campos.add(new BasicNameValuePair("telfsolicitante", String.valueOf(datos.elementAt(3))));
        campos.add(new BasicNameValuePair("fecsolicitud", String.valueOf(datos.elementAt(4))));
        campos.add(new BasicNameValuePair("estatus", String.valueOf(datos.elementAt(5))));
        campos.add(new BasicNameValuePair("codigo", String.valueOf(datos.elementAt(6))));


        try {

            try {
                ok =  rest.Execute(RestClient.RequestMethod.GET, url, campos, valores);
            }catch (NullPointerException nulo){
                nulo.printStackTrace();
                ok = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

        return ok;
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
