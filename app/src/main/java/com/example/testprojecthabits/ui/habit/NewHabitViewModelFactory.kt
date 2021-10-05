package com.example.testprojecthabits.ui.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testprojecthabits.ui.DB.HabitsDao
import javax.inject.Inject

class NewHabitViewModelFactory @Inject constructor(val db: HabitsDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewHabitViewModel::class.java)) {
            return NewHabitViewModel(NewHabitRepository(db)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}