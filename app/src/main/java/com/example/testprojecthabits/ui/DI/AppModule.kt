package com.example.testprojecthabits.ui.DI

import android.app.Application
import com.example.testprojecthabits.ui.DB.HabitsDao
import com.example.testprojecthabits.ui.DB.dbAbstract
import com.example.testprojecthabits.ui.main.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideDbAbstract(): HabitsDao {
        return dbAbstract.getDatabase(application).habitsDao()
    }

    @get:Provides
    val db: dbAbstract = dbAbstract.getDatabase(application)

    @Provides
    fun provideMainRepository(): MainRepository {
        return MainRepository(db)
    }
}