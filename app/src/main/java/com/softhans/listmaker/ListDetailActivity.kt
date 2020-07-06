package com.softhans.listmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ListDetailActivity : AppCompatActivity() {

    lateinit var list: TaskList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_detail)

        //Use key assigned to list in MainActivity to reference the list in the Intent and assign it to the list variable.
        list = intent.getParcelableExtra(MainActivity.INTENT_LIST_KEY) as TaskList

        // Assign the title of the Activity to the name of the list to let the user know what list they’re viewing.
        title = list.name
    }
}