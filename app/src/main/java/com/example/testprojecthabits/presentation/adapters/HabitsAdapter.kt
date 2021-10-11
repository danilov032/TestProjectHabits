package com.example.testprojecthabits.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojecthabits.R
import com.example.testprojecthabits.domain.modeles.Habit
import com.example.testprojecthabits.presentation.holders.HabitsViewHolder


class HabitsAdapter (private val editHabitListener: (Habit) -> Unit): RecyclerView.Adapter<HabitsViewHolder>(){

    private val habits: MutableList<Habit> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitsViewHolder {
        val view =LayoutInflater.from(parent.context).
                inflate(R.layout.item_habit, parent, false)
        return HabitsViewHolder(view, editHabitListener)
    }

    override fun onBindViewHolder(holder: HabitsViewHolder, position: Int) {
        holder.bindItem(habits[position])
    }

    override fun getItemCount(): Int {
        return habits.size
    }

    fun updateData(list: List<Habit>){
        habits.clear()
        habits.addAll(list)
        notifyDataSetChanged()
    }
}