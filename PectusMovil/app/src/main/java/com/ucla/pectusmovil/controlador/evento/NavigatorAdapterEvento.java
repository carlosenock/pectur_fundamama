package com.ucla.pectusmovil.controlador.evento;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.modelo.Item_objct;
import com.ucla.pectusmovil.modelo.Patrocinador;

import java.util.ArrayList;

/**
 * Created by DarkGooz on 03-11-2014.
 */
public class NavigatorAdapterEvento extends BaseAdapter{
    private Fragment activity;
    ArrayList<Patrocinador> arrayitms;

    public NavigatorAdapterEvento(Fragment activity,ArrayList<Patrocinador>  listarry) {
        super();
        this.activity = activity;
        this.arrayitms=listarry;
    }
    //Retorna objeto Item_objct del array list
    @Override
    public Object getItem(int position) {
        return arrayitms.get(position);
    }
    public int getCount() {
        // TODO Auto-generated method stub
        return arrayitms.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    //Declaramos clase estatica la cual representa a la fila
    public static class Fila
    {
        TextView titulo_itm;
        ImageView icono;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Fila view;
        LayoutInflater inflator = activity.getLayoutInflater(Bundle.EMPTY);
        if(convertView==null)
        {
            view = new Fila();
            //Creo objeto item y lo obtengo del array
            Patrocinador itm=arrayitms.get(position);
            convertView = inflator.inflate(R.layout.items_patrocinadores, null);
            //Titulo
            view.titulo_itm = (TextView) convertView.findViewById(R.id.listaPatrocinadorEventoItem);
            //Seteo en el campo titulo el nombre correspondiente obtenido del objeto
            view.titulo_itm.setText((CharSequence)itm.getNombre());
            //Icono
            //view.icono = (ImageView) convertView.findViewById(R.id.icon);
            //Seteo el icono
         //   view.icono.setImageResource(itm.getIcono());
            convertView.setTag(view);
        }
        else
        {
            view = (Fila) convertView.getTag();
        }
        return convertView;
    }
}
