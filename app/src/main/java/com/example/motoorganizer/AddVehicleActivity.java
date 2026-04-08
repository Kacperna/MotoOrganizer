package com.example.motoorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class AddVehicleActivity extends AppCompatActivity {

    private EditText daneNazwa, danePrzebieg;
    private Spinner spinner;
    private Button zapisz, anuluj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        daneNazwa = findViewById(R.id.daneNazwa);
        danePrzebieg = findViewById(R.id.danePrzebieg);
        spinner = findViewById(R.id.spinner);
        zapisz = findViewById(R.id.zapisz);
        anuluj = findViewById(R.id.anuluj);
        String[] typyPojazdow = {"Motocykl", "Samochód", "Inne"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                typyPojazdow
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nazwa = daneNazwa.getText().toString();
                String przebieg = danePrzebieg.getText().toString();
                String typ = spinner.getSelectedItem().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("nazwa", nazwa);
                resultIntent.putExtra("przebieg", przebieg);
                resultIntent.putExtra("typ", typ);

                setResult(RESULT_OK, resultIntent);
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