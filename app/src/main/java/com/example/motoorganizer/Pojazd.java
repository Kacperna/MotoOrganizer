package com.example.motoorganizer;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pojazd {

    // Główne pole identyfikujące pojazd.
    @PrimaryKey(autoGenerate = true)
    public int id;

    // Nazwa pojazdu wpisana przez użytkownika.
    public String nazwa;

    // Przebieg pojazdu wpisany przez użytkownika.
    public String przebieg;

    // Typ pojazdu (np. motocykl, samochód, inne).
    public String typ;

    // Pole przechowujące aktywności związane z pojazdem.
    // Na początku jest puste, później może być uzupełniane.
    public String aktywnosci = "";

    public Pojazd(String nazwa, String przebieg, String typ) {
        this.nazwa = nazwa;
        this.przebieg = przebieg;
        this.typ = typ;
    }

    // Zwraca zapisane aktywności pojazdu.
    public String getAktywnosci() {
        return aktywnosci;
    }

    // Ustawia nowe aktywności dla pojazdu.
    public void setAktywnosci(String aktywnosci) {
        this.aktywnosci = aktywnosci;
    }

    // To, co ma się wyświetlać na liście pojazdów.
    @Override
    public String toString() {
        return nazwa + " | " + przebieg + " km | " + typ;
    }
}
