package com.example.testprojecthabits.ui.modeles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Habit (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    val name: String = "",
    val description: String = "",
    val priority: String = "",
    val type: String = "",
    val number: Short = 0,
    val interval: Short = 0,
    val color: String = "")