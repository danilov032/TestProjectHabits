package com.example.testprojecthabits.ui.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testprojecthabits.R
import com.example.testprojecthabits.ui.DB.dbAbstract
import com.example.testprojecthabits.ui.DI.AppModule
import com.example.testprojecthabits.ui.DI.DaggerAppComponent
import com.example.testprojecthabits.ui.HabitsAdapter
import com.example.testprojecthabits.ui.habit.NewHabitActivity
import com.example.testprojecthabits.ui.modeles.Habit
import com.example.testprojecthabits.ui.modeles.HabitModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import io.reactivex.Observable


class MainActivity : AppCompatActivity() {

    @Inject lateinit var repository: MainRepository

//    val customAdapter = HabitsAdapter {habit -> onClickEditHabit(habit) }

    val customAdapter: HabitsAdapter by lazy { HabitsAdapter { habit -> onClickEditHabit(habit) } }

    var isSave = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()
            .injectActivity(this)

        list_habies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = customAdapter
        }

        getHabits()

        fab.setOnClickListener {
            val intent = Intent(this, NewHabitActivity::class.java)
            intent.putExtra("isSave", isSave)
            startActivity(intent)
        }
    }

    @SuppressLint("CheckResult")
    fun getHabits() {
        repository.getHabits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                customAdapter.updateData(it)
            }, {
                val er = 10
            })
    }

    fun onClickEditHabit(habit: Habit){
        isSave = false
        val intent = Intent(this, NewHabitActivity::class.java)
        val l = habitToHabitModel(habit)
        intent.putExtra("habit", habitToHabitModel(habit))
        intent.putExtra("isSave", isSave)
        startActivity(intent)
    }

    fun habitToHabitModel(habit: Habit) =
        with(habit){
            HabitModel(id, name, description, priority, type, number, interval, color)
        }
}