package com.example.petadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaReservasActivity extends AppCompatActivity {

    private String usuario;
    private DatabaseReference fireDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_reservas);
        usuario=getIntent().getStringExtra("usuario");
        setTitle("Reservas de "+usuario);
        fireDB= FirebaseDatabase.getInstance().getReference();

        ArrayList<Reservas>lista=cargarReservas();
        ArrayAdapter<Reservas> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,lista);
        ((ListView)findViewById(R.id.lvListaReservas)).setAdapter(adapter);


    }
    public ArrayList<Reservas> cargarReservas(){
        final ArrayList<Reservas>lista=new ArrayList<>();
        fireDB.child("Reservas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for (DataSnapshot a : snapshot.getChildren()){
                        String fechaIni=a.child("fechaIni").getValue().toString();
                        String fechaFin=a.child("fechaFin").getValue().toString();
                        int importe= Integer.parseInt(a.child("importe").getValue().toString());
                        String usuarioReser=a.child("nombre").getValue().toString();
                        String alojamiento=a.child("nAlojamiento").getValue().toString();
                        Reservas reser=new Reservas(usuarioReser,fechaIni,fechaFin,importe,alojamiento);
                        if(usuario.equals(usuarioReser)){
                            lista.add(reser);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return lista;
    }
}
