package com.example.testprojecthabits.ui.modeles

data class HabitsModel(
    val name: String,
    val description: String,
    val priority: String,
    val type: String,
    val number: Short,
    val interval: Short
)