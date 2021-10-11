package com.example.testprojecthabits.domain.modeles

import java.io.Serializable

data class HabitModel(
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val priority: String = "",
    val type: String = "",
    val number: Short = 0,
    val interval: Short = 0,
    val color: String = ""
) : Serializable