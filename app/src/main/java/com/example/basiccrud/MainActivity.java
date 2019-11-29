package com.example.basiccrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.basiccrud.dal.SerieDAL;
import com.example.basiccrud.dto.Serie;

public class MainActivity extends AppCompatActivity {
    private SerieDAL serieDAL;
    private ListView listSeries;
    private ArrayAdapter<Serie> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.serieDAL = new SerieDAL(getApplicationContext(), new Serie());
        // i.- Enlazar la interfaz gráfica al componente
        this.listSeries = (ListView) findViewById(R.id.listSeries);

        // ii.- Crear ArrayAdapter y asociarlo al cRud
        this.adapter = new ArrayAdapter<Serie>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                new SerieDAL(getBaseContext()).seleccionar()
        );

        // iii.- Asociar el ArrayAdapter al componente ListView
        this.listSeries.setAdapter(adapter);

        // Testing (No lo hagan en casa)
/*
        // TEST DE ELIMINAR
        if(serieDAL.eliminar(1)) {
            Toast.makeText(
                    getApplicationContext(),
                    "Se eliminó!",
                    Toast.LENGTH_LONG
            ).show();
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "NO se eliminó!",
                    Toast.LENGTH_LONG
            ).show();
        }*/

/*
        // TEST INSERTAR
        Serie s = new Serie("The boys", "Sci-Fi", 8);
        this.serieDAL = new SerieDAL(getApplicationContext(), s);

        if(serieDAL.insertar()) {
            Toast.makeText(getApplicationContext(), "OK! Insertó", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "MAL! NO Insertó", Toast.LENGTH_LONG).show();

        }*/

    }
}
