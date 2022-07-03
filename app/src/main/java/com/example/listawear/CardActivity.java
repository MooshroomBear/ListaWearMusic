package com.example.listawear;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CardActivity extends Activity {
    private TextView titulo;
    private TextView descripcion;
    private Button cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card);

        titulo = (TextView) findViewById(R.id.txtTitulo);
        descripcion = (TextView) findViewById(R.id.txtDescripcion);
        cerrar = (Button) findViewById(R.id.btnCerrar);

        Bundle extras = getIntent().getExtras();
        if( extras != null){
            titulo.setText(extras.getString("titulo"));
            descripcion.setText(extras.getString("descripcion"));
        }

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
