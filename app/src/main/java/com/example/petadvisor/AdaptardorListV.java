package com.example.petadvisor;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.example.petadvisor.R.id.imageViewItem;

public class AdaptardorListV extends BaseAdapter {

    private Context context;
    private ArrayList<Alojamientos> listaAlojamientos;

    public AdaptardorListV(Context context,ArrayList<Alojamientos> listaAlojamientos) {
        this.context=context;
        this.listaAlojamientos = listaAlojamientos;

    }

    @Override
    public int getCount() {
        return listaAlojamientos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaAlojamientos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Alojamientos itemAlo= (Alojamientos) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.item_listviewper, null);
        ImageView imagen=convertView.findViewById(imageViewItem);
        TextView tvNombre =convertView.findViewById(R.id.tvItemNombre);
        TextView tvDesc = convertView.findViewById(R.id.tvItemDesc);



        Picasso.get().load(generarURL(itemAlo.getUrlimagen())).resize(50,50).into(imagen);
        tvNombre.setText(itemAlo.getNombre());
        tvDesc.setText(itemAlo.getDescripcion());

        return convertView;
    }

     public String generarURL(String url){
        String[] array= url.split("/");
        String imagenUrl="https://drive.google.com/uc?export=download&id="+array[5];
        return  imagenUrl;
     }
}
