package me.dio.web.businesscard.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import me.dio.web.businesscard.data.dao.CardDao
import me.dio.web.businesscard.model.Card

@Database(entities = [Card::class], version = 1)
abstract class CardDataBase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object {

        @Volatile
        private var INSTANCE: CardDataBase? = null

        fun getInstance(context: Context): CardDataBase {
            return INSTANCE ?: synchronized(CardDataBase::class) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardDataBase::class.java,
                    "usersDb"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
