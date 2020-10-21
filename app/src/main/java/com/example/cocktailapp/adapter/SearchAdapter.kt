package com.example.cocktailapp.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.cocktailapp.DrinkDetailsActivity
import com.example.cocktailapp.R
import com.example.cocktailapp.databinding.ItemDrinkBinding
import com.example.cocktailapp.model.Drink
import com.example.cocktailapp.model.DrinksList


class SearchAdapter() : RecyclerView.Adapter<SearchAdapter. SearchViewHolder>(){

    private var drinks = ArrayList<Drink>()
    private val TAG = "MAAAAAAAAAAAAAX"
    private lateinit var idDrinkForDetails: String


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  SearchViewHolder {
        val binding: ItemDrinkBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_drink, parent, false)
        return SearchViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int {
        return drinks.size
    }


    override fun onBindViewHolder(viewHolder:  SearchViewHolder, position: Int) {
        viewHolder.bind(drinks[position])

    }

    fun updateData(drink: ArrayList<Drink>) {
        this.drinks.clear()
        this.drinks.addAll(drink)
        notifyDataSetChanged()
    }

    inner class SearchViewHolder(private val binding: ItemDrinkBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(drink: Drink) {
            binding.drinkName.text = drink.strDrink
            binding.drinkImage.load(drink.strDrinkThumb) {
                transformations(RoundedCornersTransformation(25f))
                scale(Scale.FILL)
            }
            itemView.setOnClickListener {
                val intent = Intent(context, DrinkDetailsActivity::class.java)
                idDrinkForDetails = drink.idDrink
                //Log.e(TAG, idDrinkForDetails)

                intent.putExtra("idDrink", idDrinkForDetails)
                context.startActivity(intent)

            }
        }
    }
}

