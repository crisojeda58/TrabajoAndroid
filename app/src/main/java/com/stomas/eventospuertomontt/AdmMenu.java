package com.stomas.eventospuertomontt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdmMenu extends AppCompatActivity {

    Button btnEditEvent;
    Button btnCreateEvent;
    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admmenu);

        //ir a editar evento
        btnEditEvent = findViewById(R.id.btn_editarEvento);
        btnEditEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdmMenu.this, EditEventActivity.class);
                startActivity(intent);
            }
        });

        //ir a Crear evento
        btnCreateEvent = findViewById(R.id.btn_crearEvento);
        btnCreateEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdmMenu.this, CreateEventActivity.class);
                startActivity(intent);
            }
        });

        //ir a cerrar sesion
        btnLogOut = findViewById(R.id.btn_cerrarAdm);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdmMenu.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}