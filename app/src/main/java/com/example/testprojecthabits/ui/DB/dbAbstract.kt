package com.example.testprojecthabits.ui.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testprojecthabits.ui.modeles.Habit

@Database(entities = arrayOf(Habit::class),version = 1)
abstract class dbAbstract (): RoomDatabase() {
    abstract fun habitsDao(): HabitsDao

    companion object {
        fun getDatabase(context: Context): dbAbstract {
            return Room.databaseBuilder(
                context.applicationContext,
                dbAbstract::class.java,
                "habitsDB"
            ).build()
        }
    }
}
