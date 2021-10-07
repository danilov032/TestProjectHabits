package com.example.testprojecthabits.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
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

    fun getHabits() {
        Log.d("AAA", "getHabits")
        repository.getHabits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                data.value = it
            }, {
                val er = "Error"
            })
    }

    fun getLiveDataHabits(): LiveData<List<Habit>> = data
}