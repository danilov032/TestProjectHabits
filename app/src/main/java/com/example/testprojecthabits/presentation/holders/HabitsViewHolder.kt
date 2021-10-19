package com.example.testprojecthabits.presentation.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojecthabits.data.entity_model.EntityHabit
import com.example.testprojecthabits.domain.modeles.Habit
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