package com.droidcon.sduicompsemvvm.data.remote.repository

import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Learning
import com.droidcon.sduicompsemvvm.data.local.model.LearningCourseItem

interface LearningRepository {
    suspend fun insertLearningItem(learning: Learning): Long
    suspend fun deleteLearningItemByItemId(itemId: Int)
    suspend fun getLearningItemsByCourseIds(courseIds: List<Int>): Map<Int, List<LearningCourseItem>>
    suspend fun getLearningItemsBySection(sectionId: Int, courseId: Int): LearningCourseItem?
    suspend fun getLearningItemById(itemId: Int, sectionId: Int, courseId: Int): LearningCourseItem?
    suspend fun getAllLearningItems(): List<LearningCourseItem>
    suspend fun getEnrollCourses():  Result<List<Course>>
    suspend fun checkItemsExist(courseId: Int, sectionId: List<Int>,  itemIds: List<Int>): List<Learning>
}