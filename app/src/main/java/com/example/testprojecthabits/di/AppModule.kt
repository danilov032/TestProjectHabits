package com.example.testprojecthabits.di

import android.app.Application
import com.example.testprojecthabits.data.db.HabitsDao
import com.example.testprojecthabits.data.db.dbAbstract
import com.example.testprojecthabits.data.repositories.NewHabitRepository
import com.example.testprojecthabits.data.repositories.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @get:Provides
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