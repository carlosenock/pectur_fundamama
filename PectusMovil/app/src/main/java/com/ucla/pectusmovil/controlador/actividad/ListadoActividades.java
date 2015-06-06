package com.ucla.pectusmovil.controlador.actividad;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.controlador.calculos.Calculos;
import com.ucla.pectusmovil.modelo.Actividad;
import com.ucla.pectusmovil.servicio.ServicioActividad;

import java.util.ArrayList;
import java.util.List;

public class ListadoActividades extends Activity {

    private ListView list;
    private List<Actividad> listaActividades;
    private List<Actividad> listaCharlas;
    private List<Actividad> listaOrdenada;
    private ServicioActividad serActividad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_actividades);
        listaActividades = new ArrayList<>();
        listaCharlas = new ArrayList<>();
        serActividad = new ServicioActividad();
        listaOrdenada = new ArrayList<>();


        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("Calendario de Charlas");
        getActionBar().setIcon(R.drawable.pectus_logo);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));
        Calculos filtro = new Calculos();

        //llamada a servicio que retorna los eventos
        listaActividades = filtro.ordenarListaActividadFecha(new ServicioActividad().buscarActividad(), "antes");


        listaCharlas = filtro.ordenarListaCharlas(listaActividades,1);

        //Collections.reverse(listaCharlas);

        //listaActividades = serActividad.buscarActividad();
        CustomListListadoActividades adapter = new
                CustomListListadoActividades(ListadoActividades.this, listaCharlas);

        list = (ListView) findViewById(R.id.lvListaActividades);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(ListadoActividades.this, DetalleActividad.class);
                intent.putExtra("actividadSeleccionado", listaCharlas.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.listado_actividades, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
