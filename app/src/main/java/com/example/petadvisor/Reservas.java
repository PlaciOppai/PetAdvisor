package com.example.petadvisor;

import java.util.Date;

public class Reservas {
    private String nombre;
    private String fechaIni;
    private String fechaFin;
    private int importe;
    private String nAlojamiento;
    private static int id=0;

    public Reservas(String nombre, String fechaIni, String fechaFin, int importe, String nAlojamiento) {
        this.nombre = nombre;
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
        this.importe = importe;
        this.nAlojamiento = nAlojamiento;
        id++;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getImporte() {
        return importe;
    }

    public void setImporte(int importe) {
        this.importe = importe;
    }

    public String getnAlojamiento() {
        return nAlojamiento;
    }

    public void setnAlojamiento(String nAlojamiento) {
        this.nAlojamiento = nAlojamiento;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Reservas.id = id;
    }

    @Override
    public String toString() {
        return  "Alojamiento: "+getnAlojamiento()+
                "\nFecha inicio: "+getFechaIni()+
                "\tImporte: "+getImporte()+"€";


    }
}
