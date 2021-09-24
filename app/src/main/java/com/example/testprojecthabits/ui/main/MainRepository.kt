package com.example.testprojecthabits.ui.main

import com.example.testprojecthabits.ui.DB.dbAbstract
import javax.inject.Inject

class MainRepository @Inject constructor(val db: dbAbstract) {

    fun getHabits() = db.habitsDao()
}