package com.ucla.pectusmovil.servicio;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by ENOCK on 27/01/2015.
 */
public class RestClient {

    public enum RequestMethod {
        GET,
        POST
    }

    public int responseCode = 0;
    public String message;
    public String response;

    public Object Execute(RequestMethod method, String url, ArrayList<NameValuePair> headers, ArrayList<NameValuePair> params) throws Exception {
        Object ok= false;
        //Log.e("progreso de Cita  ", "Dentro del restClient ");
        switch (method) {
            case GET: {

                //Log.e("progreso de Cita  ", "Dentro del GET ");
                // add parameters
                String combinedParams = "";
                if (params != null) {
                    combinedParams += "?";
                    for (NameValuePair p : params) {
                        String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(), "UTF-8");
                        if (combinedParams.length() > 1)
                            combinedParams += "&" + paramString;
                        else
                            combinedParams += paramString;

                        //Log.e("tira de parametros:  ", combinedParams);
                    }
                }
                HttpGet request = new HttpGet(url + combinedParams);
                // add headers
                if (headers != null) {
                    headers = addCommonHeaderField(headers);
                    for (NameValuePair h : headers)
                        request.addHeader(h.getName(), h.getValue());
                }
                ok = executeRequest(request, url);
                break;
            }
            case POST: {

                //Log.e("progreso de Cita  ", "Dentro del POST ");
                HttpPost request = new HttpPost(url);
                // add headers
                if (headers != null) {
                    headers = addCommonHeaderField(headers);
                    for (NameValuePair h : headers)
                        request.addHeader(h.getName(), h.getValue());
                }
                if (params != null)
                    request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                ok = executeRequest(request, url);
                break;
            }
        }
        return ok;
    }

    private ArrayList<NameValuePair> addCommonHeaderField(ArrayList<NameValuePair> _header) {
        _header.add(new BasicNameValuePair("Content-Type", "application/x-www-form-urlencoded"));
        return _header;
    }

    private Object executeRequest(HttpUriRequest request, String url) {

        Object dato = new Object();
        dato = false;
        //Log.e("progreso de Cita  ", "executeRequest ");
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse;
        try {
            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();
            HttpEntity entity = httpResponse.getEntity();

            if (entity != null) {
                InputStream instream = entity.getContent();
                response = convertStreamToString(instream);
                try{
                    JSONObject json = new JSONObject(response);
                    dato= json.get("ok");
                    //Log.v("final del pago", (String.valueOf(dato)));
                }catch(JSONException e){
                    e.printStackTrace();
                }
                instream.close();

            }
        } catch (Exception e) {
        }
        return dato;
    }

    private static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
        } catch (IOException e) {
        }
        return sb.toString();
    }
}