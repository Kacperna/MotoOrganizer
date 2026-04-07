package com.example.motoorganizer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddVehicleActivity extends AppCompatActivity {

    private EditText daneNazwa, danePrzebieg;
    private Spinner Spinner;
    private Button zapisz, anuluj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        daneNazwa = findViewById(R.id.daneNazwa);
        danePrzebieg = findViewById(R.id.danePrzebieg);
        Spinner = findViewById(R.id.spinner);
        zapisz = findViewById(R.id.zapisz);
        anuluj = findViewById(R.id.anuluj);

        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        anuluj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}