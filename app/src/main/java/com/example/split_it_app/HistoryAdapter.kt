package com.example.split_it_app

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HistoryAdapter(context: Context, private val bills: List<BillDatabaseHelper.BillHistory>) :
    ArrayAdapter<BillDatabaseHelper.BillHistory>(context, 0, bills) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_item_bill, parent, false)

        val bill = bills[position]

        // Bind data to views
        val billTitleText = view.findViewById<TextView>(R.id.textViewBillTitle)
        val billSubText = view.findViewById<TextView>(R.id.textViewBillSub)
        val billAmountText = view.findViewById<TextView>(R.id.textViewBillAmount)

        billTitleText.text = bill.billTitle
        billSubText.text = "Split among ${bill.peopleCount} people - ₹${bill.totalAmount} total"
        billAmountText.text = "₹${bill.amountPerPerson}"

        return view
    }
}
