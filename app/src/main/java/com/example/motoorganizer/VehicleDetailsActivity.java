package com.example.motoorganizer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehicleDetailsActivity extends AppCompatActivity {

    // Pola tekstowe wyświetlające dane pojazdu
    private TextView nazwaTekst, przebiegTekst, typTekst;

    // Przyciski: wyjście, zapis oraz dodanie aktywności
    private Button buttonWyjdz, buttonZapisz, przyciskDodajAktywnosc;

    // Lista wyświetlająca aktywności pojazdu
    private ListView listaAktywnosci;

    // Obiekt pojazdu pobrany z bazy
    private Pojazd pojazd;

    // Dostęp do bazy danych
    private AppDatabase baza;

    // Lista aktywności w formie tekstowej
    private List<String> aktywnosciLista = new ArrayList<>();

    // Adapter do wyświetlania aktywności w ListView
    private ArrayAdapter<String> adapterAktywnosci;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);

        // Pobranie instancji bazy danych
        baza = AppDatabase.getInstance(this);

        // Powiązanie elementów z layoutu
        nazwaTekst = findViewById(R.id.nazwaTekst);
        przebiegTekst = findViewById(R.id.przebiegTekst);
        typTekst = findViewById(R.id.typTekst);

        buttonWyjdz = findViewById(R.id.wyjdz);
        buttonZapisz = findViewById(R.id.zapisz);
        przyciskDodajAktywnosc = findViewById(R.id.przyciskDodajAktywnosc);
        listaAktywnosci = findViewById(R.id.listaAktywnosci);

        // Pobranie ID pojazdu przekazanego z MainActivity
        int id = getIntent().getIntExtra("id", -1);

        // Pobranie pojazdu z bazy na podstawie ID
        pojazd = baza.pojazdDao().getById(id);

        // Ustawienie danych pojazdu na ekranie
        nazwaTekst.setText("Nazwa: " + pojazd.nazwa);
        przebiegTekst.setText("Przebieg: " + pojazd.przebieg + " km");
        typTekst.setText("Typ: " + pojazd.typ);

        // Wczytanie aktywności zapisanych w bazie
        // Aktywności są zapisane jako jeden długi tekst oddzielony średnikami
        if (pojazd.getAktywnosci() != null && !pojazd.getAktywnosci().isEmpty()) {
            String[] tab = pojazd.getAktywnosci().split(";");
            aktywnosciLista.addAll(Arrays.asList(tab));
        }

        // Adapter do wyświetlania aktywności w liście
        adapterAktywnosci = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                aktywnosciLista
        );

        listaAktywnosci.setAdapter(adapterAktywnosci);

        // Przycisk zamykający ekran
        buttonWyjdz.setOnClickListener(v -> finish());

        // Przycisk zapisujący zmiany (np. przebieg)
        // W tej wersji nic nie zmienia, tylko zamyka ekran
        buttonZapisz.setOnClickListener(v -> {
            finish();
        });

        // Przycisk dodający nową aktywność
        przyciskDodajAktywnosc.setOnClickListener(v -> dodajAktywnosc());
    }

    // Metoda odpowiedzialna za dodanie nowej aktywności
    private void dodajAktywnosc() {

        // Okno dialogowe z polem tekstowym
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Dodaj aktywność");

        final EditText input = new EditText(this);
        builder.setView(input);

        // Obsługa kliknięcia przycisku "Dodaj"
        builder.setPositiveButton("Dodaj", (dialog, which) -> {
            String nowa = input.getText().toString().trim();

            // Jeśli wpisano jakikolwiek tekst, dodajemy go do listy
            if (!nowa.isEmpty()) {
                aktywnosciLista.add(nowa);

                // Zapis aktywności do bazy w formie jednego tekstu oddzielonego średnikami
                StringBuilder sb = new StringBuilder();
                for (String s : aktywnosciLista) {
                    sb.append(s).append(";");
                }

                // Aktualizacja pojazdu w bazie
                pojazd.setAktywnosci(sb.toString());
                baza.pojazdDao().update(pojazd);

                // Odświeżenie listy na ekranie
                adapterAktywnosci.notifyDataSetChanged();
            }
        });

        // Przycisk anulujący dodawanie
        builder.setNegativeButton("Anuluj", null);

        // Wyświetlenie okna dialogowego
        builder.show();
    }
}
