package com.example.testprojecthabits.presentation.habit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testprojecthabits.data.repositories.NewHabitRepository
import com.example.testprojecthabits.domain.modeles.Habit
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class NewHabitViewModel(
    private val repository: NewHabitRepository
//    private val isSave: Boolean,
//    private val habit: Habit
) : ViewModel() {

    private val data = MutableLiveData<Habit>()

    fun insertHabit(habit: Habit) {
        Observable.just(habit)
            .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                repository.addHabit(it)
            }, {
                val er = "Error"
            })
    }

    fun getLiveDataHabit(): LiveData<Habit> = data
}