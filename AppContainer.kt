package com.disasterassistant.app

import android.content.Context
import androidx.room.Room
import com.disasterassistant.app.ai.LocalAIEngine
import com.disasterassistant.app.ai.RuleBasedLocalAIEngine
import com.disasterassistant.app.data.local.AppDatabase
import com.disasterassistant.app.data.repository.RoomEmergencyRepository
import com.disasterassistant.app.domain.repository.EmergencyRepository
import com.disasterassistant.app.domain.usecase.InterpretEmergencyUseCase

class AppContainer(context: Context) {
    private val database = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "disaster_assistant.db"
    ).build()

    val aiEngine: LocalAIEngine = RuleBasedLocalAIEngine()
    val emergencyRepository: EmergencyRepository = RoomEmergencyRepository(database.emergencyProtocolDao())
    val interpretEmergency = InterpretEmergencyUseCase(aiEngine, emergencyRepository)
}
