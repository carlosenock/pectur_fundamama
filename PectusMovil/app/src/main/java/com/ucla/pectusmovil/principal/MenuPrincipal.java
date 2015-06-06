package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Gallery;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.controlador.actividad.ListaActividadVoluntario;
import com.ucla.pectusmovil.controlador.actividad.ListadoActividades;
import com.ucla.pectusmovil.controlador.evento.ListaEventoVoluntario;
import com.ucla.pectusmovil.controlador.evento.TodosEventos;
import com.ucla.pectusmovil.modelo.Actividad;
import com.ucla.pectusmovil.modelo.Ciudad;
import com.ucla.pectusmovil.modelo.Evento;
import com.ucla.pectusmovil.modelo.TipoActividad;
import com.ucla.pectusmovil.servicio.ServicioActividad;
import com.ucla.pectusmovil.servicio.ServicioCiudad;
import com.ucla.pectusmovil.servicio.ServicioEvento;
import com.ucla.pectusmovil.servicio.ServicioTipoActividad;

import java.util.ArrayList;
import java.util.List;


public class MenuPrincipal extends Activity {

    public static MenuPrincipal instance = null;

    //DECLARACIONES DE VARIABLES
    private Context c;
    private String cedulaVoluntario;

    //DECLARACIONES DE ELEMENTOS DEL LAYOUT
    private DrawerLayout drawerLayout;
    private ListView listView;
    private RelativeLayout RelativeMisEventos;
    private RelativeLayout RelativeMisActividades;
    private String user;
    private String tipo;

    private ServicioActividad serActividad;
    private ServicioEvento serEvento;
    private ServicioCiudad serCiudad;
    private ServicioTipoActividad serTipoActividad;

    private static List<TipoActividad> listaTipoActividad = new ArrayList<>();
    private static List<Ciudad> listaCiudades = new ArrayList<>();


    private static ArrayList<Evento> eventosVoluntario;
    private static ArrayList<Actividad> eventosActividad;

    //LLENADO DE DATOS PARA CARGAR EL MENU LATERAL
    private String[] opciones;

    public MenuPrincipal(){
        super();
    }

    public MenuPrincipal(Context c) {
        super();
        this.c = c;
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        //OBTENEMOS LA BARRA SUPERIOR Y LE DAMOS COLOR Y LOGO
        getActionBar().setTitle("Fundamama");
        getActionBar().setSubtitle("Una esperanza de vida");
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));
        getActionBar().setDisplayHomeAsUpEnabled(true);
        cargar();



        //METODO PARA SETEAR OPCIONES EN EL MENU LATERAL
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                opciones));
        //METODO PARA CLICKS EN EL MENU LATERAL
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                if (opciones[arg2].equals("Iniciar Sesion")){
                    SharedPreferences preferencias=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencias.edit();
                    editor.putString("nologin", "");
                    editor.commit();
                    Intent i = new Intent(MenuPrincipal.this, Login.class );
                    startActivity(i);

                }else if (opciones[arg2].equals("Salir")){
                    SharedPreferences preferencias=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferencias.edit();
                    editor.putString("nologin", "");
                    editor.commit();
                    Intent i = new Intent(MenuPrincipal.this, Bienvenida.class );
                    startActivity(i);
                    Toast.makeText(MenuPrincipal.this, "CERRANDO SESIÓN",Toast.LENGTH_SHORT).show();
                }
             //   Toast.makeText(MenuPrincipal.this, "Item: " + opciones[arg2],
               //         Toast.LENGTH_SHORT).show();

                drawerLayout.closeDrawers();
            }
        });



    }


    @Override

    //METODO PARA ABRIR LA BARRA AL HACER CLICK EN EL BOTON DEL MENU
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(listView)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(listView);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//EVENTOS DE CLIC EN LAS OPCIONES DEL MENU
    public void miseventos(View view){
        Intent intent = new Intent(MenuPrincipal.this, ListaEventoVoluntario.class);
        startActivity(intent);
    }
    public void misactividades(View view){
        Intent intent = new Intent(MenuPrincipal.this, ListaActividadVoluntario.class);
        startActivity(intent);
    }
    public void actividades(View view){
        startActivity(new Intent(MenuPrincipal.this, ListadoActividades.class));
    }
    public void eventos(View view){
        startActivity(new Intent(MenuPrincipal.this, TodosEventos.class));
    }
    public void solicitud(View view){
        startActivity(new Intent(MenuPrincipal.this, SolicitudActivity.class));
    }
    public void consultar(View view){
        startActivity(new Intent(MenuPrincipal.this, ConsultaSolicitudes.class));
    }
    public void quienesomos(View view){
        startActivity(new Intent(MenuPrincipal.this, ConocenosActivity.class));
    }
    public void siguenos(View view){
        startActivity(new Intent(MenuPrincipal.this, redesActivity.class));
    }

    public void MostrarBotones(){
        RelativeMisActividades.setVisibility(View.VISIBLE);
        RelativeMisEventos.setVisibility(View.VISIBLE);

    }
    public void OcultarBotones(){
       RelativeMisActividades.setVisibility(View.GONE);
        RelativeMisEventos.setVisibility(View.GONE);

    }

    public void cargar(){


        RelativeMisActividades = (RelativeLayout) findViewById(R.id.relativeLayoutMisActividades);
        RelativeMisEventos = (RelativeLayout) findViewById(R.id.relativeLayoutMisEventos);
        listView = (ListView) findViewById(R.id.list_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        //METODO PARA CARGAR LA GALERIA
        Gallery gallery = (Gallery)findViewById(R.id.gallery);
        gallery.setAdapter(new ImageAdapter(this));
        //METODO PARA EVENTOS CLICK DE LA GALERIA
        gallery.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Toast.makeText(MenuPrincipal.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
        //OBTENEMOS EL PARAMETRO QUE TRAE EL LOGIN
        SharedPreferences preferencias=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        tipo = preferencias.getString("usuario","");

        //Log.e("cargar Menu", preferencias.getString("usuario", ""));

        if (tipo.equals("voluntario"))
        {
            MostrarBotones();
            opciones = new String[]{"Salir" };
            setVoluntarioSesion(preferencias.getString("voluntario",""));

        }

        else
        {
            opciones = new String[]{"Iniciar Sesion"};
            OcultarBotones();
        }
    }


    public void setVoluntarioSesion(String dato){
        this.cedulaVoluntario= dato;
    }
    public String getVoluntarioSesion(){
        return this.cedulaVoluntario;
    }

    public static List<TipoActividad> getListaTipoActividad() {
        return listaTipoActividad;
    }

    public static void setListaTipoActividad(List<TipoActividad> listaTipoActividad) {
        MenuPrincipal.listaTipoActividad = listaTipoActividad;
    }

    public static List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public static void setListaCiudades(List<Ciudad> listaCiudades) {
        MenuPrincipal.listaCiudades = listaCiudades;
    }
}