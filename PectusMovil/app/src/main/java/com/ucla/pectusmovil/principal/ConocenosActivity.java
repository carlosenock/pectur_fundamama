package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class ConocenosActivity extends Activity {

    private int progres=1;
    private int cont=0;
    private Button buttonPrev, buttonNext, buttonSalir;
    private ViewAnimator viewAnimator;
    private TextView titulofunda, titulopectus, titulopectusmovil,qesfundadmama;
    private ProgressBar barra;
    private Animation slide_in_left, slide_out_right;
    private String tipo;
    private  TextView valores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        getActionBar().setTitle("¿Qué es Fundamama?");
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));
        getActionBar().setDisplayHomeAsUpEnabled(true);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conocenos);

        buttonPrev = (Button)findViewById(R.id.prev);
        buttonNext = (Button)findViewById(R.id.next);
        buttonSalir=(Button)findViewById(R.id.salir);
        viewAnimator = (ViewAnimator)findViewById(R.id.viewanimator);
        barra= (ProgressBar)findViewById(R.id.progressBar2);
        valores = (TextView)findViewById((R.id.tvValores));

        slide_in_left = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        barra.setProgress(progres);
        viewAnimator.setInAnimation(slide_in_left);
        viewAnimator.setOutAnimation(slide_out_right);
        valores.setText("Compromiso: Fieles a nuestra palabra y a nuestras acciones.\n" +
                "Responsabilidad: Cumplimos nuestras obligaciones y compromisos en función de nuestras metas.\n" +
                "Sensibilidad: Demostramos lo mejor del ser humano, ser consientes de cada realidad y sus emociones.\n" +
                "Solidaridad: Nos apegamos a la causa de brindarle a las personas una mejor calidad de vida.\n" +
                "Confianza: Brindamos toda la seguridad y ánimo a quien lo necesita.\n" +
                "Voluntad: Hacemos todo lo necesario para hacer las cosas con energía y perseverancia para alcanzar nuestras metas.");

        buttonPrev.setVisibility(View.INVISIBLE);

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
                    buttonPrev.setVisibility(View.VISIBLE);
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
                Intent i=   new Intent(getBaseContext(), MenuPrincipal.class);
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