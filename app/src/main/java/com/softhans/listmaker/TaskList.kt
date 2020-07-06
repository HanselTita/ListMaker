package com.softhans.listmaker

import android.os.Parcel
import android.os.Parcelable

class TaskList constructor(val name: String, val tasks:
ArrayList<String> = ArrayList()) : Parcelable {

    //Reading from a Parcel
    constructor(source: Parcel) : this( source.readString()!!,
        source.createStringArrayList()!! )
    override fun describeContents() = 0

    //Writing to a Parcel:
    override fun writeToParcel(dest: Parcel, flags: Int) { dest.writeString(name)
        dest.writeStringList(tasks) }

    //Fulfilling static interface requirements:
    companion object CREATOR: Parcelable.Creator<TaskList> {

        // Calling your constructor:
        override fun createFromParcel(source: Parcel): TaskList =
            TaskList(source)
        override fun newArray(size: Int): Array<TaskList?> =
            arrayOfNulls(size)
    }
}