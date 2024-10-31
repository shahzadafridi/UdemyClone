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
data class Enroll(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,  // Auto-increment primary key
    val courseId: Int,
)

data class EnrollCourse(
    @Embedded val enroll: Enroll,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "id"
    )
    val course: Course
)