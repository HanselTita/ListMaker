package com.softhans.listmaker

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),
    ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListener {

    val listDataManager: ListDataManager = ListDataManager(this)
    //initialize a property to hold the ListDataManager: This creates a new ListDataManager as soon as the Activity is created.

    lateinit var listsRecyclerView: RecyclerView
    //You use the lateinit keyword to tell the compiler that a RecyclerView will be created sometime in the future.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        /**Link the RecyclerView in your class to the one in your layout and give it a LayoutManager and Adapter.*/

        //list of TaskLists from listDataManager, ready for use.
        val lists = listDataManager.readLists()

        //Let the RecyclerView know what kind of Layout to present your items in.
        listsRecyclerView = findViewById<RecyclerView>(R.id.lists_recyclerview)
        lists_recyclerview.layoutManager = LinearLayoutManager(this)

        listsRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists, this)


       fab.setOnClickListener {

           showCreateListDialog()

       }



    }

    private fun showCreateListDialog() {

        //Retrieve the strings defined in strings.xml for use in the Dialog
        val dialogTitle = getString(R.string.name_of_list)
        val positiveButtonTitle = getString(R.string.create_list)


        //Create an AlertDialog.Builder to help construct the Dialog.
        val builder = AlertDialog.Builder(this)

        //Create EditText to serve as the input field for the user to enter the name of the list.
        val listTitleEditText = EditText(this)

        //Specify the input type to give Android a hint as to what the most appropriate keyboard to show is
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        //Set title of the Dialog by calling setTitle.
        builder.setTitle(dialogTitle)

        //set the content View of the Dialog
        builder.setView(listTitleEditText)

        //Add a positive button to the Dialog; telling the Dialog a positive action has occurred and something should happen.
        builder.setPositiveButton(positiveButtonTitle) { dialog, _ ->

            //add create a list and save it to the ListDataManager:
            val list = TaskList(listTitleEditText.text.toString())
            listDataManager.saveList(list)

            val recyclerAdapter = listsRecyclerView.adapter as ListSelectionRecyclerViewAdapter
                recyclerAdapter.addList(list)

            dialog.dismiss()
            showListDetail(list)
        }
        //Instruct the Dialog Builder to create the Dialog and display it on the screen.
            builder.create().show()

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }

    }
    private fun showListDetail(list: TaskList) {

        val listDetailIntent = Intent(this, ListDetailActivity::class.java)
        listDetailIntent.putExtra(INTENT_LIST_KEY, list)
        startActivity(listDetailIntent)
    }

    override fun listItemClicked(list: TaskList) {
        showListDetail(list)
    }
    companion object { const val INTENT_LIST_KEY = "list" }


}


