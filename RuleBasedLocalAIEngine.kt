package com.disasterassistant.app.ai

import com.disasterassistant.app.domain.model.AIResponse
import com.disasterassistant.app.domain.model.EmergencyCategory
import com.disasterassistant.app.domain.model.EmergencyClassification
import com.disasterassistant.app.domain.model.EmergencyContext

/**
 * MOCK LOCAL AI used only for Stage 1.
 * It performs deterministic, on-device classification and does NOT generate medical instructions.
 * Replace this class with LlamaCppLocalAIEngine later without changing UI or repositories.
 */
class RuleBasedLocalAIEngine : LocalAIEngine {
    override suspend fun interpret(userMessage: String, context: EmergencyContext): AIResponse {
        val text = userMessage.lowercase()
        val category = context.selectedScenario ?: when {
            listOf("bleeding badly", "heavy bleeding", "severe bleeding").any(text::contains) -> EmergencyCategory.SEVERE_BLEEDING
            "bleed" in text -> EmergencyCategory.MINOR_BLEEDING
            "burn" in text -> EmergencyCategory.BURN
            "fracture" in text || "broken bone" in text -> EmergencyCategory.SUSPECTED_FRACTURE
            "unconscious" in text || "not waking" in text -> EmergencyCategory.UNCONSCIOUS
            "breath" in text -> EmergencyCategory.DIFFICULTY_BREATHING
            "flood" in text -> EmergencyCategory.FLOOD
            "trapped" in text -> EmergencyCategory.TRAPPED
            "shelter" in text -> EmergencyCategory.NEED_SHELTER
            "medical help" in text || "hospital" in text -> EmergencyCategory.NEED_MEDICAL_HELP
            else -> EmergencyCategory.UNKNOWN
        }
        val confidence = if (category == EmergencyCategory.UNKNOWN) 0.25f else 0.90f
        return AIResponse(
            classification = EmergencyClassification(
                category = category,
                confidence = confidence,
                interpretation = if (category == EmergencyCategory.UNKNOWN) {
                    "I could not confidently classify this emergency."
                } else {
                    "Possible ${category.name.lowercase().replace('_', ' ')} detected."
                }
            ),
            protocolId = if (category == EmergencyCategory.UNKNOWN) null else category.name
        )
    }
}
