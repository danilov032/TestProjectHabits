package com.example.testprojecthabits.presentation.habit

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.testprojecthabits.R
import com.example.testprojecthabits.data.repositories.NewHabitRepository
import com.example.testprojecthabits.di.AppModule
import com.example.testprojecthabits.di.DaggerAppComponent
import com.example.testprojecthabits.data.entity_model.EntityHabit
import com.example.testprojecthabits.domain.modeles.Habit
import com.example.testprojecthabits.presentation.viewmodeles.NewHabitViewModel
import kotlinx.android.synthetic.main.fragment_new_habit.*
import javax.inject.Inject

private const val ARG_ISSAVE = "isSave"
private const val ARG_HABIT = "habit"

class NewHabitFragment : Fragment() {
    private var isSave: Boolean = false
    private var habit: Habit = Habit()

    @Inject
    lateinit var repository: NewHabitRepository

    @Inject
    lateinit var factory: NewHabitViewModelFactory

    private lateinit var newHabitViewModel: NewHabitViewModel

    private fun setupViewModel() {
        newHabitViewModel = ViewModelProviders.of(
            this,
            factory
        ).get(NewHabitViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            isSave = it.getBoolean(ARG_ISSAVE)
            habit = it.getSerializable(ARG_HABIT) as Habit
        }

        DaggerAppComponent.builder()
            .appModule(AppModule(requireActivity().application))
            .build()
            .injectNewHabitFragment(this)

        setupViewModel()
        newHabitViewModel.setData(isSave, habit)
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

        newHabitViewModel.getIsDone().observe(this, {
            Log.d("AAA", "fragmentManager")
            fragmentManager?.popBackStack()
        })

        newHabitViewModel.getLiveDataMessageError().observe(this,{
            showMessageError(it ?: "Unknown error")
        })

        newHabitViewModel.getLiveDataHabit().observe(this, {
            fillInTheFields(it)
        })

        bt_save.setOnClickListener {
            val radio = if (radio_useful.isChecked) radio_useful.text.toString()
            else radio_harmful.text.toString()

            newHabitViewModel.performAnAction(
                name = ed_name_habit.text.toString(),
                description = ed_description_habit.text.toString(),
                priority = spinner_priority.selectedItem.toString(),
                radio = radio,
                number = ed_number.text.toString().toShort(),
                interval = ed_interval.text.toString().toShort()
            )
        }
    }

    private fun fillInTheFields(habit: Habit) {
        ed_name_habit.setText(habit.name)
        ed_description_habit.setText(habit.description)
        ed_number.setText(habit.number.toString())
        ed_interval.setText(habit.interval.toString())
    }

    private fun initScroll() {
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
        val margin = heightView / 4

        for (i in 0..15) {
            var view = View(requireContext())

            var layoutParams = LinearLayout.LayoutParams(
                heightView,
                heightView
            )
            layoutParams.setMargins(margin, 0, margin, 0)
            view.layoutParams = layoutParams
            view.setBackgroundColor(Color.parseColor(listColor[i]))

            scroll_container.addView(view)
        }
    }

    private fun showMessageError(message: String){
        Toast.makeText(requireContext(),message, Toast.LENGTH_SHORT).show()
    }

    companion object {
        @JvmStatic
        fun newInstance(isSave: Boolean, habit: Habit) =
            NewHabitFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_ISSAVE, isSave)
                    putSerializable(ARG_HABIT, habit)
                }
            }
    }
}