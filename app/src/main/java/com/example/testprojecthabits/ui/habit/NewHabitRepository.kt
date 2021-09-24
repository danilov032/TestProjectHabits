package com.example.testprojecthabits.ui.habit

import com.example.testprojecthabits.ui.DB.HabitsDao
import com.example.testprojecthabits.ui.modeles.Habit
import javax.inject.Inject

class NewHabitRepository @Inject constructor(val db: HabitsDao) {

    fun addHabit(habit: Habit) = db.insertHabit(habit)
}