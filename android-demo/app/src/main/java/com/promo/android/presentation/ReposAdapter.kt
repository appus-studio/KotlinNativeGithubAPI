package com.promo.android.presentation

import android.content.Context

import android.view.LayoutInflater
import android.view.View
import com.promo.android.R
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import domain.model.Repo

/**
 * Created by volodymyr.zaika on 2019-05-24 18:39. android
 */

class ReposAdapter(val items: List<Repo>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvId?.text = items[position].id.toString()
        holder?.tvName?.text = items[position].name
        holder?.tvDescription?.text = items[position].description
        holder?.tvUrl?.text = items[position].url
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvId = view.findViewById<TextView>(R.id.tvId)
    val tvName = view.findViewById<TextView>(R.id.tvName)
    val tvDescription = view.findViewById<TextView>(R.id.tvDescription)
    val tvUrl = view.findViewById<TextView>(R.id.tvUrl)
}