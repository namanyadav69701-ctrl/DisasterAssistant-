package com.disasterassistant.app.ui.screens.survival

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.disasterassistant.app.domain.model.EmergencyCategory
import com.disasterassistant.app.domain.model.EmergencyContext
import com.disasterassistant.app.domain.usecase.EmergencyResult
import com.disasterassistant.app.domain.usecase.InterpretEmergencyUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SurvivalUiState(
    val message: String = "",
    val selectedScenario: EmergencyCategory? = null,
    val isLoading: Boolean = false,
    val result: EmergencyResult? = null,
    val error: String? = null
)

class SurvivalViewModel(
    private val interpretEmergency: InterpretEmergencyUseCase,
    private val offlineMode: Boolean
) : ViewModel() {
    private val _state = MutableStateFlow(SurvivalUiState())
    val state: StateFlow<SurvivalUiState> = _state.asStateFlow()

    fun setMessage(value: String) = _state.update { it.copy(message = value) }

    fun chooseScenario(category: EmergencyCategory, defaultMessage: String) {
        _state.update { it.copy(selectedScenario = category, message = defaultMessage, result = null, error = null) }
    }

    fun submit() {
        val current = _state.value
        if (current.message.isBlank()) return
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null, result = null) }
            runCatching {
                interpretEmergency(
                    current.message,
                    EmergencyContext(offlineMode = offlineMode, selectedScenario = current.selectedScenario)
                )
            }.onSuccess { result ->
                _state.update { it.copy(isLoading = false, result = result) }
            }.onFailure { throwable ->
                _state.update { it.copy(isLoading = false, error = throwable.message ?: "Unknown error") }
            }
        }
    }

    companion object {
        fun factory(useCase: InterpretEmergencyUseCase, offlineMode: Boolean): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return SurvivalViewModel(useCase, offlineMode) as T
                }
            }
    }
}
