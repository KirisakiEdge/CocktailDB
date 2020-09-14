package com.example.cocktailapp

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cocktailapp.adapter.SearchAdapter
import com.example.cocktailapp.model.DrinksList
import com.example.cocktailapp.networking.NetworkService
import kotlinx.android.synthetic.main.activity_seacrh.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class SearchActivity : AppCompatActivity(), SearchAdapter.OnDrinkListener {

    private lateinit var gridLayoutManager: GridLayoutManager
    private var adapter: SearchAdapter? = null
    private lateinit var drinksList: DrinksList
    private lateinit var idDrinkForDetails: String
    val columns = 2
   // private var searchView: androidx.appcompat.widget.SearchView = androidx.appcompat.widget.SearchView(this)

    private val client = NetworkService()

    private val TAG = "SearchActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seacrh)

        setupDrinksList()
    }

    private fun setupDrinksList() {
        gridLayoutManager = GridLayoutManager(this, columns)
        drinksFound_recycle_view.layoutManager = gridLayoutManager

        adapter = SearchAdapter(this)
        drinksFound_recycle_view.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater

        inflater.inflate(R.menu.search_menu, menu)
        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search_icon)
        val searchView = searchItem?.actionView as androidx.appcompat.widget.SearchView
        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                //Toast.makeText(this@SearchActivity, "Looking for $query", Toast.LENGTH_LONG).show()

                val call = client.getService().getDrinksByName(query.toString())
                Log.e(TAG, query.toString())
                call.clone().enqueue(object : Callback<DrinksList> {
                    override fun onFailure(call : Call<DrinksList>, t: Throwable) {
                        Log.e(TAG, t.toString())
                        Toast.makeText(this@SearchActivity, "No network connection", Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<DrinksList>, response: Response<DrinksList>) {
                        response.body()?.let{ drinks ->
                            drinksList = drinks
                            try {
                                adapter?.updateData(drinks.allDrinks!!)
                                enterText.text = null
                            }catch (e:Exception){
                                Toast.makeText(this@SearchActivity, "Invalid Name", Toast.LENGTH_LONG).show()
                            }

                        }?: kotlin.run {Log.e(TAG, response.toString())
                            Toast.makeText(this@SearchActivity, "No network connection", Toast.LENGTH_LONG).show()
                        }

                    }

                })
                //Toast.makeText(this@SearchActivity, "Looking for $newText", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
        return true
    }

    override fun onDrinkClick(position: Int) {
        val intent = Intent(this@SearchActivity, DrinkDetailsActivity::class.java)
        idDrinkForDetails = drinksList.getIdByPosition(position)
        //Log.e(TAG, idDrinkForDetails)

        intent.putExtra("idDrink", idDrinkForDetails)
        this@SearchActivity.startActivity(intent)

    }

/*    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@SearchActivity, MainActivity::class.java)
        this@SearchActivity.startActivity(intent)
        this@SearchActivity.finish()
    }*/


}
