package com.example.testprojecthabits.ui.DI

import android.app.Application
import com.example.testprojecthabits.ui.DB.HabitsDao
import com.example.testprojecthabits.ui.DB.dbAbstract
import com.example.testprojecthabits.ui.habit.NewHabitRepository
import com.example.testprojecthabits.ui.main.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @get:Provides
    @Singleton
    val db: HabitsDao = dbAbstract.getDatabase(application).habitsDao()

    @Provides
    fun provideMainRepository(): MainRepository {
        return MainRepository(db)
    }

    @Provides
    fun provideNewHabitRepository(): NewHabitRepository {
        return NewHabitRepository(db)
    }
}