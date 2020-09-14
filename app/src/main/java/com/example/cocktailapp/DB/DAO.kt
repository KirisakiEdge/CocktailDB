package com.example.cocktailapp.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cocktailapp.model.DBDrink

@Dao
interface DAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveNewDrink(dbDrink: DBDrink)

    @Query("SELECT * FROM DBDrink")
    fun drinks(): List<DBDrink>

    @Query("SELECT * FROM DBDrink WHERE idDrink IN (:userIds)")
    fun loadDrinkById(userIds: String): DBDrink

    @Query("DELETE FROM DBDrink")
    fun deleteAllDrinks()
}