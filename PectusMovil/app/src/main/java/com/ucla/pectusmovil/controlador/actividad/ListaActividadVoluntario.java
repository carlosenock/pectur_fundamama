package com.ucla.pectusmovil.controlador.actividad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.modelo.Actividad;
import com.ucla.pectusmovil.servicio.ServicioActividad;

import java.util.ArrayList;
import java.util.List;

public class ListaActividadVoluntario extends Activity {

    private ListView list;
    private List<Actividad> listaOrdenada;
    private List<Actividad> actividadesVoluntario;
    private String voluntarioSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_actividad_voluntario);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("Actividades Pendientes");
        getActionBar().setIcon(R.drawable.pectus_logo);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));

        ServicioActividad serActividad = new ServicioActividad();
        SharedPreferences preferencias=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        voluntarioSesion=  preferencias.getString("voluntario","");

        actividadesVoluntario = new ArrayList<>();
        listaOrdenada = new ArrayList<>();

        //Llamamos al servicio que nos trae la lista de actividades dado un numero de cedula
        actividadesVoluntario = serActividad.buscarActividadesVoluntario(voluntarioSesion);

        //Collections.reverse(actividadesVoluntario);

        CustomListListadoActividades adapter = new
                CustomListListadoActividades(ListaActividadVoluntario.this, actividadesVoluntario);

        list = (ListView) findViewById(R.id.lvlistaActividadVoluntario);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(ListaActividadVoluntario.this, DetalleActividad.class);
                intent.putExtra("actividadSeleccionado", actividadesVoluntario.get(position));
                startActivity(intent);
            }
        });
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
