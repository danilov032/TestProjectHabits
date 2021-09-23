package com.example.testprojecthabits.ui.modeles

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Habit (
    @PrimaryKey(autoGenerate = true) var  id: Int? = null,
    val name: String,
    val description: String,
    val priority: String,
    val type: String,
    val number: Short,
    val interval: Short,
    val color: String)