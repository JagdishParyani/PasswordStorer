package com.example.passwordstorer.room.repository

import com.example.passwordstorer.room.entity.PinEntity

interface AppDataBaseRepository {

    suspend fun insertPin(pin: PinEntity): Long

    suspend fun getPinFromDb(): String
}