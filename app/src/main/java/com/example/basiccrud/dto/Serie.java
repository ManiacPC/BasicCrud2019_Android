package com.example.basiccrud.dto;

import java.io.Serializable;

public class Serie implements Serializable {
    private int id;
    private String nombre;
    private String categoria;
    private int capitulos;

    public Serie() {
    }

    public Serie(int id, String nombre, String categoria, int capitulos) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.capitulos = capitulos;
    }

    public Serie(String nombre, String categoria, int capitulos) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.capitulos = capitulos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public void setCapitulos(int capitulos) {
        this.capitulos = capitulos;
    }

    @Override
    public String toString()
    {
        return (this.getNombre() + " || " + this.getCategoria() + " (" + getCapitulos() + " capitulos)");
    }


}
