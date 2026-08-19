package com.disasterassistant.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emergency_protocols")
data class EmergencyProtocolEntity(
    @PrimaryKey val id: String,
    val category: String,
    val title: String,
    val summary: String,
    val immediateActions: String,
    val warnings: String,
    val escalationLevel: String,
    val verificationStatus: String
)
