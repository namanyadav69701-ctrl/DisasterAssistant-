package com.disasterassistant.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EmergencyProtocolDao {
    @Query("SELECT * FROM emergency_protocols WHERE id = :id LIMIT 1")
    suspend fun getById(id: String): EmergencyProtocolEntity?

    @Query("SELECT COUNT(*) FROM emergency_protocols")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<EmergencyProtocolEntity>)
}
