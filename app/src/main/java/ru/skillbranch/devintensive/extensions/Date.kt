package ru.skillbranch.devintensive.extensions

import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy") : String{
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date{
    var time = this.time
    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()) : String {
    val diff = date.time - this.time
    val absDiff = abs(diff)
    val isPast = diff > 0

    return when{
        absDiff / SECOND <= 1  -> "только что"
        absDiff / SECOND <= 45 -> if (isPast) "несколько секунд назад"
                                  else "через несколько секунд"
        absDiff / SECOND <= 75 -> if (isPast) "минуту назад"
                                  else "через минуту"
        absDiff / MINUTE <= 45 -> if (isPast) "${TimeUnits.MINUTE.plural((absDiff/MINUTE).toInt())} назад"
                                  else "через ${TimeUnits.MINUTE.plural((absDiff/MINUTE).toInt())}"
        absDiff / MINUTE <= 75 -> if (isPast) "час назад"
                                  else "через час"
        absDiff / HOUR   <= 22 -> if (isPast) "${TimeUnits.HOUR.plural((absDiff/HOUR).toInt())} назад"
                                  else "через ${TimeUnits.HOUR.plural((absDiff/HOUR).toInt())}"
        absDiff / MINUTE <= 26 -> if (isPast) "день назад"
                                  else "через день"
        absDiff / DAY   <= 360 -> if (isPast) "${TimeUnits.DAY.plural((absDiff/DAY).toInt())} назад"
                                  else "через ${TimeUnits.DAY.plural((absDiff/DAY).toInt())}"
        else                   -> if (isPast) "более года назад"
                                  else "более чем через год"
    }
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int) : String{
        val plurals = mapOf(
            SECOND to arrayListOf<String>("секунду", "секунды", "секунд"),
            MINUTE to arrayListOf<String>("минуту", "минуты", "минут"),
            HOUR to arrayListOf<String>("час", "часа", "часов"),
            DAY to arrayListOf<String>("день", "дня", "дней")
        )
        return when{
            (value % 100) > 10 &&
            (value % 100) < 20    -> "$value ${plurals[this]?.get(2)!!}"
            (value % 10) == 1     -> "$value ${plurals[this]?.get(0)!!}"
            (value % 10) in 2..4  -> "$value ${plurals[this]?.get(1)!!}"
            else                  -> "$value ${plurals[this]?.get(2)!!}"
        }
    }
}

