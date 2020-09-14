package com.example.cocktailapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.example.cocktailapp.DB.DataBase
import com.example.cocktailapp.adapter.MainAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainAdapter.OnDrinkListener {

    private lateinit var gridLayoutManager: GridLayoutManager
    private var adapter: MainAdapter? = null
    private val TAG = "MainActivity"
    private lateinit var db: DataBase
    private lateinit var idDrinkForDetails: String
    private val columns = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        db = Room.databaseBuilder(applicationContext, DataBase::class.java, "DBDrink").build()
        setupEmployeeList()

        fab.setOnClickListener {
            val mainIntent = Intent(this@MainActivity, SearchActivity::class.java)
            this@MainActivity.startActivity(mainIntent)
            //this@MainActivity.finish()
        }

        deleteDrinks.setOnClickListener {
            Thread{  db.dao().deleteAllDrinks()
                val intent = intent
                startActivity(intent)
                finish()
            }.start()
        }

    }

    private fun setupEmployeeList() {

        gridLayoutManager = GridLayoutManager(this, columns)
        drinks_recycle_view.layoutManager = gridLayoutManager
        adapter = MainAdapter(this)
        drinks_recycle_view.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        loadEmployees()
    }

    private fun loadEmployees(){
        Thread {
            val tempDrink= db.dao().drinks()
            //Log.e(TAG, db.dao().drinks().toString())
                if (db.dao().drinks().isNotEmpty()) {
                    runOnUiThread {
                        deleteDrinks.visibility = View.VISIBLE
                        empty.text = null
                        adapter?.updateData(tempDrink)
                    }
                }
        }.start()
    }


    override fun onDrinkClick(position:Int, idDrink: ArrayList<String>) {

        val intent = Intent(this@MainActivity, DrinkDetailsForDbActivity::class.java)
        idDrinkForDetails = idDrink[position]
       // Log.e(TAG, idDrinkForDetails)

        intent.putExtra("idDrink", idDrinkForDetails)
        this@MainActivity.startActivity(intent)
    }
}
