package com.example.testprojecthabits.ui.habit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testprojecthabits.ui.modeles.Habit
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class NewHabitViewModel (private val repository: NewHabitRepository) : ViewModel() {

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
}