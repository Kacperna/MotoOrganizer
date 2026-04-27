package com.example.motoorganizer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PojazdDao {

    // Dodaje nowy pojazd do bazy.
    @Insert
    void insert(Pojazd pojazd);

    // Aktualizuje istniejący pojazd.
    @Update
    void update(Pojazd pojazd);

    // Usuwa wybrany pojazd.
    @Delete
    void delete(Pojazd pojazd);

    // Pobiera wszystkie pojazdy z bazy.
    @Query("SELECT * FROM Pojazd")
    List<Pojazd> getAll();

    // Pobiera jeden pojazd na podstawie jego ID.
    @Query("SELECT * FROM Pojazd WHERE id = :id LIMIT 1")
    Pojazd getById(int id);

    // Usuwa wszystkie pojazdy z tabeli.
    @Query("DELETE FROM Pojazd")
    void deleteAll();
}
