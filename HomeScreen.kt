package com.disasterassistant.app.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    offlineSimulation: Boolean,
    onOfflineSimulationChanged: (Boolean) -> Unit,
    onEnterSurvivalMode: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("DISASTER ASSISTANT", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Text("Offline-first emergency assistant", style = MaterialTheme.typography.bodyLarge)

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text("SYSTEM STATUS", fontWeight = FontWeight.Bold)
                Text("AI: LOCAL MOCK READY")
                Text("EMERGENCY DATA: LOCAL READY")
                Text(if (offlineSimulation) "NETWORK: FORCED OFFLINE" else "NETWORK: NORMAL")
                Text("GPS / MAPS: planned for next stages")
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(Modifier.weight(1f)) {
                    Text("Offline simulation", fontWeight = FontWeight.Bold)
                    Text("Blocks future network-backed operations during testing.")
                }
                Switch(checked = offlineSimulation, onCheckedChange = onOfflineSimulationChanged)
            }
        }

        Spacer(Modifier.height(6.dp))
        Button(
            modifier = Modifier.fillMaxWidth().height(64.dp),
            onClick = onEnterSurvivalMode
        ) {
            Text("ENTER SURVIVAL MODE", fontWeight = FontWeight.Bold)
        }

        Text(
            "Emergency aid only — not a replacement for professional medical care.",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
