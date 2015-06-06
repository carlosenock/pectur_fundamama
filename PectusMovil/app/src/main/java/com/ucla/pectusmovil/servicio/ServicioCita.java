package com.ucla.pectusmovil.servicio;

import com.ucla.pectusmovil.modelo.Persona;
import com.ucla.pectusmovil.modelo.Visita;

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
import java.util.Vector;

/**
 * Created by ENOCK on 26/01/2015.
 */
public class ServicioCita {

    RestClient rest;
    ArrayList<NameValuePair> campos;
    ArrayList<NameValuePair> valores;


    private String ip = "192.168.1.198:5000";

    public Object agregarCitaPaciente(Vector<Object> datos){

        Object ok =false;
        String url = "http://"+ip+"/paciente/agregar";

        rest = new RestClient();
        campos = new ArrayList<NameValuePair>();
        valores = new ArrayList<NameValuePair>();

        valores.add(new BasicNameValuePair("cedula", String.valueOf(datos.elementAt(0))));
        valores.add(new BasicNameValuePair("idciudad", String.valueOf(datos.elementAt(1))));
        valores.add(new BasicNameValuePair("nombre", String.valueOf(datos.elementAt(2))));
        valores.add(new BasicNameValuePair("apellido", String.valueOf(datos.elementAt(3))));
        valores.add(new BasicNameValuePair("tlfcelular", String.valueOf(datos.elementAt(4))));
        valores.add(new BasicNameValuePair("tlffijo", String.valueOf(datos.elementAt(5))));
        valores.add(new BasicNameValuePair("edocivil", String.valueOf(datos.elementAt(6))));
        valores.add(new BasicNameValuePair("direccion", String.valueOf(datos.elementAt(7))));
        valores.add(new BasicNameValuePair("correo", String.valueOf(datos.elementAt(8))));
        valores.add(new BasicNameValuePair("fecnacimiento", String.valueOf(datos.elementAt(9))));
        valores.add(new BasicNameValuePair("sexo", String.valueOf(datos.elementAt(10))));
        valores.add(new BasicNameValuePair("profesion", String.valueOf(datos.elementAt(11))));
        valores.add(new BasicNameValuePair("estatus", String.valueOf(datos.elementAt(12))));

        campos.add(new BasicNameValuePair("cedula", String.valueOf(datos.elementAt(0))));
        campos.add(new BasicNameValuePair("idciudad", String.valueOf(datos.elementAt(1))));
        campos.add(new BasicNameValuePair("nombre", String.valueOf(datos.elementAt(2))));
        campos.add(new BasicNameValuePair("apellido", String.valueOf(datos.elementAt(3))));
        campos.add(new BasicNameValuePair("tlfcelular", String.valueOf(datos.elementAt(4))));
        campos.add(new BasicNameValuePair("tlffijo", String.valueOf(datos.elementAt(5))));
        campos.add(new BasicNameValuePair("edocivil", String.valueOf(datos.elementAt(6))));
        campos.add(new BasicNameValuePair("direccion", String.valueOf(datos.elementAt(7))));
        campos.add(new BasicNameValuePair("correo", String.valueOf(datos.elementAt(8))));
        campos.add(new BasicNameValuePair("fecnacimiento", String.valueOf(datos.elementAt(9))));
        campos.add(new BasicNameValuePair("sexo", String.valueOf(datos.elementAt(10))));
        campos.add(new BasicNameValuePair("profesion", String.valueOf(datos.elementAt(11))));
        campos.add(new BasicNameValuePair("estatus", String.valueOf(datos.elementAt(12))));


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

    public Persona buscarEstadoSolicitud(String cedula)
    {
        Persona persona = new Persona();
        HttpClient httpclient = new DefaultHttpClient();

        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://"+ip+"/persona/buscar?cedula="+cedula);
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
                    JSONArray serSolicitud = json.getJSONArray("personas");
                    for(int i=0; i < serSolicitud.length(); i++){
                        persona.setNombre((String) serSolicitud.getJSONObject(i).get("nombre"));
                        persona.setApellido((String) serSolicitud.getJSONObject(i).get("apellido"));
                        persona.setCedula((String) serSolicitud.getJSONObject(i).get("cedula"));
                        persona.setDireccion((String) serSolicitud.getJSONObject(i).get("direccion"));
                        persona.setFechaNacimiento(convertirFecha((String) serSolicitud.getJSONObject(i).get("fecnacimiento")));
                        persona.setCelular( serSolicitud.getJSONObject(i).getString("tlfcelular"));
                        persona.setFijo(serSolicitud.getJSONObject(i).getString("tlffijo"));
                        persona.setProfesion(serSolicitud.getJSONObject(i).getString("profesion"));
                        persona.setEstatus( serSolicitud.getJSONObject(i).getString("estatus"));
                        //Log.e("ESTATUS", serSolicitud.getJSONObject(i).getString("estatus"));
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                    persona = null;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return persona;
    }

    public String buscarFechaVisita(String cedula)
    {
        String fechaString = "";
        Visita visita = new Visita();
        HttpClient httpclient = new DefaultHttpClient();

        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://"+ip+"/visita/buscar?cedulapersona="+cedula);
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
                    JSONArray serVisita = json.getJSONArray("visitas");
                    for(int i=0; i < serVisita.length(); i++){
                        visita.setCodigo(serVisita.getJSONObject(i).get("codigo").toString());
                        fechaString = String.valueOf(serVisita.getJSONObject(i).get("fecha").toString());
                        //visita.setFecha();
                        //Log.e("Fecha Visita", (String.valueOf(serVisita.getJSONObject(i).get("fecha"))));
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return fechaString;
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

    public Date convertirFecha(String fecha){
        Calendar calendario = Calendar.getInstance();
        Calendar calendarioActual = Calendar.getInstance();
        String fechaFraccionada[] = fecha.split("-");
        calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.YEAR));
        calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
        calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.DAY_OF_MONTH));


        return calendario.getTime();
    }
}
