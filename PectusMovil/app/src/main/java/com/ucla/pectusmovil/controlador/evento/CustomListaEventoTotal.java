package com.ucla.pectusmovil.controlador.evento;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.modelo.Evento;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by DarkGooz on 21/10/2014.
 */
public class CustomListaEventoTotal extends ArrayAdapter<Evento>{

   private final List<Evento> listaEventos;
        private final Activity context;
        DateFormat df2 = DateFormat.getDateInstance(DateFormat.SHORT); 
        public CustomListaEventoTotal(Activity context,
                                      List<Evento> listaEventos) {
        super(context, R.layout.item_evento, listaEventos);
        this.context = context;
            this.listaEventos = listaEventos;
        //    this.lista = web;
    }
        @Override
        public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.item_evento, null, true);


            DateFormat mmm = new SimpleDateFormat("MMM");
            DateFormat dd = new SimpleDateFormat("dd");

            String vacio = new String("");


            TextView locacion=(TextView) rowView.findViewById(R.id.textVLocacion);
            locacion.setVisibility(TextView.GONE);

            TextView mes=(TextView) rowView.findViewById(R.id.txtmes);
            mes.setText(mmm.format(listaEventos.get(position).getFecha()));

            TextView dia=(TextView) rowView.findViewById(R.id.txtdia);
            dia.setText(dd.format(listaEventos.get(position).getFecha()));

            TextView txtTitle = (TextView) rowView.findViewById(R.id.txtdetalle);
            String[] nombresEventos = {listaEventos.get(position).getNombre()};

            TextView txtLugar = (TextView) rowView.findViewById(R.id.txtlugar);
            txtLugar.setText(vacio);


            txtTitle.setText((CharSequence)listaEventos.get(position).getNombre());

        // imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
