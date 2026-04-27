package com.example.motoorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.room.Room;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // lista, w której wyświetlamy wszystkie pojazdy
    private ListView pojazdyLista;

    //baza danych – tutaj trzymamy pojazdy
    private AppDatabase db;

    //tekst pokazuje się, gdy lista pojazdów jest pusta
    private TextView tekst;

    // Przycisk do dodawania nowego pojazdu
    private Button dodajPojazd;

    // Lista pojazdów pobrana z bazy
    private ArrayList<Pojazd> pojazdy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // pobieranie instancji bazy danych.
        db = AppDatabase.getInstance(this);

        pojazdyLista = findViewById(R.id.pojazdyLista);
        tekst = findViewById(R.id.pustyTekst);
        dodajPojazd = findViewById(R.id.dodajPojazd);

        // Pobieramy wszystkie pojazdy z bazy i wrzucamy do listy
        pojazdy = new ArrayList<>(db.pojazdDao().getAll());

        // Odświeżamy wygląd ekranu – pokaż listę albo tekst "pusto"
        updateUI();

        // Obsługa kliknięcia przycisku "Dodaj pojazd"
        // Po kliknięciu przechodzimy do ekranu dodawania pojazdu
        dodajPojazd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddVehicleActivity.class);
                // startActivityForResult – wrócimy tu z danymi nowego pojazdu
                startActivityForResult(intent, 1);
            }
        });

        // Obsługa kliknięcia na konkretny pojazd z listy
        pojazdyLista.setOnItemClickListener((parent, view, position, id) -> {

            // Pobieramy pojazd, na który kliknął użytkownik
            Pojazd pojazd = pojazdy.get(position);

            // Przechodzimy do ekranu szczegółów pojazdu
            Intent intent = new Intent(MainActivity.this, VehicleDetailsActivity.class);

            // Przekazujemy ID pojazdu, żeby drugi ekran wiedział, który pojazd otworzyć
            intent.putExtra("id", pojazd.id);

            startActivity(intent);
        });
    }

    // Ta metoda odświeża wygląd ekranu
    // Sprawdza, czy są jakieś pojazdy i odpowiednio pokazuje listę lub tekst
    private void updateUI() {

        // Ponownie pobieramy wszystkie pojazdy z bazy
        pojazdy = new ArrayList<>(db.pojazdDao().getAll());

        // Jeśli lista jest pusta – pokazujemy tekst "brak pojazdów"
        if (pojazdy.isEmpty()) {
            tekst.setVisibility(View.VISIBLE);
            pojazdyLista.setVisibility(View.GONE);
        } else {
            // Jeśli są pojazdy – chowamy tekst i pokazujemy listę
            tekst.setVisibility(View.GONE);
            pojazdyLista.setVisibility(View.VISIBLE);

            // Adapter zamienia listę pojazdów na elementy listy widoczne na ekranie
            ArrayAdapter<Pojazd> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    pojazdy
            );

            // Podpinamy adapter do listy
            pojazdyLista.setAdapter(adapter);
        }
    }

    // Ta metoda odpala się, kiedy wracamy z ekranu dodawania pojazdu
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Sprawdzamy, czy wróciliśmy z dodawania pojazdu i czy wszystko poszło OK
        if (requestCode == 1 && resultCode == RESULT_OK) {

            // Pobieramy dane nowego pojazdu, które przyszły z AddVehicleActivity
            String nazwa = data.getStringExtra("nazwa");
            String przebieg = data.getStringExtra("przebieg");
            String typ = data.getStringExtra("typ");

            // Tworzymy nowy obiekt pojazdu
            Pojazd pojazd = new Pojazd(nazwa, przebieg, typ);

            // Wrzucamy go do bazy danych
            db.pojazdDao().insert(pojazd);

            // Odświeżamy ekran, żeby nowy pojazd pojawił się na liście
            updateUI();
        }
    }
}
