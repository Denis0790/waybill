package com.example.waybill

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.waybill.data.MyDatabaseHelper
import com.example.waybill.date.getDate

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: MyDatabaseHelper
    private lateinit var listView: ListView
    private lateinit var enterMil: EditText
    private lateinit var allFuel: EditText
    private lateinit var fuelCons: EditText
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = MyDatabaseHelper(this, null)
        listView = findViewById(R.id.listView)
        enterMil = findViewById(R.id.enterMileage)
        allFuel = findViewById(R.id.inputAllFuel)
        fuelCons = findViewById(R.id.inputFuelCons)
        btn = findViewById(R.id.button)

        btn.setOnClickListener {
            if (allFuel.text.isEmpty() || enterMil.text.isEmpty() || fuelCons.text.isEmpty()) {
                Toast.makeText(this, "Не все поля заполнены!", Toast.LENGTH_SHORT).show()
            } else {
                val day = getDate()
                val dayFuelCons = CountMil().consToDay(enterMil.text.toString(), fuelCons.text.toString())
                allFuel.setText(CountMil().setInRemText(allFuel.text.toString(), enterMil.text.toString(), fuelCons.text.toString()))

                val getDataForArr = GetDataForArr(
                    allFuel.text.toString(),
                    fuelCons.text.toString(),
                    enterMil.text.toString(),
                    dayFuelCons,
                    day
                )
                dbHelper.addArr(getDataForArr)

                displayDataInListView()
            }
        }
    }

    private fun displayDataInListView() {
        val dataList = dbHelper.getAllData()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        listView.adapter = adapter
    }

}
