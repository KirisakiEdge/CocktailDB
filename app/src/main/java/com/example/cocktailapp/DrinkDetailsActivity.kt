package com.example.cocktailapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
import com.example.cocktailapp.networking.NetworkService
import kotlinx.android.synthetic.main.activity_drink_details.*
import kotlinx.android.synthetic.main.activity_drink_details.ingredient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.*

class DrinkDetailsActivity : AppCompatActivity() {

    private lateinit var adapter: IngredientsAdapter
    private val TAG = "DrinkDetails"
    private lateinit var idDrink: String
    private var drink: Drink = Drink()
    private var linearLayoutManager: LinearLayoutManager = LinearLayoutManager(
        this@DrinkDetailsActivity, RecyclerView.VERTICAL, false)
    private lateinit var db: DataBase
    private var dbDrink: DBDrink = DBDrink()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drink_details)
        db = Room.databaseBuilder(applicationContext, DataBase::class.java, "DBDrink").build()
    }


    override fun onStart() {
        super.onStart()
        supportActionBar!!.title = ""
        val arguments = intent.extras
        idDrink = arguments!!["idDrink"].toString()
        //Log.e(TAG, idDrink)
        loadDrink()
    }

    private fun loadDrink(){
        val client = NetworkService()
        val call = client.getService().getDrinkById(idDrink)
        call.clone().enqueue(object : Callback<DrinksList> {
            override fun onFailure(call : Call<DrinksList>, t: Throwable) {
                Log.e(TAG, t.toString())
                Toast.makeText(this@DrinkDetailsActivity, "Get employees failed", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<DrinksList>, response: Response<DrinksList>) {
                response.body()?.let{
                        drinks ->

                    drink = drinks.getDrink()
                    drink.allIngredients()
                    drink.allMeasure()
                    supportActionBar!!.title = drink.strDrink

                    drinkImageDet.load(drink.strDrinkThumb){
                        transformations(RoundedCornersTransformation(25f))
                    }
                    drinkName.text = drink.strDrink
                    drinkAlcocholic.text = drink.strAlcoholic
                    drinkGlass.text = drink.strGlass
                    ingredient.layoutManager = linearLayoutManager
                    adapter = IngredientsAdapter(drink.ingredients, drink.measure)
                    ingredient.adapter = adapter
                    drinkInstruction.text = drink.strInstructions

                    Thread{
                        if (dbDrink.idDrink !=  drink.idDrink){
                            dbDrink.idDrink = drink.idDrink
                            dbDrink.strDrinkThumb = drink.strDrinkThumb
                            dbDrink.strDrink = drink.strDrink
                            dbDrink.strAlcoholic = drink.strAlcoholic
                            dbDrink.strGlass = drink.strGlass
                            dbDrink.ingredients = drink.ingredients
                            dbDrink.measure = drink.measure
                            dbDrink.strInstructions= drink.strInstructions
                            db.dao().saveNewDrink(dbDrink)
                            Log.e("DBDBDBBDBDBD", db.dao().drinks().toString())
                        }
                    }.start()

                }?: kotlin.run {
                    Log.e(TAG, response.toString())
                    Toast.makeText(this@DrinkDetailsActivity, "Get employees failed2", Toast.LENGTH_LONG).show()
                }

            }

        })

    }

}
