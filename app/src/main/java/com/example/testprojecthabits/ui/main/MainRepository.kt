package com.example.testprojecthabits.ui.main

import com.example.testprojecthabits.ui.DB.HabitsDao
import com.example.testprojecthabits.ui.modeles.Habit
import io.reactivex.Observable
import javax.inject.Inject

class MainRepository @Inject constructor(val db: HabitsDao) {

    fun getHabits() : Observable<List<Habit>> = db.getHabits()
}