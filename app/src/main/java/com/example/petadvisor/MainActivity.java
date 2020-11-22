package com.example.petadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.botonIni)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sesion= new Intent(MainActivity.this.getApplicationContext(),IniSesionActivity.class);
                startActivityForResult(sesion,0);
            }
        });

        ((Button)findViewById(R.id.botonVer)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent alojamientos= new Intent(MainActivity.this.getApplicationContext(),AlojamientosActivity.class);
                startActivityForResult(alojamientos,0);
            }
        });
    }
}
