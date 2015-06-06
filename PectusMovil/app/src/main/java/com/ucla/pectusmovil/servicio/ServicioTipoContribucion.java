package com.ucla.pectusmovil.servicio;

import com.ucla.pectusmovil.modelo.PatrocinanteEvento;
import com.ucla.pectusmovil.modelo.TipoContribucion;

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
 * Created by DarkGooz on 01-12-2014.
 */
public class ServicioTipoContribucion {
    private String ip = "192.168.1.198:5000";


    public ServicioTipoContribucion() {
    }

    public List<TipoContribucion> buscarTipoContribucion()
    {
        List<TipoContribucion> listaTipoContribucion = new ArrayList<TipoContribucion>();
        HttpClient httpclient = new DefaultHttpClient();

        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://"+ip+"/tipo-colaboracion/todos");
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
                    JSONArray serEvento = json.getJSONArray("tipocolaboracion");
                    for(int i=0; i < serEvento.length(); i++){
                        TipoContribucion contribucion = new TipoContribucion();

                        contribucion.setNombre((String) serEvento.getJSONObject(i).get("nombre"));
                        contribucion.setId((Integer) serEvento.getJSONObject(i).get("id"));






                        listaTipoContribucion.add(contribucion);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaTipoContribucion;
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


    public void agregarContribucion(PatrocinanteEvento contribucion){
        try {
            HttpClient httpclient = new DefaultHttpClient();
            PatrocinanteEvento contri = new PatrocinanteEvento();
            contri = contribucion;

            HttpGet httpget = new HttpGet("http://"+ip+"/colaboracion/agregar?idevento=" + contribucion.getEvento().getId().toString() + "&rif=" + contribucion.getPatrocinador().getRif() + "&cantidad=" + contribucion.getCantidad() + "&idtipocolaboracion=" + contribucion.getTipoContribucion().getId().toString());
            HttpResponse response;
            httpclient.execute(httpget);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
