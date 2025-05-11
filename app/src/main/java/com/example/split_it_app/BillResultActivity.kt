package com.example.split_it_app

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BillResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bill_result)

        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvBillAmount = findViewById<TextView>(R.id.tvBillAmount)
        val tvPeopleCount = findViewById<TextView>(R.id.tvPeopleCount)
        val tvTipPercent = findViewById<TextView>(R.id.tvTipPercent)
        val tvTotalAmount = findViewById<TextView>(R.id.tvTotalAmount)
        val tvAmountPerPerson = findViewById<TextView>(R.id.tvAmountPerPerson)

        val title = intent.getStringExtra("title")
        val billAmount = intent.getDoubleExtra("billAmount", 0.0)
        val peopleCount = intent.getIntExtra("peopleCount", 1)
        val tipPercent = intent.getDoubleExtra("tipPercent", 0.0)
        val totalAmount = intent.getDoubleExtra("totalAmount", 0.0)
        val amountPerPerson = intent.getDoubleExtra("amountPerPerson", 0.0)

        tvTitle.text = "Title: $title"
        tvBillAmount.text = "Bill Amount: ₹%.2f".format(billAmount)
        tvPeopleCount.text = "People: $peopleCount"
        tvTipPercent.text = "Tip: %.1f%%".format(tipPercent)
        tvTotalAmount.text = "Total Amount: ₹%.2f".format(totalAmount)
        tvAmountPerPerson.text = "Per Person: ₹%.2f".format(amountPerPerson)
    }
}
