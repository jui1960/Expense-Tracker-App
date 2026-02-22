package com.example.expensetracker

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ExpenseDao {
    @Insert
    fun insert(data: Data)

    @Update
    fun update(data: Data)

    @Delete
    fun delete(data: Data)

    @Query("SELECT * FROM expense_tracker ORDER BY id DESC")
    fun getAllData(): List<Data>


    @Query("SELECT SUM(amount) FROM expense_tracker")
    fun getTotalAmount(): Double
}

