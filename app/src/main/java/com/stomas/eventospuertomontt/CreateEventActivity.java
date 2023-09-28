package com.stomas.eventospuertomontt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateEventActivity extends AppCompatActivity {
    Button btnCancelCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        btnCancelCreate = findViewById(R.id.btn_cancelarCreacion);
        btnCancelCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateEventActivity.this, AdmMenu.class);
                startActivity(intent);
            }
        });
    }
}