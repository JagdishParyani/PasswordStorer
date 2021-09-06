package com.example.passwordstorer.room.repository.pin

import com.example.passwordstorer.room.dao.PinDao
import com.example.passwordstorer.room.entity.PinEntity
import javax.inject.Inject

class PinDBRepoImpl @Inject constructor(private val pinDao: PinDao)  : PinDBRepo {

    override suspend fun insertPin(pin: PinEntity): Long {
        return pinDao.insertPin(pin)
    }

    override suspend fun getPinFromDb(): String {
        return pinDao.getPin()
    }

    override suspend fun updatePin(oldPin: PinEntity, newPin: PinEntity): Long {
        return pinDao.updatePin(oldPin, newPin)
    }
}