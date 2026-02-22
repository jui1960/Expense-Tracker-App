package com.example.expensetracker

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.expensetracker.databinding.ActivityItemDetailsBinding
import com.example.expensetracker.databinding.ActivityMainBinding

class ItemDetails : AppCompatActivity() {
    private lateinit var binding: ActivityItemDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getIntExtra("id",-1)
        val title = intent.getStringExtra("title")
        val amount = intent.getDoubleExtra("amount",0.0)
        val date = intent.getStringExtra("date")


        binding.tvDetailTitle.text = title
        binding.tvDetailAmount.text = "$ ${"%.2f".format(amount)}"
        binding.tvDetailDate.text = "Date : $date"


        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}