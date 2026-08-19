package com.disasterassistant.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.disasterassistant.app.ui.navigation.DisasterAssistantNavHost
import com.disasterassistant.app.ui.theme.DisasterAssistantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val container = (application as DisasterAssistantApp).container
        setContent {
            DisasterAssistantTheme {
                DisasterAssistantNavHost(container)
            }
        }
    }
}
