package com.example.testprojecthabits.ui.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testprojecthabits.ui.modeles.Habit
import io.reactivex.Observable
import androidx.room.Update

@Dao
interface HabitsDao {
    @Query("SELECT * FROM Habit")
    fun getHabits(): Observable<List<Habit>>

    @Insert
    fun insertHabit(todo: Habit)

    @Query("DELETE from Habit WHERE name = :name")
    fun deleteHabit(name: String)

    @Update
    fun update(habit: Habit?)
}