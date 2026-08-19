package com.disasterassistant.app

import android.app.Application

class DisasterAssistantApp : Application() {
    val container by lazy { AppContainer(this) }
}
