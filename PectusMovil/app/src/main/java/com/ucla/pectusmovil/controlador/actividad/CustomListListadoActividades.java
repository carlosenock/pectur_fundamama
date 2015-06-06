package com.ucla.pectusmovil.controlador.actividad;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.modelo.Actividad;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by ENOCK on 19/11/2014.
 */
public class CustomListListadoActividades extends ArrayAdapter<Actividad> {

    private final List<Actividad> listaActividad;
    private final Activity context;
    DateFormat df2 = DateFormat.getDateInstance(DateFormat.SHORT);
    public CustomListListadoActividades(Activity context,
                                  List<Actividad> listaActividad) {
        super(context, R.layout.item_evento, listaActividad);
        this.context = context;
        this.listaActividad = listaActividad;
        //Log.e("CustomList  ", "ListadoActividades");
        //Log.e("CustomList  ", "Nombre Lugar"+listaActividad.get(0).getDescripcion());
        //    this.lista = web;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.item_evento, null, true);

        DateFormat mmm = new SimpleDateFormat("MMM");
        DateFormat dd = new SimpleDateFormat("dd");
        String vacio = new String("");

        String[] nombreActividad = {listaActividad.get(position).getLugar().getNombre()};

        TextView mes=(TextView) rowView.findViewById(R.id.txtmes);
        mes.setText(mmm.format(listaActividad.get(position).getFechainicio()));

        TextView dia=(TextView) rowView.findViewById(R.id.txtdia);
        dia.setText(dd.format(listaActividad.get(position).getFechainicio()));
        TextView txtDescrip = (TextView) rowView.findViewById(R.id.txtdetalle);
        txtDescrip.setText(listaActividad.get(position).getLugar().getNombre());
        TextView txtLugar = (TextView) rowView.findViewById(R.id.txtlugar);


        return rowView;
    }
}
