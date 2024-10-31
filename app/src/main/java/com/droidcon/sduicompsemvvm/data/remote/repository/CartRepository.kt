package com.droidcon.sduicompsemvvm.data.remote.repository

import com.droidcon.sduicompsemvvm.data.local.model.CartWithCourse
import com.droidcon.sduicompsemvvm.data.local.model.Course
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun addToCart(course: Course):  Pair<String,Boolean>
    suspend fun getCartCourse(courseId: Int): Course?
    suspend fun getCartCourses(): Flow<List<CartWithCourse>>
    suspend fun deleteCartCourse(courseId: Int)
}