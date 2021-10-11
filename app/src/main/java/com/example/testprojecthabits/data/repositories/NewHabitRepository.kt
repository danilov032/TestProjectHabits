package com.example.testprojecthabits.data.repositories

import com.example.testprojecthabits.data.db.HabitsDao
import com.example.testprojecthabits.domain.modeles.Habit
import javax.inject.Inject

class NewHabitRepository @Inject constructor(val db: HabitsDao) {

    fun addHabit(habit: Habit) = db.insertHabit(habit)

    fun updateHabit(
        id: Int,
        name: String,
        description: String,
        priority: String,
        type: String,
        number: Short,
        interval: Short,
        color: String
    ) = db.update(id, name, description, priority, type, number, interval, color)
}
