package com.ucla.pectusmovil.servicio;

import com.ucla.pectusmovil.modelo.Voluntario;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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
import java.util.List;

/**
 * Created by DarkGooz on 20-11-2014.
 */
public class ServicioVoluntario {

    private String ip = "192.168.1.198:5000";

    public List<Voluntario> buscarVoluntarios()
    {
        List<Voluntario> listaVoluntario = new ArrayList<Voluntario>();
        HttpClient httpclient = new DefaultHttpClient();

        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://"+ip+"/voluntario/todos");
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
                    JSONArray serEvento = json.getJSONArray("voluntarios");
                    for(int i=0; i < serEvento.length(); i++){
                        Voluntario voluntario = new Voluntario();

                        voluntario.setNombre((String) serEvento.getJSONObject(i).get("nombre"));
                        voluntario.setCedula((String) serEvento.getJSONObject(i).get("cedula"));
                        voluntario.setApellido((String) serEvento.getJSONObject(i).get("apellido"));

                        voluntario.setDireccion((String) serEvento.getJSONObject(i).get("direccion"));

                        listaVoluntario.add(voluntario);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaVoluntario;
    }

    public boolean buscarVoluntario(String cedula)
    {

        boolean dato =false;
        Voluntario voluntario = new Voluntario();
        HttpClient httpclient = new DefaultHttpClient();

        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://"+ip+"/voluntario/buscar?cedula="+cedula);
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
                    JSONArray serEvento = json.getJSONArray("voluntarios");
                    for(int i=0; i < serEvento.length(); i++){
                        voluntario.setNombre((String) serEvento.getJSONObject(i).get("nombre"));
                        voluntario.setCedula((String) serEvento.getJSONObject(i).get("cedula"));
                        voluntario.setApellido((String) serEvento.getJSONObject(i).get("apellido"));
                        voluntario.setDireccion((String) serEvento.getJSONObject(i).get("direccion"));
                        dato = true;
                        //Log.e("Voluntario", (String) serEvento.getJSONObject(i).get("cedula"));

                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return dato;
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
