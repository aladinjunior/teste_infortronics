package com.example.appendereco.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appendereco.model.Address
import com.example.appendereco.repository.CorreiosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val correiosRepository: CorreiosRepository) : ViewModel() {


    fun getAddress(cep: String) = viewModelScope.launch(Dispatchers.IO) {
        correiosRepository.getAddress(cep)
    }


    val address: LiveData<Address>
        get() = correiosRepository.address

    val message: LiveData<String>
        get() = correiosRepository.message
}