package com.ucla.pectusmovil.servicio;

import android.content.Context;

import com.ucla.pectusmovil.modelo.Ciudad;
import com.ucla.pectusmovil.modelo.Estado;

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
 * Created by ENOCK on 05/02/2015.
 */
public class ServicioCiudad {

    private String ip = "192.168.1.198:5000";
    private Context c;


    public ServicioCiudad(){
        super();
    }


    public List<Ciudad> buscarCiudades(){

        List<Ciudad> listaCiudades = new ArrayList<Ciudad>();


        HttpClient httpclient = new DefaultHttpClient();

        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://"+ ip+"/ciudad/todos");
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
                    JSONArray serCiudad = json.getJSONArray("ciudades");
                    for(int i=0; i < serCiudad.length(); i++){
                        Ciudad ciudad = new Ciudad();
                        ciudad.setNombre((String) serCiudad.getJSONObject(i).get("nombre"));
                        ciudad.setId((Integer) serCiudad.getJSONObject(i).get("id"));

                        Estado estado = new Estado();
                        estado.setId((Integer) serCiudad.getJSONObject(i).getJSONObject("estado").get("id"));
                        estado.setNombre((String) serCiudad.getJSONObject(i).getJSONObject("estado").get("nombre"));

                        ciudad.setEstado(estado);
                        listaCiudades.add(ciudad);

                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaCiudades;
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
