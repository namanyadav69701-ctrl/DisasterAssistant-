package com.disasterassistant.app.ui.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.disasterassistant.app.AppContainer
import com.disasterassistant.app.ui.screens.home.HomeScreen
import com.disasterassistant.app.ui.screens.survival.SurvivalScreen
import com.disasterassistant.app.ui.screens.survival.SurvivalViewModel

private const val HOME = "home"
private const val SURVIVAL = "survival"

@Composable
fun DisasterAssistantNavHost(container: AppContainer) {
    val navController = rememberNavController()
    var offlineSimulation by remember { mutableStateOf(true) }

    NavHost(navController = navController, startDestination = HOME) {
        composable(HOME) {
            HomeScreen(
                offlineSimulation = offlineSimulation,
                onOfflineSimulationChanged = { offlineSimulation = it },
                onEnterSurvivalMode = { navController.navigate(SURVIVAL) }
            )
        }
        composable(SURVIVAL) {
            val vm: SurvivalViewModel = viewModel(
                factory = SurvivalViewModel.factory(container.interpretEmergency, offlineSimulation)
            )
            SurvivalScreen(viewModel = vm, onBack = { navController.popBackStack() })
        }
    }
}
