package ru.skillbranch.devintensive.utils

object Utils {

    val T = mapOf(
        "а" to "a",
        "б" to "b",
        "в" to "v",
        "г" to "g",
        "д" to "d",
        "е" to "e",
        "ё" to "e",
        "ж" to "zh",
        "з" to "z",
        "и" to "i",
        "й" to "i",
        "к" to "k",
        "л" to "l",
        "м" to "m",
        "н" to "n",
        "о" to "o",
        "п" to "p",
        "р" to "r",
        "с" to "s",
        "т" to "t",
        "у" to "u",
        "ф" to "f",
        "х" to "h",
        "ц" to "c",
        "ч" to "ch",
        "ш" to "sh",
        "щ" to "sh'",
        "ъ" to "",
        "ы" to "i",
        "ь" to "",
        "э" to "e",
        "ю" to "yu",
        "я" to "ya")

    fun parseFullName(fullName : String?) : Pair<String?,String?>{
        val parts : List<String>? = fullName?.split(" ")
        var firstName = parts?.getOrNull(0)
        var lastName = parts?.getOrNull(1)
        return MustShowString(firstName) to MustShowString(lastName)
    }

    fun MustShowString(s : String?) : String{
        when (!s.isNullOrEmpty()){
            true -> return s
            false -> return "null"
        }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        var res = ""

        for (char in payload){
            res += when (char.toString().toLowerCase()){
                in T -> T[char.toString().toLowerCase()]
                " " -> divider
                else -> char.toString()
            }

        }
        val parts : List<String>? = res.split(divider)
        return parts!!.get(0).capitalize() + divider + parts!!.get(1).capitalize()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var s = ""

        if (!firstName.isNullOrEmpty() && firstName!=" ") {
            s += firstName.substring(0,1).toUpperCase()
        }
        if (!lastName.isNullOrEmpty() && lastName!=" ") {
            s += lastName.substring(0,1).toUpperCase()
        }
        if (s === ""){
            s = "null"
        }
        return s
    }

}