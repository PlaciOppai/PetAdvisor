package com.example.petadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IniSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ini_sesion);
        setTitle("Crear Usuario");

        ((Button)findViewById(R.id.BotonCrear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crear= new Intent(v.getContext(),CrearUsuActivity.class);
                startActivityForResult(crear,0);
            }
        });

    }



}
