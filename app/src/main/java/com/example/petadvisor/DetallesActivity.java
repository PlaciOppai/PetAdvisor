package com.example.petadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

public class DetallesActivity extends AppCompatActivity {

    String []imagenes;
    String nombre;
    String descripcion;
    String comunidad;
    String precio;
    String dinero;
    String usuario;

    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        //caramos los elementos del alojamiento que nos hemos pasado en las distintas variables
        imagenes=getIntent().getStringArrayExtra("imagenes");
        nombre=getIntent().getStringExtra("nombreAlo");
        descripcion=getIntent().getStringExtra("descripcion");
        comunidad=getIntent().getStringExtra("comunidad");
        dinero=getIntent().getStringExtra("precio");
        precio="Precio por una noche es de " + getIntent().getStringExtra("precio")+"€";
        usuario=getIntent().getStringExtra("usuario");

        viewFlipper=findViewById(R.id.ViewFlipper);
        for (int i = 0; i < imagenes.length-1; i++) {
            fliper(i);
        }
        ((TextView)findViewById(R.id.textViewDnombre)).setText(nombre);
        ((TextView)findViewById(R.id.textViewDdescripcion)).setText(descripcion);
        ((TextView)findViewById(R.id.textViewDnombre)).setText(nombre);
        ((TextView)findViewById(R.id.textViewDprecio)).setText(precio);

        //nos lleva a la reserva con los datos del usuario nombre de alojamiento y dinero que cuesta pasar una noche
        ((Button)findViewById(R.id.buttonReservar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ReservasActivity.class);
                intent.putExtra("nombreAlo",nombre);
                intent.putExtra("usuario",usuario);
                intent.putExtra("precio",dinero);
                startActivityForResult(intent,0);
            }
        });

    }

    //se encarga del carrusel de imagenes y de que vayan cambiando las fotos cada 3segundos
    public void fliper(int numImage){
        ImageView imageView=new ImageView(this);
        Picasso.get().load(generarURL(imagenes[numImage])).into(imageView);

        viewFlipper.setInAnimation(this,android.R.anim.fade_out);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

    }

    //genera una url accesible para cargar las fotos
    public String generarURL(String url){
        String[] array= url.split("/");
        String imagenUrlAlo="https://drive.google.com/uc?export=download&id="+array[5];
        return  imagenUrlAlo;
    }
}
