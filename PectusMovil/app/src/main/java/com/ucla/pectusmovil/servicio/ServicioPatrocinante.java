package com.ucla.pectusmovil.servicio;

import com.ucla.pectusmovil.modelo.Patrocinador;

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
public class ServicioPatrocinante {

    private String ip = "192.168.1.198:5000";


    public ServicioPatrocinante() {
    }

    public List<Patrocinador> buscarPatrocinantes()
    {
        List<Patrocinador> listaPatrocinante = new ArrayList<Patrocinador>();
        HttpClient httpclient = new DefaultHttpClient();

        // Preparar un objeto request via method Get
        HttpGet httpget = new HttpGet("http://"+ip+"/patrocinador/todos");
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
                    JSONArray serEvento = json.getJSONArray("patrocinadores");
                    for(int i=0; i < serEvento.length(); i++){
                        Patrocinador patrocinador = new Patrocinador();

                        patrocinador.setNombre((String) serEvento.getJSONObject(i).get("nombre"));
                        patrocinador.setRif((String) serEvento.getJSONObject(i).get("rif"));
                        patrocinador.setCorreoRepresentante((String) serEvento.getJSONObject(i).get("nombrerepresentante"));
                        patrocinador.setTelefonoCelular((String) serEvento.getJSONObject(i).get("tlfcelular"));
                        patrocinador.setDireccion((String) serEvento.getJSONObject(i).get("direccion"));
                        patrocinador.setCorreoRepresentante((String) serEvento.getJSONObject(i).get("correorepresentante"));
                        patrocinador.setTelefonoFijo((String) serEvento.getJSONObject(i).get("tlffijo"));
                        patrocinador.setTelefonoRepresentante((String) serEvento.getJSONObject(i).get("tlfrepresentante"));






                        listaPatrocinante.add(patrocinador);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaPatrocinante;
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
