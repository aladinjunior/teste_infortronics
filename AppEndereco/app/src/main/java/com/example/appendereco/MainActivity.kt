package com.example.appendereco

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.appendereco.databinding.ActivityMainBinding
import com.example.appendereco.repository.CorreiosRepository
import com.example.appendereco.api.CorreiosAPI
import com.example.appendereco.viewmodel.MainViewModel
import com.example.appendereco.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        val correiosAPI = CorreiosAPI.getInstance()

        val correiosRepository = CorreiosRepository(correiosAPI)


        viewModel = ViewModelProvider(this, MainViewModelFactory(correiosRepository))[MainViewModel::class.java]

        viewModel.address.observe(this) { address ->
            with(binding){
                city.setTextColor(ContextCompat.getColor(this@MainActivity, android.R.color.white))
                city.text = address.localidade
                street.text = address.logradouro
                neighborhood.text = address.bairro
            }
        }

        viewModel.message.observe(this) { message ->
            binding.city.setTextColor(ContextCompat.getColor(this@MainActivity, android.R.color.holo_red_light))
            binding.city.text = message
            binding.street.text = ""
            binding.neighborhood.text = ""
        }




        binding.button.setOnClickListener {
            viewModel.getAddress(binding.editCep.text.toString())
        }

    }
}