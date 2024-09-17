package com.example.shopdata

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShopAdapter(val shopData: ArrayList<ShopDetails>) :
    RecyclerView.Adapter<ShopAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val shopImage = itemView.findViewById<ImageView>(R.id.shopImage_ImageView)
        val shopName = itemView.findViewById<TextView>(R.id.shopName_TextView)
        val shopDistance: TextView = itemView.findViewById<TextView>(R.id.shopDistance_TextView)
        val shopOwner: TextView = itemView.findViewById(R.id.shopOwnerName_TextView)
        val shopLocation: TextView = itemView.findViewById(R.id.shopAddress_TextView)
        val shopRating: TextView = itemView.findViewById(R.id.shopRating_TextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_shop_item_cell, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return shopData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.shopImage.setImageResource(shopData[position].shopImage)
        holder.shopName.text = shopData[position].shopName
        holder.shopDistance.text = shopData[position].shopDistance
        holder.shopOwner.text = shopData[position].shopOwnerName
        holder.shopLocation.text = shopData[position].shopAddress
        holder.shopRating.text = shopData[position].shopRating

    }
}