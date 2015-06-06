package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.modelo.SolicitudActividad;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ENOCK on 08/02/2015.
 */
public class CustomListConsultarSolicitudesCharlas extends ArrayAdapter<SolicitudActividad> {

    private final Activity context;

    private List<SolicitudActividad> listaSolicitudActividad;

    public CustomListConsultarSolicitudesCharlas(Activity context, List<SolicitudActividad> listaSolicitudesActividad) {

        super(context, R.layout.lista_solicitud_charlas, listaSolicitudesActividad);

        this.context = context;
        this.listaSolicitudActividad = listaSolicitudesActividad;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.lista_solicitud_charlas, null, true);


        SolicitudActividad solicitudActividad = listaSolicitudActividad.get(position);

        TextView descripcion = (TextView) rowView.findViewById(R.id.tvDescripcionSolicitud);
        TextView nombre = (TextView) rowView.findViewById(R.id.tvNombreSolicitante);
        TextView fecha = (TextView) rowView.findViewById(R.id.tvFechaSolicitud);
        TextView estatus = (TextView) rowView.findViewById(R.id.tvEstatusSolicitud);

        descripcion.setText((CharSequence)solicitudActividad.getDescripcion());
        nombre.setText((CharSequence)solicitudActividad.getNomsolicitante());

        SimpleDateFormat formateadorFecha = new SimpleDateFormat("yyyy-mm-dd");
        Date date = new Date();
        //date = formateadorFecha.parse(date.toString());


        Date fechaDate = solicitudActividad.getFecha();
        SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yy");

        String fechaString = formato.format(fechaDate);
        fecha.setText(fechaString);

        String letra ="A";
        char estatusChar = letra.charAt(0);
        String estat =String.valueOf(solicitudActividad.getEstatus());
        if(estat.equalsIgnoreCase("A")) {
           estatus.setText("Solicitud Aceptada");
        }else if(estat.equalsIgnoreCase("C")) {
           estatus.setText("Solicitud En Revisión");
        }else if(estat.equalsIgnoreCase("R")) {
            estatus.setText("Solicitud En Rechazada");
        }
        return rowView;
    }
}
