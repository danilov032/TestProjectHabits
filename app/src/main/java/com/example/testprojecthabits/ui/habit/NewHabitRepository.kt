package com.example.testprojecthabits.ui.habit

import com.example.testprojecthabits.ui.DB.HabitsDao
import com.example.testprojecthabits.ui.modeles.Habit
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
