package com.example.passwordstorer.room.repository

import com.example.passwordstorer.room.dao.PinDao
import com.example.passwordstorer.room.entity.PinEntity
import javax.inject.Inject

class AppDatabaseRepositoryImpl @Inject constructor(private val pinDao: PinDao) :
    AppDataBaseRepository {

    override suspend fun insertPin(pin: PinEntity): Long {
        return pinDao.insertPin(pin)
    }

    override suspend fun getPinFromDb(): String {
        return pinDao.getPin()
    }

}