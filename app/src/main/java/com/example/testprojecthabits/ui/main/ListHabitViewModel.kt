package com.example.testprojecthabits.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testprojecthabits.ui.modeles.Habit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListHabitViewModel(private val repository: MainRepository) : ViewModel() {

    private val data = MutableLiveData<List<Habit>>()

    init {
        getHabits()
    }

    private fun getHabits() {
        repository.getHabits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                data.value = it
            }, {
                val er = "Error"
            })
    }

    fun getLiveDataHamits(): MutableLiveData<List<Habit>> = data
}