package com.example.testprojecthabits.presentation.list_habits

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testprojecthabits.data.db.HabitsDao
import com.example.testprojecthabits.data.repositories.MainRepository
import com.example.testprojecthabits.presentation.viewmodeles.ListHabitViewModel
import javax.inject.Inject

class ListHabitViewModelFactory @Inject constructor(val db: HabitsDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListHabitViewModel::class.java)) {
            return ListHabitViewModel(MainRepository(db)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}