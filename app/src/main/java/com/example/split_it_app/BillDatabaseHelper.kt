package com.example.split_it_app

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BillDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "bills.db"
        private const val DATABASE_VERSION = 2 // Increased version to apply ALTER TABLE

        private const val TABLE_NAME = "bills"
        private const val COLUMN_ID = "id"
        private const val COLUMN_BILL_TITLE = "bill_title"
        private const val COLUMN_BILL_AMOUNT = "bill_amount"
        private const val COLUMN_PEOPLE_COUNT = "people_count"
        private const val COLUMN_TIP_PERCENT = "tip_percent"
        private const val COLUMN_TOTAL_AMOUNT = "total_amount"
        private const val COLUMN_AMOUNT_PER_PERSON = "amount_per_person"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_BILL_TITLE TEXT,
                $COLUMN_BILL_AMOUNT REAL,
                $COLUMN_PEOPLE_COUNT INTEGER,
                $COLUMN_TIP_PERCENT REAL,
                $COLUMN_TOTAL_AMOUNT REAL,
                $COLUMN_AMOUNT_PER_PERSON REAL
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            db?.execSQL("ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_BILL_TITLE TEXT")
        }
    }

    fun insertBill(
        billTitle: String,
        billAmount: Double,
        peopleCount: Int,
        tipPercent: Double,
        totalAmount: Double,
        amountPerPerson: Double
    ) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_BILL_TITLE, billTitle)
            put(COLUMN_BILL_AMOUNT, billAmount)
            put(COLUMN_PEOPLE_COUNT, peopleCount)
            put(COLUMN_TIP_PERCENT, tipPercent)
            put(COLUMN_TOTAL_AMOUNT, totalAmount)
            put(COLUMN_AMOUNT_PER_PERSON, amountPerPerson)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllHistory(): List<BillHistory> {
        val historyList = mutableListOf<BillHistory>()
        val db = readableDatabase
        val cursor = db.query(TABLE_NAME, null, null, null, null, null, null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val billTitle = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_BILL_TITLE))
            val billAmount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_BILL_AMOUNT))
            val peopleCount = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PEOPLE_COUNT))
            val tipPercent = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TIP_PERCENT))
            val totalAmount = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_TOTAL_AMOUNT))
            val amountPerPerson = cursor.getDouble(cursor.getColumnIndexOrThrow(COLUMN_AMOUNT_PER_PERSON))

            historyList.add(
                BillHistory(
                    id, billTitle, billAmount, peopleCount, tipPercent, totalAmount, amountPerPerson
                )
            )
        }

        cursor.close()
        db.close()
        return historyList
    }

    // ✅ Included BillHistory data class here itself
    data class BillHistory(
        val id: Int,
        val billTitle: String,
        val billAmount: Double,
        val peopleCount: Int,
        val tipPercent: Double,
        val totalAmount: Double,
        val amountPerPerson: Double
    )
}
