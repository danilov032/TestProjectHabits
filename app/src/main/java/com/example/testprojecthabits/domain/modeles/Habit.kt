package com.example.testprojecthabits.domain.modeles

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

class Habit (
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val priority: String = "",
    val type: String = "",
    val number: Short = 0,
    val interval: Short = 0,
    val color: String = "") : Serializable