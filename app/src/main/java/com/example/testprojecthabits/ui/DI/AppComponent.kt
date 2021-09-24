package com.example.testprojecthabits.ui.DI

import com.example.testprojecthabits.ui.main.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectActivity(activity: MainActivity)
}