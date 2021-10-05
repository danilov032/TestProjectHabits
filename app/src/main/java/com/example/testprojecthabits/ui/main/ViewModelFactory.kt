package com.example.testprojecthabits.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testprojecthabits.ui.DB.HabitsDao
import com.example.testprojecthabits.ui.main.ListHabitViewModel
import com.example.testprojecthabits.ui.main.MainRepository
import javax.inject.Inject

class ListHabitViewModelFactory @Inject constructor(val db: HabitsDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListHabitViewModel::class.java)) {
            return ListHabitViewModel(MainRepository(db)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}