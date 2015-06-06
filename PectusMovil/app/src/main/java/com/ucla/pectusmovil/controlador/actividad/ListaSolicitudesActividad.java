package com.ucla.pectusmovil.controlador.actividad;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.controlador.calculos.Calculos;
import com.ucla.pectusmovil.modelo.SolicitudActividad;
import com.ucla.pectusmovil.principal.CustomListConsultarSolicitudesCharlas;
import com.ucla.pectusmovil.servicio.ServicioSolicitudActividad;

import java.util.ArrayList;
import java.util.List;

public class ListaSolicitudesActividad extends Activity {

    private ListView listaSolicitudesCharla;
    private List<SolicitudActividad> listaSolicitudCharla;
    private Calculos filtro;

    private TextView alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_solicitudes_actividad);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getActionBar().setTitle("Solicitudes");
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));
        getActionBar().setIcon(R.drawable.pectus_logo);

        listaSolicitudCharla = new ArrayList<>();
        listaSolicitudesCharla = (ListView)findViewById(R.id.lvSolicitudesCharla);
        alerta = (TextView)findViewById(R.id.tvAlerta);

        filtro = new Calculos();
        //llamada a servicio que retorna los eventos
        listaSolicitudCharla = filtro.ordenarSolicitudActividad(new ServicioSolicitudActividad().buscarSolicitudCharla(), 1, "antes");
        if(listaSolicitudCharla.size()>0){


            CustomListConsultarSolicitudesCharlas adapter = new
                    CustomListConsultarSolicitudesCharlas(ListaSolicitudesActividad.this, listaSolicitudCharla);

            listaSolicitudesCharla.setAdapter(adapter);

        }else {
            alerta.setVisibility(View.VISIBLE);
            alerta.setText("No hay solicitudes.");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_solicitudes_actividad, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
