package com.example.petadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AlojamientosActivity extends AppCompatActivity {

    DatabaseReference fireBD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alojamientos);

        fireBD=FirebaseDatabase.getInstance().getReference();

        cargarComunidades();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_usuarios, menu);

        return true;
    }
/*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }*/

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

    public void cargarComunidades(){
        final List<Comunidades> listaCom= new ArrayList<>();
        fireBD.child("Comunidades").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot dato : snapshot.getChildren()){
                        String nombre=dato.child("nombre").getValue().toString();
                        listaCom.add(new Comunidades(nombre));
                    }
                    ArrayAdapter<Comunidades> adaptador= new ArrayAdapter<>(AlojamientosActivity.this,android.R.layout.simple_dropdown_item_1line,listaCom);
                    ((Spinner)findViewById(R.id.spinner)).setAdapter(adaptador);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
