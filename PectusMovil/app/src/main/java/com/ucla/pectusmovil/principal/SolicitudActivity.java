package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.ucla.pectusmovil.R;

public class SolicitudActivity extends Activity {

    private Spinner spTipoSolicitud;
    private Button continuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("Tipo de Solicitud");
        getActionBar().setIcon(R.drawable.pectus_logo);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));

        spTipoSolicitud = (Spinner) findViewById(R.id.spTipoSolicitud);
        continuar = (Button) findViewById(R.id.btSiguiente);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = spTipoSolicitud.getSelectedItemPosition();
                if (pos == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "Seleccione un Tipo de Solicitud", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {

                if (spTipoSolicitud.getSelectedItem().toString().equalsIgnoreCase("Solicitar Cita")){

                    startActivity(new Intent(SolicitudActivity.this, SolicitudCita.class));

                } else if (spTipoSolicitud.getSelectedItem().toString().equalsIgnoreCase("Solicitar Charla")){

                    startActivity(new Intent(SolicitudActivity.this, SolicitudCharla.class));
                }
            }}
        });

        //Creamos el adaptador y le decimos que tome los objetos desde el Array llamado...
        ArrayAdapter<CharSequence> adaptadorTipoSolicitud = ArrayAdapter.createFromResource(this,R.array.TipoSolicitud,android.R.layout.simple_spinner_item);
        //Añadimos el layout para el menú
        adaptadorTipoSolicitud.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Le indicamos al spinner el adaptador a usar
        spTipoSolicitud.setAdapter(adaptadorTipoSolicitud);

        spTipoSolicitud.setPrompt("Seleccione un Tipo...");
        //Le indicamos al spinner el adaptador a usar
        spTipoSolicitud.setAdapter(
                new AdaptadorSpinnerNadaSeleccionado(
                        adaptadorTipoSolicitud,
                        R.layout.spinner_solicitud_nada_seleccionado,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_solicitud, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
