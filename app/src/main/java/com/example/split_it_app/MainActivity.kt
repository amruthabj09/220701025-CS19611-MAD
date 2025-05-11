//package com.example.split_it_app
//
//import android.content.Intent
//import android.os.Bundle
//import android.widget.*
//import androidx.appcompat.app.AppCompatActivity
//
//class MainActivity : AppCompatActivity() {
//
//    private lateinit var editBillAmount: EditText
//    private lateinit var editPeopleCount: EditText
//    private lateinit var editTipPercent: EditText
//    private lateinit var btnCalculate: Button
//    private lateinit var btnHistory: Button
//    private lateinit var textResult: TextView
//    private lateinit var dbHelper: BillDatabaseHelper
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        editBillAmount = findViewById(R.id.editBillAmount)
//        editPeopleCount = findViewById(R.id.editPeopleCount)
//        editTipPercent = findViewById(R.id.editTipPercent)
//        btnCalculate = findViewById(R.id.btnCalculate)
//        btnHistory = findViewById(R.id.btnHistory)   // <-- new button for history
//        textResult = findViewById(R.id.textResult)
//
//        dbHelper = BillDatabaseHelper(this)
//
//        btnCalculate.setOnClickListener {
//            calculateSplit()
//        }
//
//        btnHistory.setOnClickListener {
//            val intent = Intent(this, HistoryActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
//    private fun calculateSplit() {
//        val billAmount = editBillAmount.text.toString().toDoubleOrNull()
//        val peopleCount = editPeopleCount.text.toString().toIntOrNull()
//        val tipPercent = editTipPercent.text.toString().toDoubleOrNull()
//
//        if (billAmount == null || peopleCount == null || tipPercent == null || peopleCount == 0) {
//            textResult.text = "Please enter valid inputs."
//            return
//        }
//
//        val tipAmount = billAmount * (tipPercent / 100)
//        val totalAmount = billAmount + tipAmount
//        val amountPerPerson = totalAmount / peopleCount
//
//        textResult.text = "Total with Tip: ₹%.2f\nPer Person: ₹%.2f".format(totalAmount, amountPerPerson)
//
//        // Save to database
//        dbHelper.insertBill(billAmount, peopleCount, tipPercent, totalAmount, amountPerPerson)
//    }
//}
package com.example.split_it_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getStartedButton = findViewById<Button>(R.id.btnGetStarted)
        getStartedButton.setOnClickListener {
            val intent = Intent(this, BillCalculatorActivity::class.java)
            startActivity(intent)
        }
    }
}

