package com.droidcon.sduicompsemvvm.data.remote.repository

import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Enroll

interface HomeRepository {
    suspend fun getCourses(): Result<List<Course>>
    suspend fun enrollInCourse(course: Course): Pair<String, Boolean>
    suspend fun IsEnrollInCourse(courseId: Int): Enroll?
}