package com.example.petadvisor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AlojamientosActivity extends AppCompatActivity {

    DatabaseReference fireBD;
    ListView listViewItem;
    AdaptardorListV adapterItem;
    String usuario;
    CircleImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alojamientos);

        setTitle("Alojamientos");
        foto=findViewById(R.id.imageCicular);
        fireBD=FirebaseDatabase.getInstance().getReference();
        Intent intentIni=getIntent();
        usuario=intentIni.getStringExtra("usuario");
        listViewItem=findViewById(R.id.lvPerso);


        cargarComunidades();

        ((Spinner)findViewById(R.id.spinner)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nombreC=parent.getItemAtPosition(position).toString();
                cargarAlojamientos(nombreC);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ((ImageButton)findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nombAlo=((EditText)findViewById(R.id.editTextBuscar)).getText().toString();
                final ArrayList<Alojamientos> aloja= new ArrayList<>();
                fireBD.child("Alojamientos").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            for (DataSnapshot a : snapshot.getChildren()) {
                                String nombre = a.child("nombre").getValue().toString();
                                String desc = a.child("descripcion").getValue().toString();
                                String url = a.child("imagen1").getValue().toString();

                                Alojamientos alojamien = new Alojamientos(nombre, desc, url);
                                if(nombre.toLowerCase().contains(nombAlo)){
                                    aloja.add(alojamien);
                                }

                            }
                            adapterItem= new AdaptardorListV(AlojamientosActivity.this,aloja);
                            listViewItem.setAdapter(adapterItem);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });


        ((ListView)findViewById(R.id.lvPerso)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Alojamientos aloSelec= (Alojamientos) parent.getItemAtPosition(position);
                Picasso.get().load(generarURLAlo(aloSelec.getUrlimagen())).into(foto);
                Toast.makeText(AlojamientosActivity.this,"Pulse en la imagen cirular para ver mas detalles",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_usuarios, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                return true;
            case R.id.item2:
                finish();
                return true;
            case R.id.item3:
                resetContra();
                return true;
        }

        return super.onOptionsItemSelected(item);
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

    public void cargarAlojamientos(final String comuni){
        final ArrayList<Alojamientos> aloja= new ArrayList<>();
        fireBD.child("Alojamientos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    if(comuni.equals("") || comuni.equals("Todas")) {
                        for (DataSnapshot a : snapshot.getChildren()) {
                            String nombre = a.child("nombre").getValue().toString();
                            String desc = a.child("descripcion").getValue().toString();
                            String url = a.child("imagen1").getValue().toString();

                            Alojamientos alojamien = new Alojamientos(nombre, desc, url);
                            aloja.add(alojamien);
                        }
                    }else {
                        for (DataSnapshot a : snapshot.getChildren()) {
                            String nombre = a.child("nombre").getValue().toString();
                            String desc = a.child("descripcion").getValue().toString();
                            String url = a.child("imagen1").getValue().toString();
                            String comunidad = a.child("comunidad").getValue().toString();
                            Alojamientos alojamien = new Alojamientos(nombre, desc, url);
                            if (comunidad.equals(comuni)) {
                                aloja.add(alojamien);
                            }

                        }
                    }
                    adapterItem= new AdaptardorListV(AlojamientosActivity.this,aloja);
                    listViewItem.setAdapter(adapterItem);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void resetContra(){
        Intent alojamientosCre= new Intent(getApplicationContext(),CambiarContraActivity.class);
        startActivityForResult(alojamientosCre,0);

    }

    public String generarURLAlo(String url){
        String[] array= url.split("/");
        String imagenUrlAlo="https://drive.google.com/uc?export=download&id="+array[5];
        return  imagenUrlAlo;
    }
}
