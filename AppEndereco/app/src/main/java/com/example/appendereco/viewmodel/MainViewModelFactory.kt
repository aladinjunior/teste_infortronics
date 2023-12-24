package com.example.appendereco.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.appendereco.repository.CorreiosRepository

class MainViewModelFactory(private val correiosRepository: CorreiosRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(correiosRepository) as T
    }
}