package com.example.waybill

import android.widget.EditText
import android.widget.Toast

class CountMil {
    fun setInRemText(allFuel: String,enterMil: String, fuelCons: String ): String{
            val res = enterMil.toDouble() *
                    fuelCons.toDouble()/100
            return (allFuel.toDouble() - res).toString()
    }

    fun consToDay(enterMil: String, fuelCons: String): String{
        return (enterMil.toDouble() *
                fuelCons.toDouble()/100).toString()
    }
}