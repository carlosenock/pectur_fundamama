package com.ucla.pectusmovil.controlador.evento;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.modelo.Evento;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetalleEvento extends FragmentActivity {
    ViewPager vp;


    private Evento eventoSeleccionado = new Evento();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_evento);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setIcon(R.drawable.pectus_logo);
        getActionBar().setTitle("Detalles del Evento");
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));

        eventoSeleccionado = (Evento)getIntent().getSerializableExtra("eventoSeleccionado");

        TextView textoDetalle = (TextView) findViewById(R.id.detalleEvento);

        TextView descEvento = (TextView) findViewById(R.id.detalleEvento);
        descEvento.setText((CharSequence)eventoSeleccionado.getDescripcion());

        TextView tituloEvento=(TextView) findViewById(R.id.tvTituloEvento);
        tituloEvento.setText((CharSequence)eventoSeleccionado.getNombre());

        TextView direccionEvento = (TextView) findViewById(R.id.tvLugarEvento);
        StringBuilder direccionCompleta = new StringBuilder();
        direccionCompleta.append("Lugar del Evento: ");
        direccionCompleta.append(eventoSeleccionado.getLugar().getDireccion());
        direccionCompleta.append(" ");
        direccionCompleta.append(eventoSeleccionado.getLugar().getNombre());
        direccionCompleta.append(" ");
        direccionCompleta.append(eventoSeleccionado.getLugar().getCiudad().getNombre());
        direccionCompleta.append(" ");
        direccionCompleta.append(eventoSeleccionado.getLugar().getCiudad().getEstado().getNombre());
        direccionEvento.setText((CharSequence) direccionCompleta);
        TextView fechaEvento = (TextView) findViewById(R.id.tvFechaEvento);
        SimpleDateFormat formatoFecha = new SimpleDateFormat(" EE dd 'de' MMMM 'del' yyyy",new Locale("es_ES"));//" EEEE dd 'de' MMMM 'del' yyyy"
        String fechaTotal = formatoFecha.format(eventoSeleccionado.getFecha());
        fechaEvento.setText((CharSequence)fechaTotal);



        /*
        vp = (ViewPager) findViewById(R.id.vp_biodata);
        ControllerFragment cf = new ControllerFragment(getSupportFragmentManager(), getFragments());
        vp.setAdapter(cf);*/


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

    @Override
    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {

    //    TextView textoTitulo = (TextView) findViewById(R.id.tituloEvento);
        TextView textoDetalle = (TextView) findViewById(R.id.detalleEvento);
        // textoTitulo.setText("eeeeeeeee");
        // textoDetalle.setText(event.getDescripcion().toString());
        return super.onCreateView(name, context, attrs);
    }
}
