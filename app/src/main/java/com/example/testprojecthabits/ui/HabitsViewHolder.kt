package com.example.testprojecthabits.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojecthabits.ui.modeles.Habit
import kotlinx.android.synthetic.main.item_habit.view.*

class HabitsViewHolder(
    private val view: View,
    private val editHabitListener: (Habit) -> Unit) : RecyclerView.ViewHolder(view) {

    fun bindItem(habit: Habit) {
        view.tv_name.text = habit.name
        view.tv_description.text = habit.description
        view.tv_priority.text = "Преоритет: " + habit.priority
        view.tv_type.text = "Тип привычки: " + habit.type
        view.tv_interval.text = "Нужно выполнять " + habit.number + " раз за " + habit.interval + "дней"


        view.btn_edit_text.setOnClickListener { editHabitListener(habit) }
    }
}