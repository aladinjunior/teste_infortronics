package com.example.numeroperfeito

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.numeroperfeito.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        with(binding) {

            verifyButton.setOnClickListener {

                //check if the user inputted any data
                if (!inputtedNumber.text?.isNotBlank()!! && !inputtedNumber.text?.isNotEmpty()!!){
                    Toast.makeText(this@MainActivity, getString(R.string.field_cant_be_null), Toast.LENGTH_SHORT).show()
                } else{
                    val number = inputtedNumber.text.toString().toInt()
                    val sumOfNumbers = calculatePerfectNum(number)

                    //check if the sum of the divisors is equal to the number
                    if (sumOfNumbers == number) {
                        button.text = getString(R.string.is_perfect)
                        button.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.green))
                    } else {
                        button.text = getString(R.string.is_not_perfect)
                        button.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.red))
                    }
                }


            }
        }
    }

    //calculate the sum of the divisors of a number
    private fun calculatePerfectNum(num: Int): Int {
        var sum = 0

        //loop to verify if "i" is a divisor of the number
        for (i in 1 until num) {
            if (num % i == 0) {
                sum += i
            }
        }

        return sum
    }
}