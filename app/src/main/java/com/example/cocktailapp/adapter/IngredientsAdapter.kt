package com.example.cocktailapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.R
import kotlinx.android.synthetic.main.item_ingredients.view.*

class IngredientsAdapter(var ingredients: ArrayList<String>, var measures: ArrayList<String>) : RecyclerView.Adapter<IngredientsAdapter.IngredientsHolder>() {

    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = IngredientsHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_ingredients, parent, false)
    )


    override fun getItemCount(): Int {
        return ingredients.size
    }


    override fun onBindViewHolder(holder: IngredientsHolder, position: Int) {
        holder.bindIn(ingredients[position])
        if (measures[position]!= null)
            holder.bindMe(measures[position])

    }

    inner class IngredientsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindIn(itemIngredient: String) {
            ++count
            itemView.ingredient.text = "$count.$itemIngredient"
        }
        fun bindMe(itemMeasure: String) {
            if (itemMeasure != "null") {
                itemView.measure.text = itemMeasure
            }
        }
    }
}