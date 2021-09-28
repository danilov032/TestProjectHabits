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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(vararg todo: Habit)

    @Query("DELETE from Habit WHERE name = :name")
    fun deleteHabit(name: String)

    @Query("UPDATE Habit SET name = :name, description = :description, priority = :priority, type = :type, number = :number, interval = :interval, color = :color WHERE id = :id")
    fun update(
        id: Int,
        name: String,
        description: String,
        priority: String,
        type: String,
        number: Short,
        interval: Short,
        color: String
    )
}