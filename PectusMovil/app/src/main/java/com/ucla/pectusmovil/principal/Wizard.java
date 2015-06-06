package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewAnimator;

import com.ucla.pectusmovil.R;

public class Wizard extends Activity {

    private int progres=1;
    private int cont=0;
    private Button buttonPrev, buttonNext, buttonSalir;
    private ViewAnimator viewAnimator;
    private TextView titulofunda, titulopectus, titulopectusmovil,qesfundadmama;
    private ProgressBar barra;
    private Animation slide_in_left, slide_out_right;
    private String tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wizard);

        buttonPrev = (Button)findViewById(R.id.prev);
        buttonNext = (Button)findViewById(R.id.next);
        buttonSalir=(Button)findViewById(R.id.salir);
        viewAnimator = (ViewAnimator)findViewById(R.id.viewanimator);
        barra= (ProgressBar)findViewById(R.id.progressBar2);

        SharedPreferences preferencias=getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        tipo = preferencias.getString("usuario","");
        if (tipo.equals("visitante")) {
            Intent i = new Intent(getBaseContext(), MenuPrincipal.class);
            startActivity(i);

            //Remove activity
            finish();
        }
        else if (tipo.equals("voluntario"))
        {
            Intent i=   new Intent(getBaseContext(), MenuPrincipal.class);
            startActivity(i);

            //Remove activity
            finish();
        }

        slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        barra.setProgress(progres);
        viewAnimator.setInAnimation(slide_in_left);
        viewAnimator.setOutAnimation(slide_out_right);

        buttonPrev.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View arg0) {

                buttonNext.setVisibility(View.VISIBLE);
                buttonSalir.setVisibility(View.GONE);
                if (progres!=1){
                    progres=progres-1;
                    barra.setProgress(progres);
                    viewAnimator.showPrevious();
                    buttonNext.setClickable(true);
                }
                else if (progres==1){
                    buttonPrev.setClickable(false);
                    buttonNext.setClickable(true);
                }

            }});

        buttonNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {

                if (progres!=3){
                    progres=progres+1;
                    barra.setProgress(progres);
                    viewAnimator.showNext();
                    buttonPrev.setClickable(true);
                }
                if (progres==3){
                    buttonNext.setClickable(false);
                    buttonPrev.setClickable(true);
                    buttonNext.setVisibility(View.GONE);
                    buttonSalir.setVisibility(View.VISIBLE);


                }

            }

        });

        buttonSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=   new Intent(getBaseContext(), Bienvenida.class);
                startActivity(i);

                //Remueve el Activity
                finish();
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
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
