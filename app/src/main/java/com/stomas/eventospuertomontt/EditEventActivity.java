package com.stomas.eventospuertomontt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditEventActivity extends AppCompatActivity {
    Button btnVolverAdmM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);

        btnVolverAdmM = findViewById(R.id.btn_volver);
        btnVolverAdmM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditEventActivity.this, AdmMenu.class);
                startActivity(intent);
            }
        });
    }
}