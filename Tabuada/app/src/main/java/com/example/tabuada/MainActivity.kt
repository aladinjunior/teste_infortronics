package com.example.tabuada

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tabuada.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        with(binding){

            button.setOnClickListener {
                if(inputtedNumber.text.toString().isBlank() or inputtedNumber.text.toString().isEmpty()){
                    Toast.makeText(this@MainActivity, getString(R.string.field_cant_be_null), Toast.LENGTH_SHORT).show()
                } else {
                    val num = inputtedNumber.text.toString().toInt()
                    val tableResult = calculateTable(num)
                    result.text = tableResult
                }
            }
        }
    }


    //getting the multiplication table and converting to string
    private fun calculateTable(num: Int): String {
        val builder = StringBuilder()

        for (i in 1..10) {
            val result = num * i
            builder.append("$num x $i = $result\n")
        }

        return builder.toString()
    }
}