package org.example.project

import android.app.Application
import org.example.project.di.sharedKoinModules
import org.example.project.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyFirstKMPApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelsModule

        startKoin {
            androidContext(this@MyFirstKMPApp)
            modules(modules)
        }
    }
}
