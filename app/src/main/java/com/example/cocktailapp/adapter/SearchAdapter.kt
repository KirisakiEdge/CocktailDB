package com.example.cocktailapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.cocktailapp.R
import com.example.cocktailapp.databinding.ItemDrinkBinding
import com.example.cocktailapp.model.Drink


class SearchAdapter(private val onDrinkListener: OnDrinkListener): RecyclerView.Adapter<SearchAdapter. SearchViewHolder>(){

    private var drinks = ArrayList<Drink>()
    private val TAG = "MAAAAAAAAAAAAAX"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):  SearchViewHolder {
        val binding: ItemDrinkBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.item_drink, parent, false)
        return SearchViewHolder(binding, onDrinkListener)
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

    inner class SearchViewHolder(private val binding: ItemDrinkBinding, private val onDrinkListener: OnDrinkListener) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {


        fun bind(drink: Drink) {
            binding.drinkName.text = drink.strDrink
            binding.drinkImage.load(drink.strDrinkThumb){
                transformations(RoundedCornersTransformation(25f))
                scale(Scale.FILL)
            }
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            onDrinkListener.onDrinkClick(adapterPosition)

        }


    }

    interface OnDrinkListener{
        fun onDrinkClick(position: Int)
    }
}

