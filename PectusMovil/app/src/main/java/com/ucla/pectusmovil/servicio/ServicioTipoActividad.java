package com.ucla.pectusmovil.servicio;

import android.content.Context;

import com.ucla.pectusmovil.modelo.Comision;
import com.ucla.pectusmovil.modelo.TipoActividad;

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
 * Created by ENOCK on 08/02/2015.
 */
public class ServicioTipoActividad {

    private String ip = "192.168.1.198:5000";
    private Context c;

    public ServicioTipoActividad(){
        super();
    }

    public List<TipoActividad> buscarTiposActividad(){

        List<TipoActividad> listaTipoActividad = new ArrayList<TipoActividad>();


        HttpClient httpclient = new DefaultHttpClient();

        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://"+ ip+"/tipo-actividad/todos");
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
                    JSONArray serActividad = json.getJSONArray("tipoactividades");
                    for(int i=0; i < serActividad.length(); i++){
                        TipoActividad tipoActividad = new TipoActividad();
                        tipoActividad.setDescripcion((String) serActividad.getJSONObject(i).get("descripcion"));
                        tipoActividad.setNombre((String) serActividad.getJSONObject(i).get("nombre"));
                        tipoActividad.setId((Integer) serActividad.getJSONObject(i).get("id"));

                        Comision comision = new Comision();
                        comision.setId((Integer) serActividad.getJSONObject(i).getJSONObject("comision").get("id"));
                        comision.setDescripcion((String) serActividad.getJSONObject(i).getJSONObject("comision").get("descripcion"));
                        comision.setDescripcion((String) serActividad.getJSONObject(i).get("descripcion"));
                        tipoActividad.setComision(comision);

                        listaTipoActividad.add(tipoActividad);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaTipoActividad;
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
