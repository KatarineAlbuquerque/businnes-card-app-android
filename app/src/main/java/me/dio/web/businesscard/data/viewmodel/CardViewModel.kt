package me.dio.web.businesscard.data.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.dio.web.businesscard.data.database.CardDataBase
import me.dio.web.businesscard.data.repository.CardRepository
import me.dio.web.businesscard.model.Card

class CardViewModel(application: Application): AndroidViewModel(application) {

    val getAllCards: LiveData<List<Card>>
    private val repository: CardRepository
    lateinit var context: Context

    init {
        val cardDao = CardDataBase.getInstance(application).cardDao()
        repository = CardRepository(cardDao)
        getAllCards = repository.getAll()
    }

    fun insertCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(card)
        }
    }

    fun removeCard(id: String) {
        viewModelScope.launch(Dispatchers.IO){
            repository.remove(id)
        }
    }
}

