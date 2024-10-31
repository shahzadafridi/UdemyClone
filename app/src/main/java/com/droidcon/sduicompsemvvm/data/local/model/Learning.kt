package com.droidcon.sduicompsemvvm.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Course::class,
            parentColumns = ["id"],
            childColumns = ["courseId"],
            onDelete = ForeignKey.CASCADE // Optional: Cascade delete
        )
    ]
)
data class Learning(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,  // Auto-increment primary key
    val courseId: Int,
    val sectionId: Int,
    val itemId: Int
)

data class LearningCourseItem(
    @Embedded val learning: Learning,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "id"
    )
    val course: Course
)