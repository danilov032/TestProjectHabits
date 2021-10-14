package com.example.testprojecthabits.presentation.viewmodeles

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testprojecthabits.data.repositories.NewHabitRepository
import com.example.testprojecthabits.domain.modeles.Habit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_new_habit.*

class NewHabitViewModel(
    private val repository: NewHabitRepository
) : ViewModel() {
    private val isDone = MutableLiveData<Boolean>()
    private val messageError = MutableLiveData<String>()
    private var habit = MutableLiveData<Habit>()
    private var isSaved: Boolean = false
    private var isDataInit: Boolean = false

    fun setData(save: Boolean, habitElem: Habit) {
        if(!isDataInit) {
            isDataInit = true
            Log.d("AAA", "setData")
            habit.value = habitElem
            isSaved = save
        }
    }

    fun performAnAction(
        name: String,
        description: String,
        priority: String,
        radio: String,
        number: Short,
        interval: Short
    ) {
        var habitNewModel: Habit
        if (isSaved) {
            habitNewModel = Habit(
                name = name,
                description = description,
                priority = priority,
                type = radio,
                number = number,
                interval = interval,
                color = "000000"
            )
            insertHabit(habitNewModel)
        } else {
            val id = habit.value?.id ?: 0
            habitNewModel = Habit(
                id = id ,
                name = name,
                description = description,
                priority = priority,
                type = radio,
                number = number,
                interval = interval,
                color = "000000"
            )
            updateHabit(habitNewModel)
        }
    }

    private fun insertHabit(habit: Habit) {
        Observable.just(habit)
            .doOnNext { repository.addHabit(it) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("AAA", "isDone")
                isDone.value = true
            }, {
                messageError.value = "Ошибка вставки."
            })
    }

    private fun updateHabit(habit: Habit) {
        Observable.just(habit)
            .doOnNext {
                repository.updateHabit(
                    it.id,
                    it.name,
                    it.description,
                    it.priority,
                    it.type,
                    it.number,
                    it.interval,
                    it.color
                )
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("AAA", "isDone")
                isDone.value = true
            }, {
                messageError.value = "Ошибка обновлениея данных."
            })
    }

    fun getIsDone(): MutableLiveData<Boolean> = isDone
    fun getLiveDataMessageError(): LiveData<String> = messageError
    fun getLiveDataHabit(): LiveData<Habit> = habit
}