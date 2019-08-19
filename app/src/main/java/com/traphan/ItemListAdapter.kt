package com.traphan

import android.app.Activity
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

open class ItemListAdapter(activity: Activity, count: Int): RecyclerView.Adapter<ItemListAdapter.ItemHolder>() {

    private val activity = activity
    private val size = count

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item, parent, false)
        return ItemHolder(view)
    }

    override fun getItemCount(): Int {
        return size
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(position)
    }


    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            val displayMetrics = DisplayMetrics()
            activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
            val width = displayMetrics.widthPixels
            itemView.layoutParams = RecyclerView.LayoutParams((width * 0.85f).toInt(), RecyclerView.LayoutParams.WRAP_CONTENT)
        }

        internal fun bind(position: Int) {
            var imageView: ImageView = itemView.findViewById(R.id.image)
            imageView.setImageResource(R.drawable.ic_launcher_background)
        }
    }
}