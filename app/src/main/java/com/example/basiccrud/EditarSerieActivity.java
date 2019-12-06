package com.example.basiccrud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.basiccrud.dal.SerieDAL;
import com.example.basiccrud.dto.Serie;

public class EditarSerieActivity extends AppCompatActivity {
    private EditText editNombre;
    private EditText editCategoria;
    private EditText editCapitulos;
    private Button btnActualizar;
    private SerieDAL serieDAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_serie);

        this.editNombre = (EditText) findViewById(R.id.editNombre);
        this.editCategoria = (EditText) findViewById(R.id.editCategoria);
        this.editCapitulos = (EditText) findViewById(R.id.editCapitulos);
        this.btnActualizar = (Button) findViewById(R.id.btnActualizar);

        this.serieDAL = new SerieDAL(getApplicationContext(), (Serie) getIntent().getSerializableExtra("serie") );


        this.editNombre.setText(serieDAL.getSerie().getNombre());
        this.editCategoria.setText(serieDAL.getSerie().getCategoria());
        this.editCapitulos.setText(String.valueOf(serieDAL.getSerie().getCapitulos()));
        // Acción para el botón actualizar
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Serie s = serieDAL.getSerie();
                s.setNombre(String.valueOf(editNombre.getText()));
                s.setCategoria(String.valueOf(editCategoria.getText()));
                s.setCapitulos(Integer.parseInt(String.valueOf(editCapitulos.getText())));

                if(serieDAL.actualizar(s)) {
                    Toast.makeText(getApplicationContext(), "Actualizado!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "NO Actualizado!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
