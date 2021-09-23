package com.example.testprojecthabits.ui.DI

import android.app.Application
import com.example.testprojecthabits.ui.DB.HabitsDao
import com.example.testprojecthabits.ui.DB.dbAbstract
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesDbAbstract(): HabitsDao {
        return dbAbstract.getDatabase(application).habitsDao()
    }
}