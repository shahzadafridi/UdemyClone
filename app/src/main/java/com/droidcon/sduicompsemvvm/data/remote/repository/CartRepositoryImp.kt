package com.droidcon.sduicompsemvvm.data.remote.repository


import com.droidcon.sduicompsemvvm.data.local.CourseDao
import com.droidcon.sduicompsemvvm.data.local.model.Cart
import com.droidcon.sduicompsemvvm.data.local.model.CartWithCourse
import com.droidcon.sduicompsemvvm.data.local.model.Course
import kotlinx.coroutines.flow.Flow

class CartRepositoryImp(
    private val courseDao: CourseDao
) : CartRepository {

    override suspend fun addToCart(course: Course): Pair<String, Boolean> {
        val isCourseNew = courseDao.getCourse(course.id) == null
        if (isCourseNew) courseDao.insertCourse(course)

        return if (courseDao.isCourseInCart(course.id) == null) {
            courseDao.insertCourseToCart(Cart(courseId = course.id))
            "Successfully added to cart" to true
        } else {
            "Failed add to cart" to false
        }
    }

    override suspend fun getCartCourse(courseId: Int): Course? {
        return courseDao.getCartWithCourse(courseId)?.course
    }

    override suspend fun getCartCourses(): Flow<List<CartWithCourse>> {
        return courseDao.getAllCartCourses()
    }

    override suspend fun deleteCartCourse(courseId: Int) {
        courseDao.deleteCourseFromCartById(courseId)
    }
}
