package com.example.testprojecthabits.presentation.habit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testprojecthabits.data.db.HabitsDao
import com.example.testprojecthabits.data.repositories.NewHabitRepository
import javax.inject.Inject

class NewHabitViewModelFactory @Inject constructor(val db: HabitsDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewHabitViewModel::class.java)) {
            return NewHabitViewModel(NewHabitRepository(db)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}