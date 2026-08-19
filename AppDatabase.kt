package com.disasterassistant.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EmergencyProtocolEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emergencyProtocolDao(): EmergencyProtocolDao
}
