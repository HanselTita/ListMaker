package com.softhans.listmaker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_list_detail.*

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList
    lateinit var listItemsRecyclerView : RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        //Use key assigned to list in MainActivity to reference the list in the Intent and assign it to the list variable.
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY) as TaskList

        // Assign the title of the Activity to the name of the list to let the user know what list they’re viewing.
        title = list.name


        // Assign the RecyclerView an Adapter, and pass in the list
        listItemsRecyclerView = findViewById(R.id.list_items_recyclerview)
        listItemsRecyclerView.adapter = ListItemsRecyclerViewAdapter(list)

        // Assign the RecyclerView a Layout Manager that uses a LinearLayoutManager to handle the presentation.
        list_items_recyclerview.layoutManager = LinearLayoutManager(this)


        add_task_button.setOnClickListener {
            showCreateTaskDialog()
        }
    }

    private fun showCreateTaskDialog() {

        //Create an EditText so you can receive text input from the user.
        val taskEditText = EditText(this)
        taskEditText.inputType = InputType.TYPE_CLASS_TEXT

        //Create an AlertDialogBuilder and use method chaining to set up various aspects of the AlertDialog.
        AlertDialog.Builder(this) .setTitle(R.string.task_to_add).setView(taskEditText)
        .setPositiveButton(R.string.add_task) { dialog, _ ->

         // In Positive Button’s click listener, access the EditText to grab the text input and create a task from the input.
         val task = taskEditText.text.toString()
            list.tasks.add(task)

         // Notify the ListItemsRecyclerViewAdapter that a new item was added.
          val recyclerAdapter = listItemsRecyclerView.adapter as ListItemsRecyclerViewAdapter
          recyclerAdapter.notifyItemInserted(list.tasks.size-1)

          //Once the RecyclerAdapter updates, dismiss dialog.
          dialog.dismiss()
        }

           //Create and show the AlertDialog
          .create()
          .show()
    }

    override fun onBackPressed() {
        val bundle = Bundle()
        bundle.putParcelable(MainActivity.INTENT_LIST_KEY, list)
        val intent = Intent()
        intent.putExtras(bundle)
        setResult(Activity.RESULT_OK, intent)
        super.onBackPressed()
    }
}