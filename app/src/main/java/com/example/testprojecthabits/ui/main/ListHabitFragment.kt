package com.example.testprojecthabits.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testprojecthabits.R
import com.example.testprojecthabits.ui.DI.AppModule
import com.example.testprojecthabits.ui.DI.DaggerAppComponent
import com.example.testprojecthabits.ui.HabitsAdapter
import com.example.testprojecthabits.ui.modeles.Habit
import com.example.testprojecthabits.ui.modeles.HabitModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_list_habit.*
import javax.inject.Inject

class ListHabitFragment : Fragment() {

    @Inject
    lateinit var repository: MainRepository

//    val customAdapter = HabitsAdapter {habit -> onClickEditHabit(habit) }
    val customAdapter: HabitsAdapter by lazy { HabitsAdapter { habit -> onClickEditHabit(habit) } }

    var isSave = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.builder()
            .appModule(AppModule(requireActivity().application))
            .build()
            .injectListHabitFragment(this)
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
//            val intent = Intent(this, NewHabitActivity::class.java)
//            intent.putExtra("isSave", isSave)
//            startActivity(intent)
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

    fun onClickEditHabit(habit: Habit){
        isSave = false
//        val intent = Intent(this, NewHabitActivity::class.java)
//        intent.putExtra("habit", habitToHabitModel(habit))
//        intent.putExtra("isSave", isSave)
//        startActivity(intent)

    }

    fun habitToHabitModel(habit: Habit) =
        with(habit){
            HabitModel(id, name, description, priority, type, number, interval, color)
        }

    companion object {

        @JvmStatic
        fun newInstance() = ListHabitFragment()
    }
}