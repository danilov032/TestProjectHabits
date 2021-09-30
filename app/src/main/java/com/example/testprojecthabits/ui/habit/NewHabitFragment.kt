package com.example.testprojecthabits.ui.habit

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.testprojecthabits.R
import com.example.testprojecthabits.ui.DI.AppModule
import com.example.testprojecthabits.ui.DI.DaggerAppComponent
import com.example.testprojecthabits.ui.modeles.Habit
import com.example.testprojecthabits.ui.modeles.HabitModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_new_habit.*
import javax.inject.Inject

private const val ARG_ISSAVE = "isSave"
private const val ARG_HABIT = "habit"

class NewHabitFragment : Fragment() {
    private var isSave: Boolean = false
    private var habit: HabitModel = HabitModel()
    @Inject
    lateinit var repository: NewHabitRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isSave = it.getBoolean(ARG_ISSAVE)
            habit = it.getSerializable(ARG_HABIT) as HabitModel
        }

        DaggerAppComponent.builder()
            .appModule(AppModule(requireActivity().application))
            .build()
            .injectNewHabitFragment(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_habit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initScroll()

        var habitM = HabitModel()
        if (isSave == false) {
            habitM = habit
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
            } else {
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
            fragmentManager?.popBackStack()
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

    fun initScroll() {
        var listColor: List<String> = listOf(
            "#ffcaa4",
            "#ffe89f",
            "#ffb3a0",
            "#f1bb79",
            "#b4e687",
            "#90c59e",
            "#98c6a3",
            "#92c070",
            "#4c3dd3",
            "#80307a",
            "#ff2146",
            "#ff2146",
            "#46383a",
            "#707070",
            "#FFFFFF",
            "#000000"
        )
        val displayMetrics = requireContext().resources.displayMetrics
        val height = displayMetrics.heightPixels

        val heightView = height / 10

        for (i in 0..15) {
            var view = View(requireContext())

            var layoutParams = LinearLayout.LayoutParams(
                heightView,
                heightView
            )
            layoutParams.setMargins(0, 0, heightView / 2, 0)


            view.setLayoutParams(layoutParams)
            view.setBackgroundColor(Color.parseColor(listColor[i]))

            scroll_container.addView(view)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(isSave: Boolean, habit: HabitModel) =
            NewHabitFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_ISSAVE, isSave)
                    putSerializable(ARG_HABIT, habit)
                }
            }
    }
}