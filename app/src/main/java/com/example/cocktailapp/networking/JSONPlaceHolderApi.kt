package com.example.cocktailapp.networking

import com.example.cocktailapp.model.Drink
import com.example.cocktailapp.model.DrinksList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface JSONPlaceHolderApi {
    @GET("search.php")
    fun getDrinksByName(@Query("s") drinksName:String): Call<DrinksList>

    @GET("search.php")
    fun getDrinkByName(@Query("s") drinkName:String): Call<Drink>

    @GET("lookup.php")
    fun getDrinkById(@Query("i")idDrink:String): Call<DrinksList>
}