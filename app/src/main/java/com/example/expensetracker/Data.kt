package com.example.expensetracker

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "expense_tracker")
data class Data(
    @PrimaryKey(autoGenerate = true)
    private val id : Int = 0,
    private val title : String,
    private val date : String,
    private val amount : Double
)
