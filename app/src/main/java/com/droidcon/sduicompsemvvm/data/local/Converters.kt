package com.droidcon.sduicompsemvvm.data.local

import androidx.room.TypeConverter
import com.droidcon.sduicompsemvvm.data.local.model.Curriculum
import com.droidcon.sduicompsemvvm.data.local.model.CurriculumSection
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromStringList(value: List<String>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String?): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }
}

class CurriculumConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromCurriculumSectionList(sections: Curriculum): String {
        return gson.toJson(sections)
    }

    @TypeConverter
    fun toCurriculumSectionList(data: String): Curriculum {
        return gson.fromJson(data, Curriculum::class.java)
    }
}

class CurriculumSectionConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromCurriculumSectionList(sections: List<CurriculumSection>): String {
        return gson.toJson(sections)
    }

    @TypeConverter
    fun toCurriculumSectionList(data: String): List<CurriculumSection> {
        val listType = object : TypeToken<List<CurriculumSection>>() {}.type
        return gson.fromJson(data, listType)
    }
}