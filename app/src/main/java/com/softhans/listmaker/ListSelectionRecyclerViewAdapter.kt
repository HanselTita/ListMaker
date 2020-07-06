package com.softhans.listmaker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter(val lists: ArrayList<TaskList>, val clickListener:
                                                ListSelectionRecyclerViewClickListener) :
                                        RecyclerView.Adapter<ListSelectionViewHolder>() {

    interface ListSelectionRecyclerViewClickListener {
        fun listItemClicked(list: TaskList)
    }

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
         return lists.size
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {

        //set a value for each of the TextViews on the ViewHolder
        holder.listPosition.text = (position + 1).toString()
        holder.listTitle.text = lists.get(position).name

        //onClickListener to the View of itemHolder:
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position]) }

    }

    fun addList(list: TaskList) {
        // Update the ArrayList with the new TaskList.
        lists.add(list)

        // Call notifyItemInserted() to inform the Adapter that you updated the data source, and you update the RecyclerView.
        notifyItemInserted(lists.size-1) }
}
