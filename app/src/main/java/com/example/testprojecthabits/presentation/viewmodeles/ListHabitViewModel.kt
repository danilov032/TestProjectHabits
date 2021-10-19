package com.example.testprojecthabits.presentation.viewmodeles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testprojecthabits.data.repositories.MainRepository
import com.example.testprojecthabits.data.entity_model.EntityHabit
import com.example.testprojecthabits.domain.modeles.Habit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ListHabitViewModel(private val repository: MainRepository) : ViewModel() {

    private val data = MutableLiveData<List<Habit>>()
    private val messageError = MutableLiveData<String>()

    fun getHabits() {
        Log.d("AAA", "getHabits")
        repository.getHabits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                data.value = it
            }, {
                messageError.value = "Ошибка при загрузки"
            })
    }

    fun getLiveDataHabits(): LiveData<List<Habit>> = data
    fun getLiveDataMessageError(): LiveData<String> = messageError
}