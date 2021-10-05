package com.example.testprojecthabits.ui.DI

import com.example.testprojecthabits.ui.habit.NewHabitFragment
import com.example.testprojecthabits.ui.main.ListHabitFragment
import com.example.testprojecthabits.ui.MainActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun injectActivity(activity: MainActivity)
    fun injectNewHabitFragment(activity: NewHabitFragment)
    fun injectListHabitFragment(activity: ListHabitFragment)
}