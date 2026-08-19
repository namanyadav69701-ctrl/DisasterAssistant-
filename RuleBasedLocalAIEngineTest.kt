package com.disasterassistant.app

import com.disasterassistant.app.ai.RuleBasedLocalAIEngine
import com.disasterassistant.app.domain.model.EmergencyCategory
import com.disasterassistant.app.domain.model.EmergencyContext
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RuleBasedLocalAIEngineTest {
    private val engine = RuleBasedLocalAIEngine()

    @Test
    fun severeBleeding_isClassifiedOffline() = runTest {
        val result = engine.interpret(
            "My friend is bleeding badly.",
            EmergencyContext(offlineMode = true)
        )
        assertEquals(EmergencyCategory.SEVERE_BLEEDING, result.classification.category)
        assertEquals("SEVERE_BLEEDING", result.protocolId)
    }

    @Test
    fun unknownMessage_doesNotInventProtocol() = runTest {
        val result = engine.interpret("Something happened.", EmergencyContext(offlineMode = true))
        assertEquals(EmergencyCategory.UNKNOWN, result.classification.category)
        assertEquals(null, result.protocolId)
    }
}
