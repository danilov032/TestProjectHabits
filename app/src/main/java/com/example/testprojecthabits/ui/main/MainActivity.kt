package com.example.testprojecthabits.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testprojecthabits.R
import com.example.testprojecthabits.ui.DB.dbAbstract
import com.example.testprojecthabits.ui.habit.NewHabitActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject lateinit var db: dbAbstract



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        DaggerAppComponent.builder()
//            .appModule(AppModule(application))
//            .build()
//            .inject(this)

        fab.setOnClickListener {
            val intent = Intent(this, NewHabitActivity::class.java)
            startActivity(intent)
        }
    }
}