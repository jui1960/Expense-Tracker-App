package com.example.expensetracker

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.expensetracker.databinding.ActivityHomeBinding
import com.example.expensetracker.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }
        db = AppDatabase.getDatabase(this)
        loadData()
        binding.rvTransactions.layoutManager = LinearLayoutManager(this)


    }

    override fun onResume() {
        super.onResume()
        loadData()
        totalAmount()


    }

    private fun totalAmount() {
        val total = db.expenseDao().getTotalAmount() ?: 0.0
        binding.tvTotalBalance.text = "$ ${"%.2f".format(total)}"
    }

    private fun loadData() {
        val list = db.expenseDao().getAllData()
        val adapter = ExpenseAdapter(
            list,
            onEdit = { data ->
                val intent = Intent(this@HomeActivity, AddActivity::class.java)
                intent.putExtra("id", data.id)

                intent.putExtra("title", data.title)
                intent.putExtra("amount", data.amount)
                intent.putExtra("date", data.date)
                startActivity(intent)
            },
            onDelete = { data ->
                db.expenseDao().delete(data)
                loadData()
                totalAmount()
            }
        )
        binding.rvTransactions.adapter = adapter

    }
}