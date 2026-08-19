package com.disasterassistant.app.ui.screens.survival

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.disasterassistant.app.domain.model.EmergencyCategory

@Composable
fun SurvivalScreen(viewModel: SurvivalViewModel, onBack: () -> Unit) {
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("SURVIVAL MODE", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            OutlinedButton(onClick = onBack) { Text("Back") }
        }
        Text("AI: LOCAL MOCK • EMERGENCY DATA: LOCAL • NETWORK-INDEPENDENT MVP")
        Text("What is happening?", style = MaterialTheme.typography.titleLarge)

        ScenarioButton("I'm injured / bleeding", EmergencyCategory.SEVERE_BLEEDING, "My friend is bleeding badly.", viewModel)
        ScenarioButton("Someone is unconscious", EmergencyCategory.UNCONSCIOUS, "Someone is unconscious and not responding.", viewModel)
        ScenarioButton("Difficulty breathing", EmergencyCategory.DIFFICULTY_BREATHING, "Someone is having difficulty breathing.", viewModel)
        ScenarioButton("Flood around me", EmergencyCategory.FLOOD, "There is flooding around me.", viewModel)
        ScenarioButton("I'm trapped", EmergencyCategory.TRAPPED, "I am trapped and need help.", viewModel)
        ScenarioButton("I need shelter", EmergencyCategory.NEED_SHELTER, "I need a nearby shelter.", viewModel)
        ScenarioButton("I need medical help", EmergencyCategory.NEED_MEDICAL_HELP, "I need medical help.", viewModel)

        OutlinedTextField(
            value = state.message,
            onValueChange = viewModel::setMessage,
            label = { Text("Describe the emergency") },
            modifier = Modifier.fillMaxWidth(),
            minLines = 3
        )
        Button(onClick = viewModel::submit, modifier = Modifier.fillMaxWidth(), enabled = !state.isLoading) {
            Text("ANALYZE OFFLINE")
        }

        if (state.isLoading) CircularProgressIndicator()
        state.error?.let { Text("Error: $it") }

        state.result?.let { result ->
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(result.interpretation, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Classifier confidence: ${(result.confidence * 100).toInt()}%")
                    val protocol = result.protocol
                    if (protocol == null) {
                        Text("The app cannot determine an appropriate protocol from the available information.")
                        Text("Seek emergency assistance when possible.", fontWeight = FontWeight.Bold)
                    } else {
                        Text(protocol.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                        Text(protocol.summary)
                        Text("Stored emergency guidance", fontWeight = FontWeight.Bold)
                        protocol.immediateActions.forEachIndexed { index, action -> Text("${index + 1}. $action") }
                        Text("Warnings", fontWeight = FontWeight.Bold)
                        protocol.warnings.forEach { Text("• $it") }
                        Text("Severity: ${protocol.escalationLevel}")
                        Text(protocol.verificationStatus, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }

        Text(
            "Emergency aid only — not a replacement for professional medical care. Demo protocols require professional review before real-world deployment.",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
private fun ScenarioButton(
    label: String,
    category: EmergencyCategory,
    defaultMessage: String,
    viewModel: SurvivalViewModel
) {
    OutlinedButton(
        onClick = { viewModel.chooseScenario(category, defaultMessage) },
        modifier = Modifier.fillMaxWidth()
    ) { Text(label) }
}
