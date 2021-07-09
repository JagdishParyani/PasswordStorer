package com.example.passwordstorer.room.dao

import androidx.room.*
import com.example.passwordstorer.room.entity.PinEntity

@Dao
interface PinDao {

    @Query("SELECT PIN FROM PinEntity")
    suspend fun getPin(): String


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPin(pin: PinEntity): Long

    @Transaction
    suspend fun updatePin(oldPin: PinEntity, newPin: PinEntity): Long {
        deletePin(oldPin)
        return insertPin(newPin)
    }

    @Delete
    fun deletePin(pin: PinEntity)
}