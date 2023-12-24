package com.example.appnumeros.controller

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.appnumeros.R

class MainController(private val context: Context, appCompatActivity: AppCompatActivity) {


    val list: MutableList<String> = mutableListOf()

    fun addToList(number: String) {
        list.add(number)
        Toast.makeText(context, context.getString(R.string.added_successfully), Toast.LENGTH_SHORT)
            .show()

    }

    private fun sortList() = list.sortedWith(compareBy { it.toDoubleOrNull() ?: 0.0 })

    fun listToString() = sortList().joinToString(" - ")

    fun createDocument() {
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, "fileName.txt")

        }

        createDocumentLauncher.launch(intent)
    }

    private val createDocumentLauncher = appCompatActivity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val uri = result.data?.data
            try {
                val outputStream = context.contentResolver.openOutputStream(uri!!)

                outputStream?.write(listToString().toByteArray())
                list.clear()
                outputStream?.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } else {
            Toast.makeText(context, context.getString(R.string.failed_to_save), Toast.LENGTH_SHORT).show()
        }


    }
}