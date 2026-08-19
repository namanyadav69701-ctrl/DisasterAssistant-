package com.disasterassistant.app.data.repository

import com.disasterassistant.app.data.local.EmergencyProtocolDao
import com.disasterassistant.app.data.local.ProtocolSeedData
import com.disasterassistant.app.domain.model.EmergencyCategory
import com.disasterassistant.app.domain.model.EmergencyProtocol
import com.disasterassistant.app.domain.model.EscalationLevel
import com.disasterassistant.app.domain.repository.EmergencyRepository

class RoomEmergencyRepository(
    private val dao: EmergencyProtocolDao
) : EmergencyRepository {
    override suspend fun initializeIfNeeded() {
        if (dao.count() == 0) dao.insertAll(ProtocolSeedData.protocols)
    }

    override suspend fun getProtocol(id: String): EmergencyProtocol? = dao.getById(id)?.let { entity ->
        EmergencyProtocol(
            id = entity.id,
            category = EmergencyCategory.valueOf(entity.category),
            title = entity.title,
            summary = entity.summary,
            immediateActions = entity.immediateActions.split("|").filter { it.isNotBlank() },
            warnings = entity.warnings.split("|").filter { it.isNotBlank() },
            escalationLevel = EscalationLevel.valueOf(entity.escalationLevel),
            verificationStatus = entity.verificationStatus
        )
    }
}
