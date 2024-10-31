package com.droidcon.sduicompsemvvm.data.remote.repository

import com.droidcon.sduicompsemvvm.data.FakeData
import com.droidcon.sduicompsemvvm.data.local.CourseDao
import com.droidcon.sduicompsemvvm.data.remote.mapper.CourseMapper
import com.droidcon.sduicompsemvvm.data.remote.service.CourseService
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Enroll

class HomeRepositoryImp(
    private val courseService: CourseService,
    private val courseMapper: CourseMapper,
    private val courseDao: CourseDao
) : HomeRepository {


    override suspend fun getCourses() = kotlin.runCatching {
        //val courses = runOnIO { courseService.getCourses() }
        FakeData.courses
    }

    override suspend fun enrollInCourse(course: Course): Pair<String, Boolean> {
        val isCourseNew = courseDao.getCourse(course.id) == null
        if (isCourseNew) courseDao.insertCourse(course)
        val id = courseDao.insertEnroll(Enroll(courseId = course.id))
        return if (id > 0) {
            "Successfully enrolled in course" to true
        } else {
            "Failed to enroll in course" to false
        }

    }

    override suspend fun IsEnrollInCourse(courseId: Int): Enroll? {
        if (courseDao.getCourse(courseId) == null) {
            return null
        }
        return courseDao.isEnrollInCourse(courseId)
    }

}

