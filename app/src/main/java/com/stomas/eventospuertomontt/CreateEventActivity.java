package com.stomas.eventospuertomontt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stomas.eventospuertomontt.databinding.ActivityCreateEventBinding;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Locale;

public class CreateEventActivity extends AppCompatActivity {
    // time
    private DatePickerDialog datePickerDialog;
    Button dateButton;

    ActivityCreateEventBinding binding;
    String nombre, organizador, hora, fecha, direccion, descripcion;
    FirebaseDatabase db;
    DatabaseReference reference;
    Button btnCancelCreate;
    Button btnCrearNuevoEvento;

    // time
    Button timeButton;
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        initDatePicker();
        dateButton = findViewById(R.id.dateButtonDialog);
        dateButton.setText(getToDaysDate());
        timeButton = findViewById(R.id.timeButton);

        binding = ActivityCreateEventBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());

        binding.btnGuardarEventoNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nombre = binding.nombreTxt.getText().toString();
                organizador = binding.organizadorTxt.getText().toString();
                fecha = binding.dateButtonDialog.getText().toString();
                hora = binding.timeButton.getText().toString();
                direccion = binding.lugarTxt.getText().toString();
                descripcion = binding.descripcionTxt.getText().toString();

                if (!nombre.isEmpty() && !organizador.isEmpty() && !direccion.isEmpty() && !descripcion.isEmpty()){

                    Eventos eventos = new Eventos(nombre, organizador, fecha, hora, direccion, descripcion);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Eventos");
                    reference.child(nombre).setValue(eventos).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.nombreTxt.setText("Nombre del Evento");
                            binding.organizadorTxt.setText("Organizador");
                            binding.lugarTxt.setText("Lugar del Evento");
                            binding.descripcionTxt.setText("Descripcion del evento");
                            Toast.makeText(CreateEventActivity.this, "Guardado correctamente", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        btnCancelCreate = findViewById(R.id.btn_cancelarCreacion);
        btnCancelCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateEventActivity.this, AdmMenu.class);
                startActivity(intent);
            }
        });
    }

    private String getToDaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }


    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "ENE";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "ABR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AGO";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DIC";
        return "ENE";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
    public void popTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Hora");
        timePickerDialog.show();
    }
}