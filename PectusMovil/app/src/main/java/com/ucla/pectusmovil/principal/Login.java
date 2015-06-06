package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ucla.pectusmovil.R;
import com.ucla.pectusmovil.modelo.Voluntario;
import com.ucla.pectusmovil.servicio.ServicioVoluntario;

import java.util.Timer;
import java.util.TimerTask;


public class Login extends Activity {
    private String userEscrito;
    private String passEscrito;
    private EditText usuario;
    private EditText clave;
    private TextView informacion;
    private Button iniciarSesion;
    private TextView olvidarClave;
    private CheckBox mostrarClave;
    private SharedPreferences preferencias;
    private String cedulaVoluntario;
    private boolean validado = false;

    private Voluntario voluntario;
    private ServicioVoluntario serVoluntario;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        startAnimation();
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setSubtitle("Entrar a Pectus como voluntario");
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));
        getActionBar().setIcon(R.drawable.pectus_logo);
        usuario = (EditText) findViewById(R.id.etUsuario);
        clave = (EditText) findViewById(R.id.etContrasena);
        olvidarClave = (TextView) findViewById(R.id.olvidar_clave);
        mostrarClave = (CheckBox) findViewById(R.id.mostrarClave);
        informacion = (TextView)findViewById(R.id.tvInformacion);

        serVoluntario = new ServicioVoluntario();
        voluntario = new Voluntario();

        //METODO QUE CARGA LOS ELEMENTOS DEL LOGIN
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        mostrarElementos();
                    }
                });
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 3200);

        //OBTENEMOS EL ID DEL BOTON EN LA VISTA PARA IDENTIFICAR EL ELEMENTO PARA SU USO
        iniciarSesion = (Button) findViewById(R.id.btIniciarSesion);
        iniciarSesion.findViewById(R.id.btIniciarSesion).setEnabled(false);

        //CUANDO PRESIONE EL CHECKBOX PARA MOSTRAR LA CLAVE
        mostrarClave.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if(!isChecked)
                    clave.setTransformationMethod(PasswordTransformationMethod.getInstance());
                else
                    clave.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
        });


        //METODO DE EVENTOS CON EL TEXTVIEW
        usuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,	int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                if((usuario.getText().toString().trim().equals("") )) {
                    iniciarSesion.findViewById(R.id.btIniciarSesion).setEnabled(false);
                    iniciarSesion.setTextColor(Color.parseColor("#ffffff"));
                    iniciarSesion.setBackgroundResource(R.drawable.btn_personalizado_login_deshabilitado);
                }
                else {
                    iniciarSesion.setBackgroundResource(R.drawable.btn_personalizado);
                    iniciarSesion.setTextColor(Color.parseColor("#ffffff"));
                    iniciarSesion.findViewById(R.id.btIniciarSesion).setEnabled(true);
                }
            }
        });

    }



    public void iniciarSesion(View view){

    userEscrito = usuario.getText().toString();

    try{
        validado = serVoluntario.buscarVoluntario(userEscrito);
    }catch(IllegalStateException e){
        e.printStackTrace();
        validado=false;
    }

    if (validado){

        SharedPreferences preferencias = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("usuario", "voluntario");
        editor.putString("voluntario", userEscrito);
        editor.commit();
        Intent i = new Intent(Login.this, MenuPrincipal.class);
        startActivity(i);
        Toast toast = Toast.makeText(getApplicationContext(), "Entrando como voluntario", Toast.LENGTH_SHORT);
        toast.show();

    }else{
            Toast toast2 = Toast.makeText(getApplicationContext(),"Voluntario no encontrado", Toast.LENGTH_SHORT);
            toast2.show();
        }
    }

    //VIBRA Y MANDA UN MENSAJE DE ERROR
    public void errorLogin(){
        Vibrator vibrator =(Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        Toast.makeText(getApplicationContext(),"Usuario o contraseña son incorrectos.", Toast.LENGTH_SHORT).show();;
    }
    private void startAnimation(){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLogin);
        layout.clearAnimation();
        layout.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        anim.reset();
        ImageView image = (ImageView) findViewById(R.id.logoLogin);
        image.clearAnimation();
        image.startAnimation(anim);

    }

    private void mostrarElementos(){
        usuario.setVisibility(View.VISIBLE);
        //clave.setVisibility(View.VISIBLE);
        iniciarSesion.setVisibility(View.VISIBLE);
        //olvidarClave.setVisibility(View.VISIBLE);
        //mostrarClave.setVisibility(View.VISIBLE);
        informacion.setVisibility(View.VISIBLE);
    }


}
