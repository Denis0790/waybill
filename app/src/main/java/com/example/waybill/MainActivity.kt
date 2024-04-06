package com.example.waybill

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import com.example.waybill.date.getDate
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.logging.Logger.global


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val day = getDate()

        val listView = findViewById<ListView>(R.id.listView)
        val enterMil = findViewById<EditText>(R.id.enterMileage)
        val allFuel = findViewById<EditText>(R.id.inputAllFuel)
        val fuelCons = findViewById<EditText>(R.id.inputFuelCons)
        val remFuel = findViewById<EditText>(R.id.inputRemFuel)
        val btn = findViewById<Button>(R.id.button)

        btn.setOnClickListener{
            if (allFuel.editableText.toString() == "" ||
                enterMil.editableText.toString() == "" ||
                fuelCons.editableText.toString() == ""){
                Toast.makeText(this, "Не все поля заполнены!", Toast.LENGTH_SHORT).show()
            }
            else{
                remFuel.setText(CountMil().setInRemText(allFuel,enterMil,fuelCons))
            }

        }












    }
}