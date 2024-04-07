package com.example.waybill

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.waybill.date.getDate
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val day = getDate()

        val listView = findViewById<ListView>(R.id.listView)
        val enterMil = findViewById<EditText>(R.id.enterMileage)
        val allFuel = findViewById<EditText>(R.id.inputAllFuel)
        val fuelCons = findViewById<EditText>(R.id.inputFuelCons)
        val btn = findViewById<Button>(R.id.button)




        btn.setOnClickListener{
            if (allFuel.editableText.toString() == "" ||
                enterMil.editableText.toString() == "" ||
                fuelCons.editableText.toString() == ""){
                Toast.makeText(this, "Не все поля заполнены!", Toast.LENGTH_SHORT).show()
            }
            else{
                var arrList = mutableListOf(
                    enterMil.getText().toString(), CountMil().consToDay(enterMil,fuelCons),day)

                allFuel.setText(CountMil().setInRemText(allFuel,enterMil,fuelCons))

                val dataList = mutableListOf<String>()
                dataList.add(arrList.joinToString(separator = "   "))

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
                listView.adapter = adapter

            }

        }












    }
}