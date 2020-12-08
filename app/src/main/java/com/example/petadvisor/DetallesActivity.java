package com.example.petadvisor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        imagenes=getIntent().getStringArrayExtra("imagenes");
        nombre=getIntent().getStringExtra("nombreAlo");
        descripcion=getIntent().getStringExtra("descripcion");
        comunidad=getIntent().getStringExtra("comunidad");
        precio="Precio por una noche es de " + getIntent().getStringExtra("precio")+"€";

        viewFlipper=findViewById(R.id.ViewFlipper);
        for (int i = 0; i < imagenes.length-1; i++) {
            fliper(i);
        }
        ((TextView)findViewById(R.id.textViewDnombre)).setText(nombre);
        ((TextView)findViewById(R.id.textViewDdescripcion)).setText(descripcion);
        ((TextView)findViewById(R.id.textViewDnombre)).setText(nombre);
        ((TextView)findViewById(R.id.textViewDprecio)).setText(precio);
    }

    public void fliper(int numImage){
        ImageView imageView=new ImageView(this);
        Picasso.get().load(generarURL(imagenes[numImage])).into(imageView);

        viewFlipper.setInAnimation(this,android.R.anim.fade_out);
        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

    }

    public String generarURL(String url){
        String[] array= url.split("/");
        String imagenUrlAlo="https://drive.google.com/uc?export=download&id="+array[5];
        return  imagenUrlAlo;
    }
}
