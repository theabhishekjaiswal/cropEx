package com.teamhealinghands.cropex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadLocale();
        Button btnui = findViewById(R.id.btnuploadimage);
        Button mtrp = findViewById(R.id.mToResponsePage);
        LinearLayout home = findViewById(R.id.home);
        LinearLayout post = findViewById(R.id.post);
        LinearLayout chat = findViewById(R.id.chat);
        LinearLayout shop = findViewById(R.id.shop);
        LinearLayout languages = findViewById(R.id.language);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inext = new Intent(MainActivity.this,MainActivity.class);
                startActivity(inext);
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inext = new Intent(MainActivity.this,post.class);
                startActivity(inext);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inext = new Intent(MainActivity.this,chat.class);
                startActivity(inext);
            }
        });
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inext = new Intent(MainActivity.this,shop.class);
                startActivity(inext);
            }
        });


        languages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseLanguage();
            }
        });


        btnui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inext = new Intent(MainActivity.this,uploadImage.class);
                startActivity(inext);
            }
        });

        mtrp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inext = new Intent(MainActivity.this,responsePage.class);
                startActivity(inext);
            }
        });
    }

    private void chooseLanguage() {
       final String languages[]= {"English" , "Hindi", "Bengali"};
       AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
       mBuilder.setTitle("Choose Languages");
       mBuilder.setSingleChoiceItems(languages, -1, new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialog, int which) {
               if(which==0){
                   setLocale("");
                   recreate();
               }
               else if(which==1){
                   setLocale("hi");
                   recreate();
               }
               else if(which==2){
                   setLocale("bn");
                   recreate();
               }

           }
       });
       mBuilder.create();
       mBuilder.show();

    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale=locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("app_lang",language);
        editor.apply();
    }
    private void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = preferences.getString("app_lang","");
        setLocale(language);
    }
}