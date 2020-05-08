package com.example.cocktailapp.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cocktailapp.model.DBDrink

@Database(entities = [DBDrink::class], version = 1)
@TypeConverters(IngredientsConverter::class)
abstract class DataBase: RoomDatabase() {

    abstract fun dao(): DAO
}