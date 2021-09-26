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
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import io.reactivex.Observable


class MainActivity : AppCompatActivity() {

    @Inject lateinit var repository: MainRepository

    val customAdapter = HabitsAdapter()

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
}