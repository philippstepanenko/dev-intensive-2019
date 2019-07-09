package ru.skillbranch.devintensive.extensions

fun String.truncate(count : Int? = 16) : String{
    var res = this.trim()
    return when (res.length > count!!){
        true -> "${res.substring(0,count!!).trim()}..."
        else -> res
    }
}

fun String.stripHtml(): String {
    val htmlRegexp = Regex("""<.+?>""")
    val escapeRegexp = Regex("""&(#\d+?|\w+?);""")
    val spaceRegexp = Regex("""\s{2,}""")
    return this.replace(htmlRegexp, "")
               .replace(escapeRegexp,"")
               .replace(spaceRegexp, " ")
}