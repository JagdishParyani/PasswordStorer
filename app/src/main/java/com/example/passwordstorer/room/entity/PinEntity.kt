package com.example.passwordstorer.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PinEntity")
data class PinEntity(
    @PrimaryKey val pin: String
)