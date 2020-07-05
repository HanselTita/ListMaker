package com.softhans.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter : RecyclerView.Adapter<ListSelectionViewHolder>() {

    //Array of strings to use as the list titles.
    val listTitles = arrayListOf("Shopping", "Chore", "Android Tuitorials")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {

        /** Programmatically create a layout using the parent context.
         * Inflate the Layout you want by passing in the layout name and the parent ViewGroup so the
        View has a parent it can refer to.
         * The Boolean value is used to specify whether the View should be attached to the parent.
         * Always use false for RecyclerView layouts as the RecyclerView attaches and detaches the Views for you.*/

        val view = LayoutInflater.from(parent.context)
                           .inflate(R.layout.list_selection_view_holder, parent, false)

        // return the viewHolder that was created passing in the view
        return ListSelectionViewHolder(view)
    }


     //how many items the RecyclerView has.
    override fun getItemCount(): Int {

         //size of the array should match the size of the RecyclerView
         return listTitles.size
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {

        //set a value for each of the TextViews on the ViewHolder
        holder.listPosition.text = (position + 1).toString()
        holder.listtitle.text = listTitles[position]

        /**For each call of onBindViewHolder(), you take the TextViews you created in the ViewHolder
         *  and populate them with their position in the list and
         *  the name of the list from the listTitles array.*/
    }

}
