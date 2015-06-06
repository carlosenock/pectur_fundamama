package com.ucla.pectusmovil.controlador.evento;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.controlador.calculos.Calculos;
import com.ucla.pectusmovil.modelo.Evento;
import com.ucla.pectusmovil.servicio.ServicioEvento;

import java.util.ArrayList;
import java.util.List;

public class TodosEventos extends Activity {

    private List<Evento> listaEventos;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_todos_eventos);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("Calendario de Eventos");
        getActionBar().setIcon(R.drawable.pectus_logo);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));

        Calculos filtro = new Calculos();

        //llamada a servicio que retorna los eventos
        listaEventos = filtro.ordenarListaEventoFecha(new ServicioEvento().BuscarEvento(), "antes");

        //Collections.reverse(listaEventos);


        setContentView(R.layout.activity_todos_eventos);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("Calendario de Eventos");
        getActionBar().setIcon(R.drawable.pectus_logo);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));

        CustomListaEventoTotal adapter = new
                CustomListaEventoTotal(TodosEventos.this, listaEventos);

        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(TodosEventos.this, DetalleEvento.class);
                intent.putExtra("eventoSeleccionado", listaEventos.get(position));
                startActivity(intent);

            }


        });
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
