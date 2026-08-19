package com.disasterassistant.app.domain.usecase

import com.disasterassistant.app.ai.LocalAIEngine
import com.disasterassistant.app.domain.model.EmergencyContext
import com.disasterassistant.app.domain.model.EmergencyProtocol
import com.disasterassistant.app.domain.repository.EmergencyRepository

data class EmergencyResult(
    val interpretation: String,
    val confidence: Float,
    val protocol: EmergencyProtocol?
)

class InterpretEmergencyUseCase(
    private val aiEngine: LocalAIEngine,
    private val repository: EmergencyRepository
) {
    suspend operator fun invoke(message: String, context: EmergencyContext): EmergencyResult {
        repository.initializeIfNeeded()
        val ai = aiEngine.interpret(message, context)
        val protocol = ai.protocolId?.let(repository::getProtocol)
        return EmergencyResult(
            interpretation = ai.classification.interpretation,
            confidence = ai.classification.confidence,
            protocol = protocol
        )
    }
}
