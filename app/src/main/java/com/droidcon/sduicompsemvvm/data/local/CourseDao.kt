package com.droidcon.sduicompsemvvm.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.droidcon.sduicompsemvvm.data.local.model.Cart
import com.droidcon.sduicompsemvvm.data.local.model.CartWithCourse
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Enroll
import com.droidcon.sduicompsemvvm.data.local.model.EnrollCourse
import com.droidcon.sduicompsemvvm.data.local.model.Learning
import com.droidcon.sduicompsemvvm.data.local.model.LearningCourseItem
import kotlinx.coroutines.flow.Flow


@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: Course): Long

    @Query("SELECT * FROM Course WHERE id = :courseId")
    suspend fun getCourse(courseId: Int): Course?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnroll(item: Enroll): Long

    @Query("SELECT * FROM Enroll WHERE courseId = :courseId")
    suspend fun isEnrollInCourse(courseId: Int): Enroll?

    @Query("SELECT * FROM Enroll")
    suspend fun getAllEnrolledCourses(): List<EnrollCourse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourseToCart(item: Cart): Long

    @Query("SELECT * FROM Cart WHERE courseId = :courseId")
    suspend fun isCourseInCart(courseId: Int): Cart?

    @Query("SELECT * FROM Cart WHERE courseId = :courseId")
    suspend fun getCartWithCourse(courseId: Int): CartWithCourse?

    @Transaction
    @Query("SELECT * FROM Cart")
    fun getAllCartCourses(): Flow<List<CartWithCourse>>

    @Query("DELETE FROM Cart WHERE courseId = :courseId")
    suspend fun deleteCourseFromCartById(courseId: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLearning(learning: Learning): Long

    @Query("DELETE FROM Learning WHERE itemId = :itemId")
    suspend fun deleteLearningItemByCourseId(itemId: Int)

    @Query("SELECT * FROM Learning WHERE courseId IN (:courseIds)")
    suspend fun getLearningCourseItemsByCourse(courseIds: List<Int>): List<LearningCourseItem>

    @Query("SELECT * FROM Learning WHERE sectionId = :sectionId AND courseId = :courseId")
    suspend fun getLearningCourseItemsBySection(sectionId: Int, courseId: Int): LearningCourseItem?

    @Query("SELECT * FROM Learning WHERE itemId = :itemId AND sectionId = :sectionId AND courseId = :courseId")
    suspend fun getLearningCourseItemsByItem(itemId: Int, sectionId: Int, courseId: Int): LearningCourseItem?

    @Query("SELECT * FROM Learning")
    suspend fun getLearningCourseItems(): List<LearningCourseItem>

    @Query("SELECT * FROM Learning WHERE courseId = :courseId AND sectionId IN (:sectionIds) AND itemId IN (:itemIds)")
    suspend fun getExistingItemIds(courseId: Int, sectionIds: List<Int>, itemIds: List<Int>): List<Learning>

}