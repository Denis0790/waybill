package com.example.waybill

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
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
                val dayFuelCons =
                    CountMil().consToDay(enterMil.text.toString(), fuelCons.text.toString())
                allFuel.setText(
                    CountMil().setInRemText(
                        allFuel.text.toString(),
                        enterMil.text.toString(),
                        fuelCons.text.toString()
                    )
                )

                val getDataForArr = GetDataForArr(
                    allFuel.text.toString(),
                    fuelCons.text.toString(),
                    enterMil.text.toString(),
                    day,

                    )
                dbHelper.addArr(getDataForArr)

                displayDataInListView()
            }
        }

        listView.setOnItemLongClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as String

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Удаление элемента")
            builder.setMessage("Вы уверены, что хотите удалить элемент \"$selectedItem\"?")


            builder.setPositiveButton("Да") { dialog, which ->
                val mileage = extractMileage(selectedItem)
                dbHelper.deleteItem(mileage)
                displayDataInListView()
                Toast.makeText(this, "Элемент \"$selectedItem\" удален", Toast.LENGTH_SHORT).show()
            }

            builder.setNegativeButton("Отмена") { dialog, which ->
                dialog.dismiss()
            }

            builder.show()

            true
        }



    }
    private fun extractMileage(selectedItem: String): String {
        return selectedItem.substringAfter("Пробег: ").substringBefore(" км.")
    }

    private fun displayDataInListView() {
        val dataList = dbHelper.getAllData()
        val adapter = ArrayAdapter<String>(this, R.layout.list_item_custom, dataList)

        listView.adapter = adapter
    }

    override fun onPause() {
        super.onPause()

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("allFuel", allFuel.text.toString())
        editor.putString("fuelCons", fuelCons.text.toString())
        editor.apply()
    }

    override fun onResume() {
        super.onResume()

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        allFuel.setText(sharedPreferences.getString("allFuel", ""))
        fuelCons.setText(sharedPreferences.getString("fuelCons", ""))

        displayDataInListView()
    }
}




