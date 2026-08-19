package com.disasterassistant.app.domain.repository

import com.disasterassistant.app.domain.model.EmergencyProtocol

interface EmergencyRepository {
    suspend fun initializeIfNeeded()
    suspend fun getProtocol(id: String): EmergencyProtocol?
}
