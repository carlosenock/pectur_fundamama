package com.ucla.pectusmovil.servicio;

import com.ucla.pectusmovil.modelo.Ciudad;
import com.ucla.pectusmovil.modelo.Estado;
import com.ucla.pectusmovil.modelo.Evento;
import com.ucla.pectusmovil.modelo.Lugar;
import com.ucla.pectusmovil.modelo.Voluntario;
import com.ucla.pectusmovil.modelo.VoluntarioEvento;

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by DarkGooz on 13-11-2014.
 */
public class ServicioEvento {



    // static AsyncHttpClient httpclient = new AsyncHttpClient();

    private String ip = "192.168.1.198:5000";

    public ServicioEvento (){

    }

    public void editarEvento(Evento evento){
        try {
            HttpClient httpclient = new DefaultHttpClient();
            Evento eventoS = new Evento();
            eventoS = evento;

            HttpGet httpget = new HttpGet("http://"+ip+"/evento/editar?id=" + eventoS.getId().toString() + "&observaciones=" + eventoS.getObservacion());
            HttpResponse response;
            httpclient.execute(httpget);
        }catch (Exception e) {
            e.printStackTrace();
        }
        }

    public List<Evento> BuscarEvento()
    {
        List<Evento> listaEvento = new ArrayList<Evento>();
        HttpClient httpclient = new DefaultHttpClient();

        // Preparar un objeto request via method Get
            HttpGet httpget = new HttpGet("http://"+ip+"/evento/todos");
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
                    JSONArray serEvento = json.getJSONArray("eventos");
                    for(int i=0; i < serEvento.length(); i++){
                        Evento evento = new Evento();
                        evento.setId((Integer) serEvento.getJSONObject(i).get("id"));
                        evento.setNombre((String) serEvento.getJSONObject(i).get("nombre"));
                        evento.setDescripcion((String) serEvento.getJSONObject(i).get("descripcion"));
                        evento.setFecha( convertirFecha((String) serEvento.getJSONObject(i).get("fecha")));
                        Lugar lugar = new Lugar();
                        lugar.setId((Integer) serEvento.getJSONObject(i).getJSONObject("lugar").get("id"));
                        lugar.setNombre((String) serEvento.getJSONObject(i).getJSONObject("lugar").get("nombre"));
                        lugar.setDireccion((String) serEvento.getJSONObject(i).getJSONObject("lugar").get("direccion"));
                        Ciudad ciudad = new Ciudad();
                        ciudad.setId((Integer) serEvento.getJSONObject(i).getJSONObject("lugar").getJSONObject("ciudad").get("id"));
                        ciudad.setNombre((String) serEvento.getJSONObject(i).getJSONObject("lugar").getJSONObject("ciudad").get("nombre"));
                        Estado estado = new Estado();
                        estado.setId((Integer) serEvento.getJSONObject(i).getJSONObject("lugar").getJSONObject("ciudad").getJSONObject("estado").get("id"));
                        estado.setNombre((String) serEvento.getJSONObject(i).getJSONObject("lugar").getJSONObject("ciudad").getJSONObject("estado").get("nombre"));
                        ciudad.setEstado(estado);
                        lugar.setCiudad(ciudad);
                        evento.setLugar(lugar);

                        JSONArray serVoluntario = serEvento.getJSONObject(i).getJSONArray("voluntarios");
                        List<Voluntario> listaVoluntarioEvento = new ArrayList<Voluntario>();
                        for(int j = 0; j<serVoluntario.length(); j++){
                            Voluntario voluntario = new Voluntario();

                            voluntario.setNombre((String) serVoluntario.getJSONObject(j).get("nombre"));
                            voluntario.setApellido((String) serVoluntario.getJSONObject(j).get("apellido"));
                            voluntario.setCedula((String) serVoluntario.getJSONObject(j).get("cedula"));
                         //   voluntario.setDireccion((String) serVoluntario.getJSONObject(i).get("direccion"));
                            VoluntarioEvento voluntarioEvento = new VoluntarioEvento();
                            voluntarioEvento.setVoluntario(voluntario);
                            voluntarioEvento.setEvento(evento);
                            listaVoluntarioEvento.add(voluntario);

                        }
                        evento.setVoluntarios(listaVoluntarioEvento);

                        listaEvento.add(evento);
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listaEvento;
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


