package com.example.expensetracker

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "expense_tracker")
data class Data(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val date: String,
    val amount: Double
)
