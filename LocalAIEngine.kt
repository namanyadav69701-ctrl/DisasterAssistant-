package com.disasterassistant.app.ai

import com.disasterassistant.app.domain.model.AIResponse
import com.disasterassistant.app.domain.model.EmergencyContext

interface LocalAIEngine {
    suspend fun interpret(userMessage: String, context: EmergencyContext): AIResponse
}
