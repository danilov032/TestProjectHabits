package com.example.testprojecthabits.presentation

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.testprojecthabits.R
import com.example.testprojecthabits.di.AppModule
import com.example.testprojecthabits.di.DaggerAppComponent
import com.example.testprojecthabits.presentation.list_habits.ListHabitFragment


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()
            .injectActivity(this)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, ListHabitFragment.newInstance())
                .commit()
        }
    }
}