package com.disasterassistant.app.domain.model

enum class EmergencyCategory {
    SEVERE_BLEEDING,
    MINOR_BLEEDING,
    BURN,
    SUSPECTED_FRACTURE,
    UNCONSCIOUS,
    DIFFICULTY_BREATHING,
    FLOOD,
    TRAPPED,
    NEED_SHELTER,
    NEED_MEDICAL_HELP,
    UNKNOWN
}

enum class EscalationLevel { LOW, MEDIUM, HIGH, CRITICAL, UNKNOWN }

data class EmergencyProtocol(
    val id: String,
    val category: EmergencyCategory,
    val title: String,
    val summary: String,
    val immediateActions: List<String>,
    val warnings: List<String>,
    val escalationLevel: EscalationLevel,
    val verificationStatus: String
)

data class EmergencyClassification(
    val category: EmergencyCategory,
    val confidence: Float,
    val interpretation: String
)

data class EmergencyContext(
    val offlineMode: Boolean,
    val selectedScenario: EmergencyCategory? = null
)

data class AIResponse(
    val classification: EmergencyClassification,
    val protocolId: String?
)
