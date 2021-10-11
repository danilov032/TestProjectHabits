package com.example.testprojecthabits.di

import com.example.testprojecthabits.presentation.habit.NewHabitFragment
import com.example.testprojecthabits.presentation.list_habits.ListHabitFragment
import com.example.testprojecthabits.presentation.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectActivity(activity: MainActivity)
    fun injectNewHabitFragment(activity: NewHabitFragment)
    fun injectListHabitFragment(activity: ListHabitFragment)
}