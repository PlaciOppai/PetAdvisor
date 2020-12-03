package com.example.petadvisor;

public class Alojamientos {

    private String nombre;
    private String comunidad;
    private String descripcion;
    private String urlimagen;

    public Alojamientos(String nombre, String comunidad, String descripcion, String urlimagen) {
        this.nombre = nombre;
        this.comunidad = comunidad;
        this.descripcion = descripcion;
        this.urlimagen = urlimagen;
    }

    public Alojamientos(String nombre, String descripcion, String urlimagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlimagen = urlimagen;
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

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }
}
