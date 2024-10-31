package com.droidcon.sduicompsemvvm.data.remote.repository

import com.droidcon.sduicompsemvvm.data.local.CourseDao
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Learning
import com.droidcon.sduicompsemvvm.data.local.model.LearningCourseItem

class LearningRepositoryImp(
    private val courseDao: CourseDao
) : LearningRepository {

    override suspend fun insertLearningItem(learning: Learning): Long {
        if (getLearningItemById(itemId = learning.itemId, sectionId = learning.sectionId, courseId = learning.courseId) == null) {
            return courseDao.insertLearning(learning)
        }
        return -1
    }

    override suspend fun deleteLearningItemByItemId(itemId: Int) {
        courseDao.deleteLearningItemByCourseId(itemId)
    }

    override suspend fun getLearningItemsByCourseIds(courseIds: List<Int>): Map<Int, List<LearningCourseItem>> {
        val items = courseDao.getLearningCourseItemsByCourse(courseIds)
        return items.groupBy { it.course.id }
    }

    override suspend fun getLearningItemsBySection(
        sectionId: Int,
        courseId: Int
    ): LearningCourseItem? {
        return courseDao.getLearningCourseItemsBySection(sectionId, courseId)
    }

    override suspend fun getLearningItemById(
        itemId: Int,
        sectionId: Int,
        courseId: Int
    ): LearningCourseItem? {
        return courseDao.getLearningCourseItemsByItem(itemId, sectionId, courseId)
    }

    override suspend fun getAllLearningItems(): List<LearningCourseItem> {
        return courseDao.getLearningCourseItems()
    }

    override suspend fun getEnrollCourses(): Result<List<Course>> {
        return kotlin.runCatching { courseDao.getAllEnrolledCourses().map { it.course } }
    }

    override suspend fun checkItemsExist(courseId: Int, sectionId: List<Int>, itemIds: List<Int>): List<Learning> {
        val existingItemIds = courseDao.getExistingItemIds(courseId, sectionId, itemIds)
        return existingItemIds
    }

}