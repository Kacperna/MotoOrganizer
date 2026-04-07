package com.example.motoorganizer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView pojazdyLista;
    private TextView tekst;
    private Button dodajPojazd;

    private ArrayList<String> pojazdy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pojazdyLista = findViewById(R.id.pojazdyLista);
        tekst = findViewById(R.id.pustyTekst);
        dodajPojazd = findViewById(R.id.dodajPojazd);

        pojazdy = new ArrayList<>();

        updateUI();

        dodajPojazd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dodajPojazd = new Intent(MainActivity.this, AddVehicleActivity.class);
                startActivity(dodajPojazd);
            }
        });
    }

    private void updateUI() {
        if (pojazdy.isEmpty()) {
            tekst.setVisibility(View.VISIBLE);
            pojazdyLista.setVisibility(View.GONE);
        } else {
            tekst.setVisibility(View.GONE);
            pojazdyLista.setVisibility(View.VISIBLE);

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    pojazdy
            );

            pojazdyLista.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }
}