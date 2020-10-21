package com.example.cocktailapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.example.cocktailapp.DB.DataBase
import com.example.cocktailapp.adapter.IngredientsAdapter
import com.example.cocktailapp.model.DBDrink
import com.example.cocktailapp.model.Drink
import com.example.cocktailapp.model.DrinksList
import kotlinx.android.synthetic.main.activity_drink_details.*


class DrinkDetailsForDbActivity : AppCompatActivity() {

    private lateinit var adapter: IngredientsAdapter
    private val TAG = "DrinkDetails"
    private lateinit var idDrink: String
    private var drink: Drink = Drink()
    private var dbDrink: DBDrink = DBDrink()
    private var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(
        this@DrinkDetailsForDbActivity, RecyclerView.VERTICAL, false)
    private lateinit var db: DataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_details_for_db)
    }

    override fun onStart() {
        super.onStart()
        supportActionBar!!.title = ""
        val arguments = intent.extras
        idDrink = arguments!!["idDrink"].toString()
        db = Room.databaseBuilder(applicationContext, DataBase::class.java, "DBDrink").build()

        loadDrink()
    }

    private fun loadDrink() {
        Thread {
            dbDrink = db.dao().loadDrinkById(idDrink)
            Log.e(TAG, dbDrink.idDrink)
            supportActionBar!!.title = dbDrink.strDrink
            Log.e("SHHHHIIIIIT", drink.measure.toString())

            drinkImageDet.load(dbDrink.strDrinkThumb) {
            transformations(RoundedCornersTransformation(25f))
            }
            drinkName.text = dbDrink.strDrink
            drinkAlcocholic.text = dbDrink.strAlcoholic
            drinkGlass.text = dbDrink.strGlass
            ingredient.layoutManager = linearLayoutManager
            adapter = IngredientsAdapter(dbDrink.ingredients, dbDrink.measure)
            ingredient.adapter = adapter
            drinkInstruction.text = dbDrink.strInstructions
        }.start()
    }

/*    override fun onPause() {
        super.onPause()
        val mainIntent = Intent(this@DrinkDetailsForDbActivity, MainActivity::class.java)
        this@DrinkDetailsForDbActivity.startActivity(mainIntent)
        this@DrinkDetailsForDbActivity.finish()
    }*/
}
