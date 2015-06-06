package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ucla.pectusmovil.R;

public class Bienvenida extends Activity
{

    private Button btUsuario;
   private Button btVisitante;

     protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        btVisitante = (Button) findViewById(R.id.btVisitante);
        btUsuario = (Button) findViewById(R.id.btUsuario);
        SharedPreferences prefe=getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
    }

    public void soyVisitante (View view)
    {
        SharedPreferences preferencias=getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);
        Editor editor=preferencias.edit();
        editor.putString("usuario", "visitante");
        editor.commit();
        //finish();
        Intent i = new Intent(Bienvenida.this, MenuPrincipal.class);
        Bundle b = new Bundle();
        b.putString("user", "Visitante");
        i.putExtras(b);
        startActivity(i);
        finish();

    }
    public void soyUsuario(View view)
    {

        startActivity(new Intent(Bienvenida.this, Login.class));

        finish();

    }
}