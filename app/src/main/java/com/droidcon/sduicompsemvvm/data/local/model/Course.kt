package com.droidcon.sduicompsemvvm.data.local.model

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Course(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val author: String,
    val rating: Float,
    val totalRatings: Int,
    val totalStudents: Int,
    val cost: Float,
    val type: String,
    val updatedAt: String,
    val language: String,
    val learns: List<String>,
    val curriculum: Curriculum,
    val requirements: List<String>,
    val thumbnail: String,
    val isFree: Boolean
): Parcelable

@Parcelize
data class Curriculum(
    val id: Int,
    val lectures: Int,
    val time: String,
    val sections: List<CurriculumSection>
): Parcelable

@Parcelize
data class CurriculumSection(
    val id: Int,
    val title: String,
    val items: List<CurriculumItem>
): Parcelable

@Parcelize
data class CurriculumItem(
    val id: Int,
    val title: String,
    val time: String,
): Parcelable


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
data class Cart(
    @PrimaryKey(autoGenerate = true) val id: Int? = 0,
    val courseId: Int?
)

data class CartWithCourse(
    @Embedded val cart: Cart,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "id"
    )
    val course: Course
)