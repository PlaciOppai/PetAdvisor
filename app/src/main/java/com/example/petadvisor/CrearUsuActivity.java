package com.example.petadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CrearUsuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usu);
        ((Button)findViewById(R.id.bCrearUsu)).setEnabled(false);

       /* ((TextView)findViewById(R.id.editTextRepita)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(((EditText)findViewById(R.id.editTextContra)).getText().toString()==(((EditText)findViewById(R.id.editTextRepita))).getText().toString()){
                    ((TextView)findViewById(R.id.textcontra)).setText("Contraseña correcta");
                }
                else {
                    ((TextView)findViewById(R.id.textcontra)).setText("Contraseñas incorrectas");
                }
                return false;
            }
        });
        ((TextView)findViewById(R.id.editTextRepita)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(((EditText)findViewById(R.id.editTextContra)).getText().toString()==(((EditText)findViewById(R.id.editTextRepita))).getText().toString()){
                    ((TextView)findViewById(R.id.textcontra)).setText("Contraseña correcta");
                    ((Button)findViewById(R.id.bCrearUsu)).setEnabled(true);
                }
                else {
                    ((TextView)findViewById(R.id.textcontra)).setText("Contraseñas incorrectas");
                }

            }
        });
        */

    }
}
