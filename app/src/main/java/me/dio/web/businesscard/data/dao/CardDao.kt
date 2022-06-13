package me.dio.web.businesscard.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import me.dio.web.businesscard.model.Card

@Dao
interface CardDao {

    @Query("SELECT * FROM Card ORDER BY id DESC")
    fun getAll(): LiveData<List<Card>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(card: Card)

    @Query("DELETE FROM Card WHERE id = :id")
    suspend fun remove(id: String)

}