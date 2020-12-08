package com.example.petadvisor;

public class Alojamientos {

    private String nombre;
    private String comunidad;
    private String descripcion;
    private String[]imagenes;
    private String precio;

    public Alojamientos(String nombre, String comunidad, String descripcion, String []imagenes) {
        this.nombre = nombre;
        this.comunidad = comunidad;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
    }

    public Alojamientos(String nombre, String descripcion, String []imagenes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(String[] imagenes) {
        this.imagenes = imagenes;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
