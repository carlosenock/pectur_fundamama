package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ucla.pectusmovil.R;

public class CambiarClave extends Activity {

    private EditText usuario, claveActual, claveNueva, claveNuevaConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_clave);
        usuario = (EditText)findViewById(R.id.etUsuario);
        claveActual = (EditText)findViewById(R.id.etClaveActual);
        claveNueva = (EditText)findViewById(R.id.etClaveNueva);
        claveNuevaConf = (EditText)findViewById(R.id.etClaveNuevaConf);// set listeners

        usuario.addTextChangedListener(mTextWatcher);
        claveActual.addTextChangedListener(mTextWatcher);
        claveNueva.addTextChangedListener(mTextWatcher);
        claveNuevaConf.addTextChangedListener(mTextWatcher); // run once to disable if empty

        checkFieldsForEmptyValues();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_cambiar_clave, menu);
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


    public void AceptarClaveNueva (View v) {
        Button boton = (Button) findViewById(R.id.btAceptarClaveNueva);

        claveNueva = (EditText)findViewById(R.id.etClaveNueva);
        claveNuevaConf = (EditText)findViewById(R.id.etClaveNuevaConf);



        if (claveNueva.getText().toString().equals(claveNuevaConf.getText().toString()))
            mensaje("Clave cambiada correctamente!!");
        else
            mensaje("Clave nueva no coincide!!");






    }


    public void mensaje(String mensaje){

        Toast toast = Toast.makeText(this, mensaje, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

    }


    TextWatcher mTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) { }


        @Override public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) { }


        @Override public void afterTextChanged(Editable editable) { // check Fields For Empty Values
         checkFieldsForEmptyValues(); } };

        void checkFieldsForEmptyValues(){
            Button boton = (Button) findViewById(R.id.btAceptarClaveNueva);


            EditText usuario = (EditText)findViewById(R.id.etUsuario),
                    claveActual = (EditText)findViewById(R.id.etClaveActual),
                    claveNueva = (EditText)findViewById(R.id.etClaveNueva),
                    claveNuevaConf = (EditText)findViewById(R.id.etClaveNuevaConf);

            if (usuario.getText().toString().equals("")||
                    claveActual.getText().toString().equals("") ||
                    claveNueva.getText().toString().equals("") ||
                    claveNuevaConf.getText().toString().equals(""))
                boton.setEnabled(false);
            else
                boton.setEnabled(true);


        }






}
