package com.example.basiccrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.basiccrud.dal.SerieDAL;
import com.example.basiccrud.dto.Serie;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SerieDAL serieDAL;
    private ListView listSeries;
    private ArrayAdapter<Serie> adapter;
    private ArrayList<Serie> listaSeries;
    private int codPosicion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.serieDAL = new SerieDAL(getApplicationContext(), new Serie());
        this.listaSeries = new SerieDAL(getBaseContext()).seleccionar();

        // i.- Enlazar la interfaz gráfica al componente
        this.listSeries = (ListView) findViewById(R.id.listSeries);

        // ii.- Crear ArrayAdapter y asociarlo al cRud
        this.adapter = new ArrayAdapter<Serie>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                this.listaSeries
        );

        // iii.- Asociar el ArrayAdapter al componente ListView
        this.listSeries.setAdapter(adapter);

        // Sólo para construir el mensaje de diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirmación");
        builder.setMessage("¿Desea borrar la serie?");
        builder.setPositiveButton("Si, borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int id = ((Serie) listaSeries.get(codPosicion)).getId();
                        boolean r = serieDAL.eliminar(id);
                        if(r){
                            Toast.makeText(getApplicationContext(), "Se ha eliminado correctamente", Toast.LENGTH_SHORT).show();
                            actualizarLista();
                        } else {
                            Toast.makeText(getApplicationContext(), "No se ha podido eliminar la serie", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog dialog = builder.create();

        listSeries.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                codPosicion = posicion;
                dialog.show();
                return true;
            }
        });
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

    private void actualizarLista() {
        adapter.clear();
        adapter.addAll(serieDAL.seleccionar());
        adapter.notifyDataSetChanged();
    }
}
