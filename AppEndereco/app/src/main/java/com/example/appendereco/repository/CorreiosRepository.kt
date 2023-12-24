package com.example.appendereco.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appendereco.api.CorreiosAPI
import com.example.appendereco.model.Address

class CorreiosRepository(private val correiosAPI: CorreiosAPI) {

    private val addressLiveData = MutableLiveData<Address>()

    val address: LiveData<Address>
        get() = addressLiveData

    private val messageLiveData = MutableLiveData<String>()


    val message: LiveData<String>
        get() = messageLiveData

    suspend fun getAddress(cep: String) {
        try {
            val result = correiosAPI.getAddress(cep)

            if (result.isSuccessful) {
                val responseBody = result.body()

                if (responseBody != null && !responseBody.erro) {
                    responseBody.let {
                        addressLiveData.postValue(it)
                    }
                } else {
                    messageLiveData.postValue("CEP Inválido")
                }
            } else {

                messageLiveData.postValue("CEP Inválido")
            }
        } catch (e: Exception) {
            messageLiveData.postValue("Erro ao conectar ao serviço")
        }

    }
}