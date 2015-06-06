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
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.servicio.RestClient;
import com.ucla.pectusmovil.servicio.ServicioSolicitudActividad;

import java.util.Calendar;
import java.util.Vector;

public class SolicitudCharla extends Activity
{
    private String idTipo;
    private EditText nombreRazon;
    private EditText cedulaRif;
    private EditText telefono;
    private EditText descripcion;
    private EditText fechaCharla;
    private TextView titulo;
    private Button Guardar;
    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;
    private Vector<Object> datos;
    private ServicioSolicitudActividad serSolicitudActividad;
    private RestClient rest;
    private Object respuesta;;



    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitud_charla);

        //setContentView(R.layout.activity_consulta_solicitudes);
        getActionBar().setTitle("Solicitud de Charla");
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));
        getActionBar().setIcon(R.drawable.pectus_logo);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        nombreRazon = (EditText) findViewById(R.id.etNombreRazon);
        cedulaRif = (EditText) findViewById(R.id.etCedulaRif);
        telefono = (EditText) findViewById(R.id.etTelefono);
        descripcion = (EditText) findViewById(R.id.etDescripcion);
        fechaCharla = (EditText) findViewById(R.id.etFechaCharla);
        Guardar = (Button) findViewById(R.id.btSolicitarCharla);

        datos = new Vector<>();
        serSolicitudActividad = new ServicioSolicitudActividad();
        rest = new RestClient();

    }



    public void seleccionarFecha (View view){
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
                        fechaCharla.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }
    //BOTON PARA SOLICITAR LA CHARLA
    public void solicitarCharla (View view) {


        if (nombreRazon.getText().toString().equals("") || cedulaRif.getText().toString().equals("")
                || telefono.getText().toString().equals("") || descripcion.getText().toString().equals("")
                || fechaCharla.getText().toString().equals(""))
        {
            // Guardar.setEnabled(false);
            Toast toast = Toast.makeText(getApplicationContext(),"Debe ingresar todos los campos", Toast.LENGTH_SHORT);
            toast.show();
        }

        else {
        leerCampos();


        //Le hace llamado al servicio que envia la solicitud de charla
        respuesta = serSolicitudActividad.agregarCharla(datos);

        final ProgressDialog dialog = ProgressDialog.show(
                SolicitudCharla.this
                ,"Solicitud de Charla"
                ,"Registrando su Solicitud..."
                ,true
                ,true);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {


                dialog.dismiss();

                /* AQUI VAS A METER EL CODIGO PARA GUARDAR TODO Y LUEGO MOSTRAR EL CODIGO DE DEMOSTRACION */

                AlertDialog.Builder MensajeConfirmacion = new AlertDialog.Builder(SolicitudCharla.this);
                MensajeConfirmacion.setTitle("Charla Registrada"); // TITULO DE LA ALERTA
                MensajeConfirmacion.setMessage("  Su solicitud ha sido Registrada");
                MensajeConfirmacion.setPositiveButton("Aceptar", //NOMBRE DEL BOTON
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1)
                            {
                                //AL PRESIONAR EL BOTON DE OK LO ENVIA AL MENU PRINCIPAL
                                startActivity(new Intent(SolicitudCharla.this, MenuPrincipal.class));
                            }
                        });

                final AlertDialog alertDialog = MensajeConfirmacion.create();
                alertDialog.show();



                }}, 3000);  // 3000 milliseconds
    }}

    public void leerCampos(){

        nombreRazon = (EditText) findViewById(R.id.etNombreRazon);
        cedulaRif = (EditText) findViewById(R.id.etCedulaRif);
        telefono = (EditText) findViewById(R.id.etTelefono);
        descripcion = (EditText) findViewById(R.id.etDescripcion);
        fechaCharla = (EditText) findViewById(R.id.etFechaCharla);

        datos.add("1");//El tipo de actividad 1 representa charla
        datos.add(descripcion.getText());
        datos.add(nombreRazon.getText());
        datos.add(telefono.getText());
        datos.add(fechaCharla.getText());
        datos.add("S");
        datos.add("SA001");


        //Log.e("Servicio Cita", String.valueOf(respuesta));


    }
}