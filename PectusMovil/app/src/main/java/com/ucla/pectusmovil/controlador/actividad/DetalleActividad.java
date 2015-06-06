package com.ucla.pectusmovil.controlador.actividad;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.modelo.Actividad;
import com.ucla.pectusmovil.modelo.TipoActividad;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class DetalleActividad extends FragmentActivity {
    ViewPager vp;
    private Actividad actividadSeleccionada = new Actividad();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_actividad);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("Detalle de Actividad");
        getActionBar().setIcon(R.drawable.pectus_logo);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));

        actividadSeleccionada = (Actividad)getIntent().getSerializableExtra("actividadSeleccionado");


        TextView informacion = ((TextView) findViewById(R.id.detalleActividad));
        informacion.setText((CharSequence)actividadSeleccionada.getDescripcion());


        TextView lugar = ((TextView) findViewById(R.id.tvTipoActividad));
        TipoActividad tipo = actividadSeleccionada.getIdSolicitudActividad().getIdTipoActividad();

        lugar.setText(tipo.getNombre());

        TextView direccionEvento = (TextView) findViewById(R.id.tvInstitucionActividad);
        StringBuilder direccionCompleta = new StringBuilder();
        direccionCompleta.append(actividadSeleccionada.getLugar().getDireccion());
        direccionCompleta.append(" ");
        direccionCompleta.append(actividadSeleccionada.getLugar().getNombre());
        direccionCompleta.append(" ");
        direccionCompleta.append(actividadSeleccionada.getLugar().getCiudad().getNombre());
        direccionCompleta.append(" ");
        direccionCompleta.append(actividadSeleccionada.getLugar().getCiudad().getEstado().getNombre());
        direccionEvento.setText((CharSequence) direccionCompleta);
        TextView fechaActividad = (TextView) findViewById(R.id.tvInfoActividad);
        SimpleDateFormat formatoFecha = new SimpleDateFormat(" EEEE dd 'de' MMMM 'del' yyyy h:mm a",new Locale("es_ES"));
        String fechaTotal = formatoFecha.format(actividadSeleccionada.getFechainicio());
        fechaActividad.setText((CharSequence)fechaTotal);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detalle_actividad, menu);
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
