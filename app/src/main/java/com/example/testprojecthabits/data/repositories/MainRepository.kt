package com.example.testprojecthabits.data.repositories

import com.example.testprojecthabits.data.db.HabitsDao
import com.example.testprojecthabits.domain.modeles.Habit
import io.reactivex.Observable
import javax.inject.Inject

class MainRepository @Inject constructor(val db: HabitsDao) {

    fun getHabits(): Observable<List<Habit>> = db.getHabits()
        .map { listHabit ->
            listHabit.map { habit ->
                with(habit){
                    Habit(id, name, description, priority, type, number, interval, color)
                }
            }
        }
}