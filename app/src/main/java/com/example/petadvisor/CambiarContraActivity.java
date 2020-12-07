package com.example.petadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class CambiarContraActivity extends AppCompatActivity {
    private FirebaseAuth fireAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_contra);
        fireAuth=FirebaseAuth.getInstance();

        ((Button)findViewById(R.id.bACambioC)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String correo=((EditText)findViewById(R.id.editTextCambio)).getText().toString();
                if(!correo.isEmpty())
                    cambioContra(correo);
                else{
                    Toast.makeText(CambiarContraActivity.this, "Ingrese un correo", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ((Button)findViewById(R.id.buttonCambioC)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder= new AlertDialog.Builder(v.getContext());
                builder.setMessage("¿Quieres cancelar el cambio de contraseña?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
        });



    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("¿Quieres cerrar la sesion?")
                    .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            builder.show();
        }

        return super.onKeyDown(keyCode, event);
    }
    public void cambioContra(String cambio){
        fireAuth.setLanguageCode("es");
        fireAuth.sendPasswordResetEmail(cambio).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CambiarContraActivity.this, "Se ha enviado el correo", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    Toast.makeText(CambiarContraActivity.this, "No se pudo enviar el correo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
