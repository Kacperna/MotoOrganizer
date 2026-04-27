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

    // Pola tekstowe, w które użytkownik wpisuje nazwę i przebieg pojazdu
    private EditText daneNazwa, danePrzebieg;

    // Lista rozwijana z wyborem typu pojazdu
    private Spinner spinner;

    // Przyciski: zapisania danych i anulowania dodawania
    private Button zapisz, anuluj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        // Podłączenie elementów z layoutu do zmiennych w kodzie
        daneNazwa = findViewById(R.id.daneNazwa);
        danePrzebieg = findViewById(R.id.danePrzebieg);
        spinner = findViewById(R.id.spinner);
        zapisz = findViewById(R.id.zapisz);
        anuluj = findViewById(R.id.anuluj);

        // Lista dostępnych typów pojazdów do wyboru
        String[] typyPojazdow = {"Motocykl", "Samochód", "Inne"};

        // Adapter na elementy widoczne w spinnerze
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                typyPojazdow
        );

        // Ustawienie wyglądu rozwijanej listy
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Podłączenie adaptera do spinnera
        spinner.setAdapter(adapter);

        // Obsługa kliknięcia przycisku "Zapisz"
        zapisz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Pobranie danych wpisanych przez użytkownika
                String nazwa = daneNazwa.getText().toString();
                String przebieg = danePrzebieg.getText().toString();
                String typ = spinner.getSelectedItem().toString();

                // Tworzymy Intent, który przekaże dane z powrotem do MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("nazwa", nazwa);
                resultIntent.putExtra("przebieg", przebieg);
                resultIntent.putExtra("typ", typ);

                // Ustawiamy wynik i kończymy aktywność
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        // Obsługa kliknięcia przycisku "Anuluj"
        // Po kliknięciu po prostu zamykamy ekran bez zapisywania
        anuluj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
