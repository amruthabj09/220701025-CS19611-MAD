package com.example.split_it_app

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class BillCalculatorActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etAmount: EditText
    private lateinit var etPeople: EditText
    private lateinit var etTip: EditText
    private lateinit var btnCalculate: Button
    private lateinit var btnHistory: Button
    private lateinit var dbHelper: BillDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_calculator)

        etTitle = findViewById(R.id.etTitle)
        etAmount = findViewById(R.id.etAmount)
        etPeople = findViewById(R.id.etPeople)
        etTip = findViewById(R.id.etTip)
        btnCalculate = findViewById(R.id.btnCalculate)
        btnHistory = findViewById(R.id.btnHistory)

        dbHelper = BillDatabaseHelper(this)

        btnCalculate.setOnClickListener {
            calculateAndNavigate()
        }

        btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun calculateAndNavigate() {
        val title = etTitle.text.toString()
        val amountStr = etAmount.text.toString()
        val peopleStr = etPeople.text.toString()
        val tipStr = etTip.text.toString()

        if (title.isBlank() || amountStr.isBlank() || peopleStr.isBlank() || tipStr.isBlank()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val billAmount = amountStr.toDoubleOrNull()
        val peopleCount = peopleStr.toIntOrNull()
        val tipPercent = tipStr.toDoubleOrNull()

        if (billAmount == null || peopleCount == null || tipPercent == null || peopleCount == 0) {
            Toast.makeText(this, "Invalid input values", Toast.LENGTH_SHORT).show()
            return
        }

        val tipAmount = billAmount * (tipPercent / 100)
        val totalAmount = billAmount + tipAmount
        val amountPerPerson = totalAmount / peopleCount

        // Insert into DB
        dbHelper.insertBill(title, billAmount, peopleCount, tipPercent, totalAmount, amountPerPerson)

        // Navigate to result screen
        val intent = Intent(this, BillResultActivity::class.java).apply {
            putExtra("title", title)
            putExtra("billAmount", billAmount)
            putExtra("peopleCount", peopleCount)
            putExtra("tipPercent", tipPercent)
            putExtra("totalAmount", totalAmount)
            putExtra("amountPerPerson", amountPerPerson)
        }
        startActivity(intent)
    }
}
