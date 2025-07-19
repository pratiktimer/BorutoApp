package com.example.borutoapp.data.local

import androidx.room.TypeConverter
import com.example.borutoapp.domain.model.article.Source
import kotlinx.serialization.json.Json
import java.lang.StringBuilder

class DatabaseConverter {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>): String {
//        val stringBuilder = StringBuilder()
//        for (item in list) {
//            stringBuilder.append(item).append(separator)
//        }
//
//        stringBuilder.setLength(stringBuilder.length - separator.length)
//        return stringBuilder.toString()
        return list.joinToString(separator = separator)
    }

    @TypeConverter
    fun convertStringToList(string: String): List<String> {
        return string.split(separator)
    }
}
class Converters {
    @TypeConverter
    fun fromSource(source: Source): String {
        return Json.encodeToString(Source.serializer(), source)
    }

    @TypeConverter
    fun toSource(sourceString: String): Source {
        return Json.decodeFromString(Source.serializer(), sourceString)
    }
}