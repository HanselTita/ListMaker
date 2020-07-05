package com.softhans.listmaker

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_selection_view_holder.view.*

class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    //References to the TextViews
    val listPosition = itemView.itemNumber as TextView
    val listtitle = itemView.itemString as TextView
}