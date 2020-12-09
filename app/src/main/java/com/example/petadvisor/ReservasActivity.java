package com.example.petadvisor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReservasActivity extends AppCompatActivity {
    private EditText fechaIni;
    private EditText fechaFin;
    private DatabaseReference fireDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);
        fechaIni=(EditText)findViewById(R.id.editTextFechaIni);
        fechaFin=(EditText)findViewById(R.id.editTextFechaFin);
        fireDB=FirebaseDatabase.getInstance().getReference().child("Reservas");

        fechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(fechaFin);
            }
        });
        fechaIni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(fechaIni);
            }
        });

        //evento que se encarga de hacer la reserver y cargarla en la base de datos
        ((Button)findViewById(R.id.buttonConfirmarReser)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fecha1=fechaIni.getText().toString();
                String fecha2=fechaFin.getText().toString();
                int numP=Integer.parseInt(((EditText)findViewById(R.id.editTextNumPerso)).getText().toString());
                int precio=Integer.parseInt(getIntent().getStringExtra("precio"));
                int total=numP*precio;
                String usuario=getIntent().getStringExtra("usuario");
                String alojamiento=getIntent().getStringExtra("nombreAlo");
                Reservas reser=new Reservas(usuario,fecha1,fecha2,total,alojamiento);
                fireDB.push().setValue(reser);
                AlertDialog.Builder builder= new AlertDialog.Builder(ReservasActivity.this);
                builder.setMessage("Se ha completado la reserva, el pago se realizara en el alojamiento. Total: "+total)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });

    }
    //muestra los datepicker calendar para elegir las fechas
    private void showDatePickerDialog(final EditText et) {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because January is zero
                final String selectedDate = day + " / " + (month+1) + " / " + year;
                et.setText(selectedDate);
            }
        });

        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    //si pulsamos atras nos salta un evento para confirmar que queremos salir de la aplicacion
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("¿Quieres cancelar la reserva?")
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
}
