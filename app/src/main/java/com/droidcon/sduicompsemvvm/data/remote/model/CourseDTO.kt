package com.droidcon.sduicompsemvvm.data.remote.model

data class CourseDTO(
    val id: Int,
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
    val curriculum: CurriculumDTO,
    val requirements: List<String>,
    val thumbnail: String,
    val isFree: Boolean,
)

data class CurriculumDTO(
    val id: Int,
    val lectures: Int,
    val time: String,
    val sections: List<CurriculumSectionDTO>
)

data class CurriculumSectionDTO(
    val id: Int,
    val title: String,
    val items: List<CurriculumItemDTO>
)

data class CurriculumItemDTO(
    val id: Int,
    val title: String,
    val time: String,
)