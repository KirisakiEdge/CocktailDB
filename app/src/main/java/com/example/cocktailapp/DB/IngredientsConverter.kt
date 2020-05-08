package com.example.cocktailapp.DB

import android.text.TextUtils
import androidx.room.TypeConverter

class IngredientsConverter {
    @TypeConverter
    fun fromIngredients(ingredients: ArrayList<String>): String {
        return TextUtils.join(", ", ingredients)

    }

    @TypeConverter
    fun toIngredients(data: String): ArrayList<String> {
        return data.replace("\\s+".toRegex(), " ").trim().split(", ") as ArrayList<String>
    }
}