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
import com.example.testprojecthabits.ui.modeles.HabitModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_new_habit.*
import javax.inject.Inject


class NewHabitActivity : AppCompatActivity() {
    @Inject
    lateinit var repository: NewHabitRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_habit)

        DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()
            .injectNewHabitActivity(this)

        val isSave = getIntent().getSerializableExtra("isSave")
        var habitM = HabitModel()
        if (isSave == false) {
            habitM = intent.getSerializableExtra("habit") as HabitModel
            fillInTheFields(habitM)
        }

        bt_save.setOnClickListener {
            var radio = ""
            radio = if (radio_useful.isChecked) radio_useful.text.toString()
            else radio_harmful.text.toString()

            if (isSave == true) {
                var habit = Habit(
                    name = ed_name_habit.text.toString(),
                    description = ed_description_habit.text.toString(),
                    priority = spinner_priority.selectedItem.toString(),
                    type = radio,
                    number = ed_number.text.toString().toShort(),
                    interval = ed_interval.text.toString().toShort(),
                    color = "000000"
                )
                insertHabit(habit)
            }
            else {
                var habit = Habit(
                    id = habitM.id,
                    name = ed_name_habit.text.toString(),
                    description = ed_description_habit.text.toString(),
                    priority = spinner_priority.selectedItem.toString(),
                    type = radio,
                    number = ed_number.text.toString().toShort(),
                    interval = ed_interval.text.toString().toShort(),
                    color = "000000"
                )
                updateHabit(habit)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun fillInTheFields(habit: HabitModel) {
        ed_name_habit.setText(habit.name)
        ed_description_habit.setText(habit.description)
        ed_number.setText(habit.number.toString())
        ed_interval.setText(habit.interval.toString())
    }

    @SuppressLint("CheckResult")
    fun insertHabit(habit: Habit) {
        Observable.just(habit)
            .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                repository.addHabit(it)
            }, {
                val er = 10
            })
    }

    @SuppressLint("CheckResult")
    fun updateHabit(habit: Habit) {
        Observable.just(habit)
            .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
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
            }, {
                val er = 10
            })
    }
}