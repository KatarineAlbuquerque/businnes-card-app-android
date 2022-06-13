package me.dio.web.businesscard.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val company: String,
    val name:String,
    val email: String,
    val phone: String
        )