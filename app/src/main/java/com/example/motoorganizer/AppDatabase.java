package com.example.motoorganizer;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Pojazd.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    //udostępniamy DAO.
    public abstract PojazdDao pojazdDao();

    // Metoda zwracająca instancję bazy danych.
    // Jeśli baza jeszcze nie istnieje, zostanie utworzona.
    public static AppDatabase getInstance(Context context) {

        // Sprawdzamy, czy baza została już utworzona.
        if (INSTANCE == null) {

            // Tworzymy bazę danych.
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "baza_pojazdow"
                    )
                    // Jeśli zmieni się wersja bazy, a nie ma migracji,
                    // stara baza zostanie usunięta i utworzona od nowa.
                    .fallbackToDestructiveMigration()

                    // Pozwala wykonywać operacje na bazie w głównym wątku.
                    .allowMainThreadQueries()

                    // Tworzy gotową instancję bazy.
                    .build();
        }

        // Zwracamy gotową bazę.
        return INSTANCE;
    }
}
