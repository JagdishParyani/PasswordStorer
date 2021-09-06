package com.example.passwordstorer.room.repository.pin

import com.example.passwordstorer.room.entity.PinEntity

interface PinDBRepo {

    suspend fun insertPin(pin: PinEntity): Long

    suspend fun getPinFromDb(): String

    suspend fun updatePin(oldPin: PinEntity, newPin: PinEntity): Long
}