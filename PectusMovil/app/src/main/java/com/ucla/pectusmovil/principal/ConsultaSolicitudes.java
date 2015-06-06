package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.controlador.actividad.ListaSolicitudesActividad;
import com.ucla.pectusmovil.controlador.calculos.Calculos;
import com.ucla.pectusmovil.modelo.Persona;
import com.ucla.pectusmovil.modelo.SolicitudActividad;
import com.ucla.pectusmovil.modelo.Visita;
import com.ucla.pectusmovil.servicio.ServicioCita;
import com.ucla.pectusmovil.servicio.ServicioSolicitudActividad;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ConsultaSolicitudes extends Activity {
    private String idTipo;
    private EditText cedula;
    private TextView nombre;
    private TextView resultado;

    private TextView lbSolicitudFija;
    private TextView lbSolicitudEditable;
    private TextView lbfechaFija;
    private TextView lbfechaEditable;
    private TextView recomendacion;
    private Button consultar;
    private Spinner spTipoConsulta;
    private TextView label;
    private TextView textCedula;

    private Persona persona;
    private SolicitudCharla solicitudCharla;
    private List<SolicitudActividad> listaSolicitudCharla;
    private ServicioCita serCita;
    private ServicioSolicitudActividad serSolicitudCharla;
    private Calculos filtro;

    private Visita visita;

    private LinearLayout llResultadoCita;

    private ListView listaSolicitudesCharla;

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_solicitudes);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        getActionBar().setTitle("Consulta");
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));
        getActionBar().setIcon(R.drawable.pectus_logo);

        declaraciones();
        ocultar();
        spTipoConsulta = (Spinner) findViewById(R.id.spTipo);
        label = (TextView) findViewById(R.id.tvInsertInfo);
        textCedula = (TextView) findViewById(R.id.etCedulaConsultada);
        spTipoConsulta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int pos = spTipoConsulta.getSelectedItemPosition();
                if (pos == 0)
                {
                    label.setVisibility(View.VISIBLE);
                    textCedula.setVisibility(View.VISIBLE);
                    ocultar();
                }
                else if (pos == 1)
                {
                    ocultar();
                    textCedula.setText("");
                    label.setVisibility(View.INVISIBLE);
                    textCedula.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
        //Creamos el adaptador y le decimos que tome los objetos desde el Array llamado...
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Tipos,android.R.layout.simple_spinner_item);

        //Añadimos el layout para el menú
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Le indicamos al spinner el adaptador a usar
        spTipoConsulta.setPrompt("Seleccione un Tipo...");
        //Le indicamos al spinner el adaptador a usar
        spTipoConsulta.setAdapter(
                new AdaptadorSpinnerNadaSeleccionado(
                        adapter,
                        R.layout.spinner_solicitud_nada_seleccionado,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        //Le indicamos al spinner el adaptador a usar
        spTipoConsulta.setAdapter(adapter);


    }


    public void consultar (View view) {

        if (textCedula.getText().toString().trim().equals("")&&(spTipoConsulta.getSelectedItem().toString().equalsIgnoreCase("Consulta de Cita"))){

            Toast toast = Toast.makeText(getApplicationContext(), "Debes indicar la cedula", Toast.LENGTH_SHORT);
            toast.show();
        }
        else
        {
            final ProgressDialog dialog = ProgressDialog.show(
                    ConsultaSolicitudes.this
                    ,"Buscando"
                    ,"Espere, por favor..."
                    ,true
                    ,true);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    dialog.dismiss();
                    busqueda();
                }}, 3000);  // 3000 milliseconds


        }}
    public void mostrarCita(){
        resultado.setVisibility(View.VISIBLE);
        nombre.setVisibility(View.VISIBLE);
        lbSolicitudEditable.setVisibility(View.VISIBLE);
        lbfechaFija.setVisibility(View.VISIBLE);
        lbfechaEditable.setVisibility(View.VISIBLE);
        llResultadoCita.setVisibility(View.VISIBLE);
        recomendacion.setVisibility(View.VISIBLE);
    }
    public void mostrarCitaPendiente(){

        llResultadoCita.setVisibility(View.VISIBLE);
        resultado.setVisibility(View.VISIBLE);
        lbfechaEditable.setVisibility(View.GONE);
        lbfechaFija.setVisibility(View.GONE);
        resultado.setVisibility(View.VISIBLE);
    }
    public void ocultar(){
        resultado.setVisibility(View.GONE);
        lbfechaEditable.setVisibility(View.GONE);
        lbfechaFija.setVisibility(View.GONE);
        llResultadoCita.setVisibility(View.GONE);
        recomendacion.setVisibility(View.GONE);
    }
    public void ocultarNoSolicitud(){
        resultado.setVisibility(View.VISIBLE);
        lbfechaEditable.setVisibility(View.GONE);
        lbfechaFija.setVisibility(View.GONE);
        llResultadoCita.setVisibility(View.GONE);
    }
    public void declaraciones(){

        serCita = new ServicioCita();
        persona = new Persona();
        visita = new Visita();
        filtro = new Calculos();
        listaSolicitudCharla = new ArrayList<>();

        consultar = (Button) findViewById(R.id.btConsultar);
        cedula = (EditText) findViewById(R.id.etCedulaConsultada);
        resultado = (TextView) findViewById(R.id.tvbannerResultado);
        nombre = (TextView) findViewById(R.id.tvNombreConsultado);
        lbSolicitudFija = (TextView) findViewById(R.id.tvEstatusSolicitud);
        lbSolicitudEditable = (TextView) findViewById(R.id.tvEstatusEditable);
        lbfechaFija = (TextView) findViewById(R.id.tvFechaFija);
        lbfechaEditable = (TextView) findViewById(R.id.tvFechaEditable);
        recomendacion = (TextView) findViewById(R.id.tvMensajeConsulta);
        llResultadoCita = (LinearLayout)findViewById(R.id.llResultadosCita);
        listaSolicitudesCharla = (ListView)findViewById(R.id.lvSolicitudesCharla);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar(v);
                }
        });
    }

    public Date convertirFecha(String fecha){
        Calendar calendario = Calendar.getInstance();
        Calendar calendarioActual = Calendar.getInstance();
        String fechaFraccionada[] = fecha.split("-");
        calendario.add(Calendar.YEAR, Integer.valueOf(fechaFraccionada[0]) - calendarioActual.get(Calendar.YEAR));
        calendario.add(Calendar.MONTH, Integer.valueOf(fechaFraccionada[1])-2);
        calendario.add(Calendar.DAY_OF_MONTH, Integer.valueOf(fechaFraccionada[2]) - calendarioActual.get(Calendar.DAY_OF_MONTH));

        return calendario.getTime();
    }

    public void busqueda(){

            String fecha = "";

            if(spTipoConsulta.getSelectedItem().toString().equalsIgnoreCase("Consulta de Cita")){
                persona = serCita.buscarEstadoSolicitud(cedula.getText().toString());

                if(persona.getEstatus()!=null){

                    if(persona.getEstatus().equalsIgnoreCase("A")){
                        //visita = serCita.buscarFechaVisita(cedula.getText().toString());
                        fecha = serCita.buscarFechaVisita(cedula.getText().toString());
                        /*if(visita!=null){*/
                            nombre.setText(persona.getNombre()+ " " +persona.getApellido());
                            //Date fecha = visita.getFecha();

                            lbfechaEditable.setText(fecha);
                            lbSolicitudEditable.setText("Cita Asignada");
                            recomendacion.setText("Presentese en la fecha indicada en la fundación");
                            mostrarCita();/*
                        }else
                            Log.e("Exception", "Visita null");*/
                    }else if(persona.getEstatus().equalsIgnoreCase("S")){

                        nombre.setText(persona.getNombre()+ " " +persona.getApellido());
                        lbSolicitudEditable.setText("En revision");
                        recomendacion.setText("Su solicitud pronto será revisada");
                        mostrarCitaPendiente();
                    }else if(persona.getEstatus().equalsIgnoreCase("I")){

                        nombre.setText(persona.getNombre()+ " " +persona.getApellido());
                        lbSolicitudEditable.setText("Registrado");
                        recomendacion.setText("Usted ya esta registrado como paciente de Fundamama");
                        mostrarCitaPendiente();
                    }
                }else {
                    ocultarNoSolicitud();
                    recomendacion.setText("No posee solicitud.");
                    Log.e("Exception", "Persona null");
                }

            }else if (spTipoConsulta.getSelectedItem().toString().equalsIgnoreCase("Consulta de Charla")){

                ocultar();

                Intent intent = new Intent(ConsultaSolicitudes.this, ListaSolicitudesActividad.class);
                startActivity(intent);


            }
    }
}