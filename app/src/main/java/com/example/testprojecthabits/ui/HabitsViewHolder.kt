package com.example.testprojecthabits.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojecthabits.ui.modeles.Habit
import kotlinx.android.synthetic.main.item_habit.view.*

class HabitsViewHolder(private val view: View): RecyclerView.ViewHolder(view) {

    fun bindItem(habit: Habit){
       view.tv_name.text = "Жопа"
    }
}