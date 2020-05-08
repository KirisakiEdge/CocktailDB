package com.example.cocktailapp.model

import androidx.room.*
import java.io.Serializable
import kotlin.collections.ArrayList


@Entity(tableName = "DBDrink")
class DBDrink : Serializable {

    @PrimaryKey
    var idDrink : String = ""

    @ColumnInfo(name = "strDrink")
    var strDrink: String = ""

    @ColumnInfo(name = "strCategory")
    var strCategory: String = ""

    @ColumnInfo(name = "strAlcoholic")
    var strAlcoholic: String = ""

    @ColumnInfo(name = "strGlass")
    var strGlass: String = ""

    @ColumnInfo(name = "strInstructions")
    var strInstructions: String? = null

    @ColumnInfo(name = "strDrinkThumb")
    var strDrinkThumb: String? = null

    @ColumnInfo(name = "strIngredient1")
    var strIngredient1: String? = null

    @ColumnInfo(name = "strIngredient2")
    var strIngredient2: String? = null

    @ColumnInfo(name = "strIngredient3")
    var strIngredient3: String? = null

    @ColumnInfo(name = "strIngredient4")
    var strIngredient4: String? = null

    @ColumnInfo(name = "strIngredient5")
    var strIngredient5: String? = null

    @ColumnInfo(name = "strIngredient6")
    var strIngredient6: String? = null

    @ColumnInfo(name = "strMeasure1")
    var strMeasure1: String = ""

    @ColumnInfo(name = "strMeasure2")
    var strMeasure2: String = ""

    @ColumnInfo(name = "strMeasure3")
    var strMeasure3: String = ""

    @ColumnInfo(name = "strMeasure4")
    var strMeasure4: String = ""

    @ColumnInfo(name = "strMeasure5")
    var strMeasure5: String = ""

    @ColumnInfo(name = "strMeasure6")
    var strMeasure6: String = ""

    @ColumnInfo(name = "ingredients")
    var ingredients: ArrayList<String> = ArrayList()

    @ColumnInfo(name = "measure")
    var measure: ArrayList<String> = ArrayList()



    override fun toString(): String {
        return "Drink(idDrink='$idDrink', strDrink='$strDrink', strCategory='$strCategory', strAlcoholic='$strAlcoholic', strGlass='$strGlass', strInstructions=$strInstructions, strDrinkThumb=$strDrinkThumb, strIngredient1=$strIngredient1, strIngredient2=$strIngredient2, strIngredient3=$strIngredient3, strIngredient4=$strIngredient4, strIngredient5=$strIngredient5, strIngredient6=$strIngredient6, strMeasure1=$strMeasure1, strMeasure2=$strMeasure2, strMeasure3=$strMeasure3, strMeasure4=$strMeasure4, strMeasure5=$strMeasure5, strMeasure6=$strMeasure6)"
    }

}
