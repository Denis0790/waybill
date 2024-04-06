package com.example.waybill.date

import android.icu.util.Calendar

fun getDate():String {
    val calendar: Calendar = Calendar.getInstance()
    val month = calendar.get(Calendar.MONTH)+1
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    val monthName = GetNameMonth().getNameMonth(month)
    return "$dayOfMonth $monthName"
}