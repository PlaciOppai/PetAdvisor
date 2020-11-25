package com.example.petadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class IniSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ini_sesion);
        setTitle("Crear Usuario");

        final String correo=((EditText)findViewById(R.id.editTextNombre)).getText().toString();
        final String contra=((EditText)findViewById(R.id.editTextContraIni)).getText().toString();

        ((Button)findViewById(R.id.BotonCrear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crear= new Intent(v.getContext(),CrearUsuActivity.class);
                startActivityForResult(crear,0);
            }
        });
        ((Button)findViewById(R.id.BotonIni)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(correo.isEmpty()){
                    ((TextView)findViewById(R.id.tvValidaCorreo)).setText("Obligatorio ingresa un correo");
                }else {
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(correo,contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent alojamientosCre= new Intent(getApplicationContext(),AlojamientosActivity.class);
                                startActivityForResult(alojamientosCre,0);
                            }else{
                                showAlert();
                            }
                        }
                    });
                }
            }
        });
    }

    private void showAlert(){
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        dialog.setTitle("Error");
        dialog.setMessage("Ha ocurrido un error al iniciar sesion, revise la contraseña y el usuario");
        dialog.setPositiveButton("Aceptar",null);
        dialog.show();

    }



}
