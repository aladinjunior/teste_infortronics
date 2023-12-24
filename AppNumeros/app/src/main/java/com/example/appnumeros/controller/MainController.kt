package com.example.appnumeros.controller

import android.content.Context
import android.widget.Toast
import com.example.appnumeros.R

class MainController(private val context: Context) {


    val list: MutableList<String> = mutableListOf()

    fun addToList(number: String) {
        list.add(number)
        Toast.makeText(context, context.getString(R.string.added_successfully), Toast.LENGTH_SHORT)
            .show()

    }

    private fun sortList() = list.sortedWith(compareBy { it.toDoubleOrNull() ?: 0.0 })

    fun listToString() = sortList().joinToString(" - ")

}