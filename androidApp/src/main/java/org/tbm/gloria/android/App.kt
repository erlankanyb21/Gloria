package org.tbm.gloria.android

import PlatformConfiguration
import android.app.Application

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        initPlatformSDK()
    }
}
fun App.initPlatformSDK() =
    PlatformSDK.init(
        configuration = PlatformConfiguration(androidContext = applicationContext)
    )