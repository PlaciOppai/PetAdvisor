package com.example.petadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class IniSesionActivity extends AppCompatActivity {

    private String correo;
    private String contra;
    FirebaseAuth miFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ini_sesion);
        setTitle("Crear Usuario");
        miFirebase=FirebaseAuth.getInstance();

        ((Button)findViewById(R.id.BotonCrear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crear= new Intent(v.getContext(),CrearUsuActivity.class);
                startActivityForResult(crear,0);
            }
        });

        ((Button)findViewById(R.id.buttonCambioC)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crear= new Intent(v.getContext(),CambiarContraActivity.class);
                startActivityForResult(crear,0);
            }
        });

        ((Button)findViewById(R.id.BotonIni)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tvValidaCorreo)).setText("");
                correo=((EditText)findViewById(R.id.editTextNombre)).getText().toString();
                contra=((EditText)findViewById(R.id.editTextContraIni)).getText().toString();
                Log.i("contra",contra);

                if(correo.isEmpty() || contra.isEmpty()){
                    ((TextView)findViewById(R.id.tvValidaCorreo)).setText("Obligatorio ingresar correo y contraseña");
                }else {

                    miFirebase.signInWithEmailAndPassword(correo,contra).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.i("contra",contra);
                            Log.i("contra",correo);
                            if(task.isSuccessful()){
                                Intent alojamientosCre= new Intent(getApplicationContext(),AlojamientosActivity.class);
                                alojamientosCre.putExtra("usuario", correo);
                                startActivityForResult(alojamientosCre,0);
                            }else{
                                Log.d("FALLO","onComplete: Failed=" + task.getException().getMessage()); //ADD THIS

                                //Toast.makeText(this, task.getException().getMessage(),Toast.LENGTH_SHORT).show();
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
