package com.example.waybill.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.waybill.GetDataForArr


class MyDatabaseHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "MyDatabase.db", factory, 1) {

    private val dataList = ArrayList<String>()

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE IF NOT EXISTS my_table (id INTEGER PRIMARY KEY, allFuel TEXT," +
                " fuelCons TEXT, mileage TEXT, date TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS my_table")
        onCreate(db)
    }

    fun addArr(getDataForArr: GetDataForArr) {
        val values = ContentValues().apply {
            put("allFuel", getDataForArr.allFuel)
            put("fuelCons", getDataForArr.fuelCons)
            put("mileage", getDataForArr.mileage)
            put("date", getDataForArr.day)
        }

        val db = this.writableDatabase
        db.insert("my_table", null, values)
        db.close()
    }

    @SuppressLint("Range")
    fun getAllData(): ArrayList<String> {
        dataList.clear()
        val db = this.readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM my_table", null)
        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val mileage = it.getString(it.getColumnIndex("mileage"))
                    val date = it.getString(it.getColumnIndex("date"))
                    dataList.add("Пробег: $mileage км  -  $date")
                } while (it.moveToNext())
            }
        }
        cursor?.close()
        db.close()
        return dataList
    }

    fun deleteItem(mileage: String) {
        val db = this.writableDatabase
        db.delete("my_table", "mileage=?", arrayOf(mileage))
        db.close()

        dataList.clear()
        dataList.addAll(getAllData())
    }
}

