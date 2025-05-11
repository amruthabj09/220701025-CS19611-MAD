package com.example.split_it_app

import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var listViewHistory: ListView
    private lateinit var noHistoryTextView: TextView
    private lateinit var dbHelper: BillDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        listViewHistory = findViewById(R.id.listViewHistory)
        noHistoryTextView = findViewById(R.id.noHistoryTextView)
        dbHelper = BillDatabaseHelper(this)

        // Retrieve all history from the database
        val historyList = dbHelper.getAllHistory()

        if (historyList.isEmpty()) {
            noHistoryTextView.visibility = View.VISIBLE
            listViewHistory.visibility = View.GONE
        } else {
            noHistoryTextView.visibility = View.GONE
            listViewHistory.visibility = View.VISIBLE

            // Set up the adapter to populate the ListView
            val adapter = HistoryAdapter(this, historyList)
            listViewHistory.adapter = adapter
        }
    }
}
