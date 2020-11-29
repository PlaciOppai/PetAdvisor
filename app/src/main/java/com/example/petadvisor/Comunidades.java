package com.example.petadvisor;

public class Comunidades {

    private int id=0;
    private String nombre;

    public Comunidades(String nombre) {
        id++;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
