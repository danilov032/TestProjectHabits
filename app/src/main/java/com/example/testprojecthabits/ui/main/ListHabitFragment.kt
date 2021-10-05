package com.example.testprojecthabits.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testprojecthabits.R
import com.example.testprojecthabits.ui.DI.AppModule
import com.example.testprojecthabits.ui.DI.DaggerAppComponent
import com.example.testprojecthabits.ui.HabitsAdapter
import com.example.testprojecthabits.ui.ListHabitViewModelFactory
import com.example.testprojecthabits.ui.habit.NewHabitFragment
import com.example.testprojecthabits.ui.modeles.Habit
import com.example.testprojecthabits.ui.modeles.HabitModel
import kotlinx.android.synthetic.main.fragment_list_habit.*
import javax.inject.Inject

class ListHabitFragment : Fragment() {

    @Inject
    lateinit var repository: MainRepository

    @Inject
    lateinit var factory: ListHabitViewModelFactory

    val customAdapter: HabitsAdapter by lazy { HabitsAdapter { habit -> onClickEditHabit(habit) } }

    private lateinit var listHabitViewModel: ListHabitViewModel

    var isSave = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.builder()
            .appModule(AppModule(requireActivity().application))
            .build()
            .injectListHabitFragment(this)

        setupViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_habit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_habies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = customAdapter
        }

        getHabits()

        fab.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, NewHabitFragment.newInstance(true, HabitModel()))
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    private fun getHabits() {
        listHabitViewModel.getLiveDataHamits().observe(this, Observer {
            customAdapter.updateData(it)
        })
    }

    private fun onClickEditHabit(habit: Habit) {
        isSave = false
        fragmentManager?.beginTransaction()
            ?.replace(R.id.container, NewHabitFragment.newInstance(false, habitToHabitModel(habit)))
            ?.addToBackStack(null)
            ?.commit()
    }

    private fun habitToHabitModel(habit: Habit) =
        with(habit) {
            HabitModel(id, name, description, priority, type, number, interval, color)
        }

    private fun setupViewModel() {
        listHabitViewModel = ViewModelProviders.of(
            this,
            factory
        ).get(ListHabitViewModel::class.java)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ListHabitFragment()
    }
}