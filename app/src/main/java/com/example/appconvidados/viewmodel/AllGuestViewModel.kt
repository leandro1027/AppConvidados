package com.example.appconvidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appconvidados.model.GuestModel
import com.example.appconvidados.repository.GuestRepository

class AllGuestViewModel(application: Application) : AndroidViewModel(application) {

    // private lateinit var repository: GuestRepository
    private val repository = GuestRepository.getInstance(application.applicationContext)

    private  val listAllGuests = MutableLiveData<List<GuestModel>>()
    val guests: LiveData<List<GuestModel>> = listAllGuests

    fun getAll(){
        listAllGuests.value = repository.getAll()
    }
}