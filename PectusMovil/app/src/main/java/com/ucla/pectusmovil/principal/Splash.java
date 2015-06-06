package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ucla.pectusmovil.R;

public class Splash extends Activity {
private String tipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /****** Create Thread that will sleep for 5 seconds *************/
        Thread background = new Thread() {
            public void run() {

                try {
                    // Thread will sleep for 5 seconds
                    sleep(4*1000);

                    // After 5 seconds redirect to another intent


                        Intent i=   new Intent(getBaseContext(), Wizard.class);
                        startActivity(i);

                        //Remove activity
                        finish();



                } catch (Exception e) {

                }
            }
        };
        background.start();
    }


    @Override
    protected void onDestroy() {

        super.onDestroy();

    }
}
