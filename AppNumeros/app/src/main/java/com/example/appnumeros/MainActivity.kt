package com.example.appnumeros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.appnumeros.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding

    private val binding get() = _binding

    private val list: MutableList<String> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)


        with(binding){
            add.setOnClickListener {
                val inputtedNumber = inputedNumber.text.toString()
                list.add(inputtedNumber)
                Toast.makeText(this@MainActivity, getString(R.string.added_successfully), Toast.LENGTH_SHORT).show()
            }

            organize.setOnClickListener {
                val sortedList = list.sorted()
                binding.tvResult.text = sortedList.toString()
            }
        }

    }
}