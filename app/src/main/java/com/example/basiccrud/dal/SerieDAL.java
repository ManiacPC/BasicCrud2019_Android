package com.example.basiccrud.dal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.basiccrud.dto.Serie;
import com.example.basiccrud.helpers.DatabaseHelper;

import java.util.ArrayList;

public class SerieDAL {
    private DatabaseHelper dbHelper; // obtener el helper
    private Serie serie;

    public SerieDAL(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.serie = new Serie();
        // Testing
        SQLiteDatabase db = dbHelper.getWritableDatabase();
    }

    public SerieDAL(Context context, Serie serie) {
        this.dbHelper = new DatabaseHelper(context);
        this.serie = serie;
    }

    public boolean insertar() {
        return this.tryInsert();
    }

    public boolean insertar(String nombre, String categoria, int capitulos)
    {
        this.serie.setNombre(nombre);
        this.serie.setCategoria(categoria);
        this.serie.setCapitulos(capitulos);

        return this.tryInsert();
    }

    public ArrayList<Serie> seleccionar()
    {
        ArrayList<Serie> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor consulta = db.rawQuery("SELECT * FROM serie", null);

        if(consulta.moveToFirst()) {
            do {
                int id = consulta.getInt(0);
                String nombre = consulta.getString(1);
                String categoria = consulta.getString(2);
                int capitulos = consulta.getInt(3);

                Serie serie = new Serie(id,nombre,categoria,capitulos);
                lista.add(serie);
                /*
                // forma B
                Serie serie = new Serie();
                serie.setId(id);
                serie.setNombre(nombre);*/

            } while(consulta.moveToNext());

        }

        // EJ: Where con parámetros
        // Cursor consulta = db.rawQuery("SELECT * FROM serie WHERE categoria = ?", new String[]{ String.valueOf("Sci-fi") });

        return lista;
    }


    public boolean actualizar(int id, Serie serie)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues c = new ContentValues(); // Objeto tipo clave-valor
        c.put("nombre", serie.getNombre());
        c.put("categoria", serie.getCategoria());
        c.put("capitulos", serie.getCapitulos());
        try {
            int filasAfectadas;
            filasAfectadas = db.update(
                    "serie",
                    c,
                    "id = ?",
                    new String[] { String.valueOf(id) }
                    );
            // if(filasAfectadas > 0) return true; else return false;
            return (filasAfectadas > 0);
        } catch (Exception e) {

        }

        return false;
    }

    public boolean actualizar(Serie serie)
    {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues c = new ContentValues(); // Objeto tipo clave-valor
        c.put("nombre", serie.getNombre());
        c.put("categoria", serie.getCategoria());
        c.put("capitulos", serie.getCapitulos());
        try {
            int filasAfectadas;
            filasAfectadas = db.update(
                    "serie",
                    c,
                    "id = ?",
                    new String[] { String.valueOf(serie.getId()) }
            );
            // if(filasAfectadas > 0) return true; else return false;
            return (filasAfectadas > 0);
        } catch (Exception e) {

        }

        return false;
    }

    public boolean eliminar(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int filasAfectadas;

/*        db.delete("serie","id = ? and nombre = ?",
                new String[] {
                        String.valueOf(id),
                        String.valueOf("the boys")
                });*/

        try {
            filasAfectadas = db.delete("serie","id = ?",
                    new String[] { String.valueOf(id) });
        } catch (Exception e) {
            return false;
        }

        return (filasAfectadas == 1);

    }


    private boolean tryInsert() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

/*
        String[] argumentos = new String[]{"Breaking Bad","Suspenso","36"};
        db.execSQL("INSERT INTO serie(nombre,categoria,capitulos) VALUES(?,?,?);", argumentos);
*/

        ContentValues c = new ContentValues(); // Objeto tipo clave-valor
        c.put("nombre", this.serie.getNombre());
        c.put("categoria", this.serie.getCategoria());
        c.put("capitulos", this.serie.getCapitulos());

        try {
            db.insert("serie", null, c);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public Serie getSerie()
    {
        return this.serie;
    }
}
