package com.example.testprojecthabits.ui.habit

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testprojecthabits.R
import com.example.testprojecthabits.ui.DI.AppModule
import com.example.testprojecthabits.ui.DI.DaggerAppComponent
import com.example.testprojecthabits.ui.main.MainActivity
import com.example.testprojecthabits.ui.modeles.Habit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_new_habit.*
import javax.inject.Inject


class NewHabitActivity : AppCompatActivity() {
    @Inject
    lateinit var repository: NewHabitRepository

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_habit)

        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()
            .injectNewHabitActivity(this)



        bt_save.setOnClickListener {
            var radio = ""
            radio = if (radio_useful.isChecked) radio_useful.text.toString()
            else radio_harmful.text.toString()

            var habit = Habit(name = ed_name_habit.text.toString(),
                description = ed_description_habit.text.toString(),
                priority = spinner_priority.selectedItem.toString(),
                type = radio,
                number = ed_number.text.toString().toShort(),
                interval = ed_interval.text.toString().toShort(),
                color = "000000")

            Observable.just(habit)
                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    var h = it
                    repository.addHabit(it)
                }, {
                    val er = 10
                })
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}