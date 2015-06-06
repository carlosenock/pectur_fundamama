package com.ucla.pectusmovil.modelo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucla.pectusmovil.R;

import java.util.List;

/**
 * Created by ENOCK on 24/10/2014.
 */
public class AdaptadorActividadesVoluntario extends ArrayAdapter {

    Activity context;
    List<Actividad> datos;


    @SuppressLint("NewApi")
    public AdaptadorActividadesVoluntario (Activity context, List<Actividad> datos) {

        super(context, R.layout.activity_avoluntario,datos);
        this.context = context;
        this.datos=datos; //==============================================>>>

    }
/*
    public View getView(int positions,View convertView, ViewGroup parent){
        LayoutInflater inflater=context.getLayoutInflater();
        View item= inflater.inflate(R.layout.activity_avoluntario, null);

        TextView iduser=(TextView) item.findViewById(R.id.txtcodActividad);
        iduser.setText("Id: "+datos[positions].getId());

        TextView monto=(TextView) item.findViewById(R.id.txtactividad);
        monto.setText("Actividad: "+datos[positions].getActividad());

        ImageView img=(ImageView) item.findViewById(R.id.imgMOROSO);
        img.setImageResource(datos[positions].getImg());



        return (item);

    }
*/
}