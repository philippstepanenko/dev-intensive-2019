package ru.skillbranch.devintensive.utils

object Utils {

    fun parseFullName(fullName : String?) : Pair<String?,String?>{
        val parts : List<String>? = fullName?.trim()?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        return MustShowString(firstName) to MustShowString(lastName)
    }

    private fun MustShowString(s : String?) : String?{
        return when (!s.isNullOrEmpty()){
            true -> s
            false -> null
        }
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var s : String? = ""

        if (!firstName.isNullOrEmpty() && firstName?.trim() != "") {
            s += "${firstName.trim()}"[0].toUpperCase()
        }
        if (!lastName.isNullOrEmpty() && lastName?.trim() != "") {
            s += "${lastName.trim()}"[0].toUpperCase()
        }
        if (s === ""){
            s = null
        }
        return s
    }


    fun transliteration(payload: String, divider: String = " ") = payload.map {
        val res = when (it.toLowerCase()) {
            'а' -> "a"
            'б' -> "b"
            'в' -> "v"
            'г' -> "g"
            'д' -> "d"
            'е', 'ё', 'э' -> "e"
            'ж' -> "zh"
            'з' -> "z"
            'и', 'й', 'ы' -> "i"
            'к' -> "k"
            'л' -> "l"
            'м' -> "m"
            'н' -> "n"
            'о' -> "o"
            'п' -> "p"
            'р' -> "r"
            'с' -> "s"
            'т' -> "t"
            'у' -> "u"
            'ф' -> "f"
            'х' -> "h"
            'ц' -> "c"
            'ч' -> "ch"
            'ш' -> "sh"
            'щ' -> "sh'"
            'ъ', 'ь' -> ""
            'ю' -> "yu"
            'я' -> "ya"
            ' ' -> divider
            else -> "$it"
        }
        if (it.isUpperCase()) res.capitalize() else res
    }.joinToString("")

}