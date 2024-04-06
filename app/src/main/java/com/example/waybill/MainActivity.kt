package com.example.waybill

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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


        val itemList: RecyclerView = findViewById(R.id.itemList)
        val enterMil = findViewById<EditText>(R.id.enterMileage)
        val btn = findViewById<Button>(R.id.button)












    }
}