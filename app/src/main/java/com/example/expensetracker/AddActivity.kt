package com.example.expensetracker

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.expensetracker.databinding.ActivityAddBinding
import java.util.Calendar

class AddActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBinding
    private lateinit var db: AppDatabase
    private  var noteid  = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        //date picker
        val calendar = Calendar.getInstance()
        binding.btnDatePicker.setOnClickListener {
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->

                    val date = "$selectedDay/${selectedMonth + 1}/$selectedYear"

                    binding.tvSelectedDate.text = date
                    binding.tvSelectedDate.setTextColor(resources.getColor(R.color.black, theme))
                },
                year, month, day
            )

            datePickerDialog.show()
        }



        db = AppDatabase.getDatabase(this)

        noteid = intent.getIntExtra("id",-1)

        if(noteid != -1){
            binding.etTitle.setText(intent.getStringExtra("title"))
            binding.etAmount.setText(intent.getDoubleExtra("amount",0.0).toString())
            binding.tvSelectedDate.text = intent.getStringExtra("date")
            binding.btnSave.text = "UPDATE TRANSACTION"
            binding.addtitle.text = "EDIT EXPENSE"
        }


        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val amount = binding.etAmount.text.toString().toDouble()
            val date = binding.tvSelectedDate.text.toString()

            if(noteid==-1) {
                val expenseData = Data(title = title, amount = amount, date = date)
                db.expenseDao().insert(expenseData)
            }
            else{
                val expensedata = Data(id = noteid,title = title,amount=amount, date = date)
                db.expenseDao().update(expensedata)
            }
            Toast.makeText(
                this@AddActivity,"data save successfully",
                Toast.LENGTH_SHORT
            ).show()
            finish()

        }

    }
}

