package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.modelo.Ciudad;
import com.ucla.pectusmovil.servicio.RestClient;
import com.ucla.pectusmovil.servicio.ServicioCita;
import com.ucla.pectusmovil.servicio.ServicioCiudad;

import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

public class SolicitudCita extends Activity
{
    private String idTipo;
    private EditText nombres;
    private EditText apellidos;
    private EditText cedula;
    private EditText correo;
    private EditText telefonoFijo;
    private EditText telefonoMovil;
    private EditText direccion;
    private EditText fecha;
    private TextView titulo;
    private EditText profesion;
    private Button siguiente;

    private Spinner spSexo;
    private Spinner spEstadoCivil;
    private Spinner spCiudades;

    private Vector<Object> datos;

    private ServicioCita serCita;
    private RestClient rest;
    private Object ok;

    private ArrayList<NameValuePair> campos;
    private ArrayList<NameValuePair> valores;

    private List<Ciudad> listaCiudades;
    private ArrayList<String> listaNombreCiudades = new ArrayList<String>();


    private Object respuesta;

    //String ip = "172.19.24.108:80";


    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_cita);


        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowTitleEnabled(true);
        getActionBar().setTitle("Solicitud de Cita");
        getActionBar().setIcon(R.drawable.pectus_logo);
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        datos = new Vector<>();

        serCita = new ServicioCita();
        rest = new RestClient();
        listaCiudades = new ArrayList<>();
        listaNombreCiudades = new ArrayList<>();

        nombres = (EditText) findViewById(R.id.etNombre);
        apellidos = (EditText) findViewById(R.id.etApellido);
        cedula = (EditText) findViewById(R.id.etCedula);
        correo = (EditText) findViewById(R.id.etEmail);
        telefonoFijo = (EditText) findViewById(R.id.etTelefono);
        telefonoMovil = (EditText) findViewById(R.id.etCelular);
        // descripcion = (EditText) findViewById(R.id.etDescripcion);
        fecha = (EditText) findViewById(R.id.etFechaNacimiento);
        siguiente = (Button) findViewById(R.id.btSiguiente);
        spSexo = (Spinner) findViewById(R.id.spSexo);
        direccion = (EditText) findViewById(R.id.etDireccion);
        spEstadoCivil = (Spinner) findViewById(R.id.spEstadoCivil);
        profesion = (EditText) findViewById(R.id.etProfesion);


        //Creamos el adaptador y le decimos que tome los objetos desde el Array llamado...
        ArrayAdapter<CharSequence> adaptadorSexo = ArrayAdapter.createFromResource(this,R.array.Sexo,android.R.layout.simple_spinner_item);
        //Añadimos el layout para el menú
        adaptadorSexo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Le indicamos al spinner el adaptador a usar
        spSexo.setPrompt("Seleccione su sexo...");
        //Le indicamos al spinner el adaptador a usar
        spSexo.setAdapter(
                new AdaptadorSpinnerNadaSeleccionado(
                        adaptadorSexo,
                        R.layout.spinner_sexo_nada_seleccionado,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        //Creamos el adaptador y le decimos que tome los objetos desde el Array llamado...
        ArrayAdapter<CharSequence> adaptadorEstadoCivil = ArrayAdapter.createFromResource(this,R.array.EstadoCivil,android.R.layout.simple_spinner_item);
        //Añadimos el layout para el menú
        adaptadorEstadoCivil.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Le indicamos al spinner el adaptador a usar
        spEstadoCivil.setPrompt("Seleccione su estado civil...");
        //Le indicamos al spinner el adaptador a usar
        spEstadoCivil.setAdapter(
                new AdaptadorSpinnerNadaSeleccionado(
                        adaptadorEstadoCivil,
                        R.layout.spinner_estadocivil_nada_seleccionado,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        spCiudades = (Spinner) findViewById(R.id.spCiudad);

        listaCiudades = new ServicioCiudad().buscarCiudades();
        for(int i=0;i<listaCiudades.size();i++){
            listaNombreCiudades.add(listaCiudades.get(i).getNombre());
            Log.e("ciudades ", listaCiudades.get(i).getNombre());
        }

        //Creamos el adaptador y le decimos que tome los objetos desde el Array llamado...
        ArrayAdapter<String> adaptadorCiudades = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,listaNombreCiudades);
        //Añadimos el layout para el menú
        adaptadorCiudades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCiudades.setPrompt("Seleccione su ciudad...");
        //Le indicamos al spinner el adaptador a usar
        spCiudades.setAdapter(
                new AdaptadorSpinnerNadaSeleccionado(
                        adaptadorCiudades,
                        R.layout.spinner_ciudad_nada_seleccionado,
                        // R.layout.contact_spinner_nothing_selected_dropdown, // Optional
                        this));

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos1 = spCiudades.getSelectedItemPosition();
                int pos2 = spEstadoCivil.getSelectedItemPosition();
                int pos3 = spSexo.getSelectedItemPosition();
                if (cedula.getText().toString().equals("") || nombres.getText().toString().equals("")
                        || apellidos.getText().toString().equals("") || correo.getText().toString().equals("")
                        || telefonoFijo.getText().toString().equals("")|| telefonoMovil.getText().toString().equals("")
                        || fecha.getText().toString().equals("")|| profesion.getText().toString().equals("")
                        || direccion.getText().toString().equals("")|| pos1 == 0 || pos2 == 0 || pos3 == 0  )
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Debes escribir y seleccionar todos los campos", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else
                {

                leerCampos();
                final ProgressDialog dialog = ProgressDialog.show(
                        SolicitudCita.this
                        ,"Solicitud de Cita"
                        ,"Registrando su Solicitud..."
                        ,true
                        ,true);
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        dialog.dismiss();
                        //=============Mensaje de Finalizacion de registro=================
                        Toast.makeText(SolicitudCita.this, "Solicitud Enviada", Toast.LENGTH_SHORT).show();

                        /* AQUI VAS A METER EL CODIGO PARA GUARDAR TODO Y LUEGO MOSTRAR EL CODIGO DE DEMOSTRACION */

                        AlertDialog.Builder MensajeConfirmacion = new AlertDialog.Builder(SolicitudCita.this);
                        MensajeConfirmacion.setTitle("Cita Registrada"); // TITULO DE LA ALERTA
                        MensajeConfirmacion.setMessage("  Su solicitud ha sido Registrada");
                        MensajeConfirmacion.setPositiveButton("Aceptar", //NOMBRE DEL BOTON
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1)
                                    {
                                        //AL PRESIONAR EL BOTON DE OK LO ENVIA AL MENU PRINCIPAL
                                        startActivity(new Intent(SolicitudCita.this, MenuPrincipal.class));
                                    }
                                });

                        final AlertDialog alertDialog = MensajeConfirmacion.create();
                        alertDialog.show();
                    }
                }, 3000);}
            }
        });
        }


    public void fecha (View view){
        // Process to get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // Launch Date Picker Dialog
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Display Selected date in textbox
                        fecha.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    public void leerCampos(){

        int idCiudad =0;
        datos.add(cedula.getText());
        String ciudad = spCiudades.getSelectedItem().toString();
        for(int i=0;i<listaCiudades.size();i++){
            if (listaCiudades.get(i).getNombre().equalsIgnoreCase(ciudad)){
                idCiudad=listaCiudades.get(i).getId();
            }
        }
        datos.add(idCiudad);

        datos.add(nombres.getText());
        datos.add(apellidos.getText());
        datos.add(telefonoMovil.getText());
        datos.add(telefonoFijo.getText());

        if(spEstadoCivil.getSelectedItem().toString().equalsIgnoreCase("Soltero")){
            datos.add("S");
        }else  if(spEstadoCivil.getSelectedItem().toString().equalsIgnoreCase("Divorciado")){
            datos.add("D");
        }else  if(spEstadoCivil.getSelectedItem().toString().equalsIgnoreCase("Viudo")){
            datos.add("V");
        }else  if(spEstadoCivil.getSelectedItem().toString().equalsIgnoreCase("Casado")){
            datos.add("C");
        }
        datos.add(direccion.getText());
        datos.add(correo.getText());

        datos.add(fecha.getText());
        datos.add(spSexo.getSelectedItem().toString());
        datos.add(profesion.getText());
        datos.add("S");

        campos = new ArrayList<NameValuePair>();
        valores = new ArrayList<NameValuePair>();

        respuesta = serCita.agregarCitaPaciente(datos);

        //Log.e("Servicio Cita", String.valueOf(respuesta));


    }


}