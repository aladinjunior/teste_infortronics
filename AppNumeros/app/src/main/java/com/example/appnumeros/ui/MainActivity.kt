package com.example.appnumeros.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appnumeros.R
import com.example.appnumeros.controller.MainController
import com.example.appnumeros.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var mainController: MainController
    private lateinit var _binding: ActivityMainBinding

    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        mainController = MainController(this, this)
        setContentView(_binding.root)


        with(binding) {

            add.setOnClickListener {
                val inputtedNumber = inputedNumber.text.toString()
                if (inputtedNumber.isNotBlank() or inputtedNumber.isNotEmpty())
                    mainController.addToList(inputtedNumber)
                else
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.type_valid_number),
                        Toast.LENGTH_SHORT
                    ).show()
            }

            organize.setOnClickListener {
                if (mainController.list.isEmpty())
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.cant_save),
                        Toast.LENGTH_SHORT
                    ).show()
                else
                    binding.tvResult.text = mainController.listToString()

            }

            save.setOnClickListener {
                if (mainController.list.isEmpty()){
                    Toast.makeText(this@MainActivity, getString(R.string.list_is_null), Toast.LENGTH_SHORT).show()
                } else {
                    mainController.createDocument()
                }
            }

        }
    }
}