package com.softhans.listmaker

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class ListDataManager(private val context: Context) {

    fun saveList(list: TaskList){


        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
          /** Reference to the app’s default SharedPreference store via PreferenceManager.getDefaultSharedPreferences(context).
         With the PreferenceManager object it returns, append .edit()to it to get a SharedPreferences.Editor instance.
         This allows you to write key-value pairs to SharedPreferences.*/


        sharedPreferences.putStringSet(list.name, list.tasks.toHashSet())
        /**Write TaskList to SharedPreferences as a set of Strings, passing in the name of the list as the key.
        Since the tasks in TaskList is an array of strings, you can’t store it directly in a string, so you convert
        the tasks in TaskList to a HashSet which SharedPreferences can use as a value to save. Since HashSet is a Set,
        it ensures unique values in the list.*/

        //Instruct the SharedPreferences Editor instance to apply the changes. This writes the changes to Listmaker’s SharedPreferences file.
        sharedPreferences.apply()
    }

    fun readLists(): ArrayList<TaskList> {

        // Reference the default SharedPreferences file. Don’t request SharedPreferences.Editor since you only need to read from SharedPreferences, not write to it.
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

        // Call sharedPreferences.all to get the contents of the SharedPreferences file as a Map.
        val sharedPreferenceContents = sharedPreferences.all

        // Create an empty ArrayList of type TaskList. You’ll use this to store the lists you retrieve from SharedPreferences.
        val taskLists = ArrayList<TaskList>()

        // Iterate over the items in the Map you received from SharedPreferences using a for loop.
        for (taskList in sharedPreferenceContents) {
            val itemsHashSet = ArrayList(taskList.value as HashSet<String>)
            val list = TaskList(taskList.key, itemsHashSet)

        // Add the newly reconstructed TaskList into the empty ArrayList you created earlier.
        taskLists.add(list)

        }
        //return the contents of taskLists to the caller of the method.
        return taskLists
    }
}