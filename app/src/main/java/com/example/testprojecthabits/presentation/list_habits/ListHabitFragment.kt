package com.example.testprojecthabits.presentation.list_habits

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testprojecthabits.R
import com.example.testprojecthabits.di.AppModule
import com.example.testprojecthabits.di.DaggerAppComponent
import com.example.testprojecthabits.presentation.adapters.HabitsAdapter
import com.example.testprojecthabits.presentation.habit.NewHabitFragment
import com.example.testprojecthabits.domain.modeles.Habit
import com.example.testprojecthabits.domain.modeles.HabitModel
import kotlinx.android.synthetic.main.fragment_list_habit.*
import javax.inject.Inject

class ListHabitFragment : Fragment() {

    @Inject
    lateinit var factory: ListHabitViewModelFactory

    val customAdapter: HabitsAdapter by lazy { HabitsAdapter { habit -> onClickEditHabit(habit) } }

    private lateinit var listHabitViewModel: ListHabitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Fragment1", "onCreate")
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

        listHabitViewModel.getLiveDataHabits().observe(this, {
            customAdapter.updateData(it)
            Log.d("AAA", "Update data")
        })

        fab.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, NewHabitFragment.newInstance(true, HabitModel()))
                ?.addToBackStack(null)
                ?.commit()
        }
    }

    private fun onClickEditHabit(habit: Habit) {
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