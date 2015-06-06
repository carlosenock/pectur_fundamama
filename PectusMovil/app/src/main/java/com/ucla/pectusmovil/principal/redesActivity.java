package com.ucla.pectusmovil.principal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.ucla.pectusmovil.R;

public class redesActivity extends Activity {
    private Intent emailIntent;
    private String feedback;
    private EditText feedbackBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().setTitle("Siguenos");
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ff7bb43d")));
        getActionBar().setIcon(R.drawable.pectus_logo);

        this.setContentView(R.layout.activity_redes);



    }

    public void llamar(View view){

        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:04247422137"));
        startActivity(callIntent);
    }
    public void email(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "some@email.address" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
        intent.putExtra(Intent.EXTRA_TEXT, "mail body");
        startActivity(Intent.createChooser(intent, ""));
    }
    public void twitter(View view) {

        Uri uri = Uri.parse("https://twitter.com/fundamama");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.twitter.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/fundamama")));
        }
    }

    public void facebook(View view){
        Uri uri = Uri.parse("https://www.facebook.com/pages/FUNDAMAMA/65668945845");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.facebook.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/pages/FUNDAMAMA/65668945845")));
        }

    }

    public void instagram(View view){
        Uri uri = Uri.parse("http://instagram.com/_u/fundamamalara");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://instagram.com/fundamamalara")));
        }
    }

    public void gmaps(View view){
        String link = "https://www.google.co.ve/maps/place/Fundamama/@10.068508,-69.293302,15z/data=!4m2!3m1!1s0x0:0x88009a7fc0cdef31?sa=X&ei=OzncVNzaCerisATKo4CgAg&ved=0CHIQ_BIwCw";
        Intent intent = null;
        intent = new Intent(intent.ACTION_VIEW,Uri.parse(link));
        startActivity(intent);
    }
    public void youtube(View view){
        String link = "https://www.youtube.com/channel/UCtIOJQt8j2cwyS7A3-xjJfQ";
        Intent intent = null;
        intent = new Intent(intent.ACTION_VIEW,Uri.parse("vnd.youtube://" + link));
        startActivity(intent);
    }

}