package com.example.waybill.date

class GetNameMonth() {
    fun getNameMonth(month: Int): String{
        when{
            month == 1 -> return "января"
            month == 2 -> return "февраля"
            month == 3 -> return "марта"
            month == 4 -> return "апреля"
            month == 5 -> return "мая"
            month == 6 -> return "июня"
            month == 7 -> return "июля"
            month == 8 -> return "августа"
            month == 9 -> return "сентября"
            month == 10 -> return "октября"
            month == 11-> return "ноября"
            else -> return "декабря"
        }
    }
}