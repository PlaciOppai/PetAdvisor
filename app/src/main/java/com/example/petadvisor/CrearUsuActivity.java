package com.example.petadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class CrearUsuActivity extends AppCompatActivity {

    String correoN;
    String contraN;
    String repitaCN;
    FirebaseAuth firebaseCrear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usu);
        firebaseCrear=FirebaseAuth.getInstance();
        //((Button)findViewById(R.id.bCrearUsu)).setEnabled(false);

        ((Button)findViewById(R.id.bCrearUsu)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contraN=((EditText)findViewById(R.id.editTextContra)).getText().toString();
                repitaCN=((EditText)findViewById(R.id.editTextRepita)).getText().toString();
                correoN=((EditText)findViewById(R.id.editTextCorreo)).getText().toString();

                Log.i("correo",correoN);
                if(correoN.isEmpty()){
                    ((TextView)findViewById(R.id.textcontra)).setText("Es obligatorio ingresar un correo");

                }else{
                    firebaseCrear.createUserWithEmailAndPassword(correoN,contraN).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Intent alojamientosCre= new Intent(getApplicationContext(),AlojamientosActivity.class);
                                startActivityForResult(alojamientosCre,0);
                            }else {
                                showAlert();
                            }
                        }
                    });
                }
            }
        });

        ((Button)findViewById(R.id.bCancelar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
    public boolean validaContraseña(String contra1, String contra2){
        if(!contra1.isEmpty() && !contra2.isEmpty()){
            if(contra1.equals(contra2)){
                return true;
            }else{
                ((TextView)findViewById(R.id.textcontra)).setText("Las contraseñas no coinciden");
            }

        }else if(contra2.isEmpty()){
            ((TextView)findViewById(R.id.textcontra)).setText("Las contraseñas no coinciden");
        }else if(contra1.isEmpty() && !contra2.isEmpty()){
            ((TextView)findViewById(R.id.textcontra)).setText("Ingrese una contraseña");
        }else if(contra1.isEmpty() && contra2.isEmpty()){
            ((TextView)findViewById(R.id.textcontra)).setText("Rellene los campos obligatorios");
        }
        return false;
    }

    private void showAlert(){
        AlertDialog.Builder dialog= new AlertDialog.Builder(this);
        dialog.setTitle("Error");
        dialog.setMessage("Ya existe un usuario con este correo");
        dialog.setPositiveButton("Aceptar",null);
        dialog.show();

    }



}
