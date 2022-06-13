package me.dio.web.businesscard.data.repository

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import me.dio.web.businesscard.data.dao.CardDao
import me.dio.web.businesscard.model.Card

class CardRepository(private val cardDao: CardDao) : ViewModel() {

    fun getAll() = cardDao.getAll()

    fun insert(card: Card) = runBlocking {
        launch(Dispatchers.IO) {
            cardDao.insert(card)
        }
    }

    fun remove(id: String) = runBlocking {
        launch(Dispatchers.IO) {
            cardDao.remove(id)
        }
    }
}