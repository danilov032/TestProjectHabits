package com.example.testprojecthabits.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testprojecthabits.data.entity_model.EntityHabit

@Database(entities = [EntityHabit::class],version = 1)
abstract class dbAbstract : RoomDatabase() {
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
