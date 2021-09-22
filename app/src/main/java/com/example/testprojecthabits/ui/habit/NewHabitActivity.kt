package com.example.testprojecthabits.ui.habit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testprojecthabits.R
import kotlinx.android.synthetic.main.activity_new_habit.*


class NewHabitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_habit)

        bt_save.setOnClickListener {
            
        }
    }
}