package com.ucla.pectusmovil.controlador.evento;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.ucla.pectusmovil.modelo.Evento;
import com.ucla.pectusmovil.servicio.ServicioEvento;

import java.util.ArrayList;
import java.util.List;

public class ListaEventoVoluntario extends Activity {

    private ListView list;
    private List<Evento> listaEventos;
    private List<Evento> eventosVoluntario;
    private String voluntarioSesion;
    List<Evento> listaOrdenada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_evento_voluntario);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("Eventos Pendientes");
        getActionBar().setIcon(R.drawable.pectus_logo);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));

        ServicioEvento servEvento = new ServicioEvento();
        Calculos filtro = new Calculos();
        SharedPreferences preferencias=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        voluntarioSesion=  preferencias.getString("voluntario","");

        //llamada a servicio que retorna los eventos
        listaEventos =  filtro.ordenarListaEventoFecha(new ServicioEvento().BuscarEvento(), "antes");

        eventosVoluntario = new ArrayList<>();
        listaOrdenada = new ArrayList<>();

        eventosVoluntario = filtro.ordenarEventosVoluntario(listaEventos,voluntarioSesion);

        //Collections.reverse(eventosVoluntario);

                CustomListaEventoTotal adapter = new
                        CustomListaEventoTotal(ListaEventoVoluntario.this, eventosVoluntario);

        list=(ListView)findViewById(R.id.listaEventoVoluntario);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(ListaEventoVoluntario.this, DetalleEvento.class);
                intent.putExtra("eventoSeleccionado", eventosVoluntario.get(position));
                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.lista_evento_voluntario, menu);
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

    public List<Evento> getListaEventos() {
        if(listaEventos == null){
            listaEventos = new ArrayList<Evento>();
        }
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }
}
