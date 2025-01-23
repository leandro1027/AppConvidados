package com.example.appconvidados.viewmodel

import android.app.Application
import androidx.collection.emptyLongSet
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appconvidados.model.GuestModel
import com.example.appconvidados.model.SucessFailure
import com.example.appconvidados.repository.GuestRepository
import kotlin.math.truncate

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    private  val guestModel = MutableLiveData<GuestModel>()
    val guests: LiveData<GuestModel> = guestModel

    private val _saveGuest  = MutableLiveData<SucessFailure>()
    val saveGuest: LiveData<SucessFailure> = _saveGuest

    fun save(guest: GuestModel) {
        val sucessFailure = SucessFailure(true, "")
        if (guest.id == 0) {
            sucessFailure.sucess = repository.insert(guest)
        } else {
            sucessFailure.sucess = repository.update(guest)
        }

    }

    fun get(id: Int){
        guestModel.value = repository.get(id)
    }
}