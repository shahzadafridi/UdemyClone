package com.droidcon.sduicompsemvvm.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.droidcon.sduicompsemvvm.data.local.model.Cart
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Enroll
import com.droidcon.sduicompsemvvm.data.local.model.Learning

@Database(entities = [Course::class, Enroll::class, Cart::class, Learning::class], version = 2)
@TypeConverters(Converters::class, CurriculumConverters::class, CurriculumSectionConverters::class)  // Register the converter here
abstract class AppDatabase : RoomDatabase() {
    abstract fun courseDao(): CourseDao
}