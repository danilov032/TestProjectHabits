package com.example.testprojecthabits.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testprojecthabits.data.entity_model.EntityHabit
import io.reactivex.Observable

@Dao
interface HabitsDao {
    @Query("SELECT * FROM EntityHabit")
    fun getHabits(): Observable<List<EntityHabit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHabit(vararg todo: EntityHabit)

    @Query("DELETE from EntityHabit WHERE name = :name")
    fun deleteHabit(name: String)

    @Query("UPDATE EntityHabit SET name = :name, description = :description, priority = :priority, type = :type, number = :number, interval = :interval, color = :color WHERE id = :id")
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