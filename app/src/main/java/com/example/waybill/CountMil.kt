package com.example.waybill

import android.widget.EditText

class CountMil {
    fun setInRemText(allFuel: EditText,enterMil: EditText, fuelCons: EditText ): String{
        val res = enterMil.getText().toString().toDouble() *
                fuelCons.getText().toString().toDouble()/100
        return (allFuel.getText().toString().toDouble() - res).toString()
    }

    fun consToDay(enterMil: EditText, fuelCons: EditText): String{
        return (enterMil.getText().toString().toDouble() *
                fuelCons.getText().toString().toDouble()/100).toString()
    }
}