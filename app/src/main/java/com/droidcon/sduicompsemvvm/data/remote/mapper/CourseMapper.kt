package com.droidcon.sduicompsemvvm.data.remote.mapper

import com.droidcon.sduicompsemvvm.data.remote.model.CourseDTO
import com.droidcon.sduicompsemvvm.data.remote.model.CurriculumDTO
import com.droidcon.sduicompsemvvm.data.remote.model.CurriculumItemDTO
import com.droidcon.sduicompsemvvm.data.remote.model.CurriculumSectionDTO
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Curriculum
import com.droidcon.sduicompsemvvm.data.local.model.CurriculumItem
import com.droidcon.sduicompsemvvm.data.local.model.CurriculumSection

class CourseMapper {

    fun toCourse(dto: CourseDTO): Course {
        return Course(
            id = dto.id,
            title = dto.title,
            description = dto.description,
            author = dto.author,
            rating = dto.rating,
            totalRatings = dto.totalRatings,
            totalStudents = dto.totalStudents,
            cost = dto.cost,
            type = dto.type,
            updatedAt = dto.updatedAt,
            language = dto.language,
            learns = dto.learns,
            curriculum = toCurriculum(dto.curriculum),
            requirements = dto.requirements,
            thumbnail = dto.thumbnail,
            isFree = dto.isFree
        )
    }

    private fun toCurriculum(dto: CurriculumDTO): Curriculum {
        return Curriculum(
            id = dto.id,
            time = dto.time,
            sections = dto.sections.map {toCurriculumSection(it) },
            lectures = dto.lectures
        )
    }

    private fun toCurriculumSection(dto: CurriculumSectionDTO): CurriculumSection {
        return CurriculumSection(
            id = dto.id,
            title = dto.title,
            items = dto.items.map { toCurriculumItem(it) }
        )
    }

    private fun toCurriculumItem(dto: CurriculumItemDTO): CurriculumItem {
        return CurriculumItem(
            id = dto.id,
            time = dto.time,
            title = dto.title
        )
    }


}