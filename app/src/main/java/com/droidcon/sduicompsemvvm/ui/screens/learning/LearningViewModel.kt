package com.droidcon.sduicompsemvvm.ui.screens.learning

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Learning
import com.droidcon.sduicompsemvvm.data.local.model.LearningCourseItem
import com.droidcon.sduicompsemvvm.data.remote.repository.LearningRepository
import com.droidcon.sduicompsemvvm.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LearningViewModel @Inject constructor(
    private val learningRepository: LearningRepository
) : ViewModel() {

    private val _enrollCoursesStateFlow = MutableStateFlow<UiState<List<Course>>>(UiState.Loading)
    val enrollCoursesStateFlow: StateFlow<UiState<List<Course>>> = _enrollCoursesStateFlow

    private val _learningItemsStateFlow = MutableStateFlow<Map<Int, List<LearningCourseItem>>>(mapOf())
    val learningItemsStateFlow: StateFlow<Map<Int, List<LearningCourseItem>> > = _learningItemsStateFlow

    private val _learningItemStateFlow = MutableStateFlow<UiState<LearningCourseItem?>>(UiState.Loading)
    val learningItemStateFlow: StateFlow<UiState<LearningCourseItem?>> = _learningItemStateFlow

    private val _itemsExistenceStateFlow = MutableStateFlow<List<Learning>>(arrayListOf())
    val itemsExistenceStateFlow: StateFlow<List<Learning>> = _itemsExistenceStateFlow


    // Get enrolled courses
    fun getEnrollCourses() {
        viewModelScope.launch {
            learningRepository.getEnrollCourses()
                .onSuccess {
                    _enrollCoursesStateFlow.value = UiState.Success(it)
                }
                .onFailure {
                    _enrollCoursesStateFlow.value = UiState.Error(it)
                }
        }
    }

    // Insert a new Learning item
    fun insertLearningItem(learning: Learning) {
        viewModelScope.launch {
            kotlin.runCatching {
                learningRepository.insertLearningItem(learning)
            }.onSuccess {
                // You can handle additional logic on success if needed
            }.onFailure {
                // Handle error as required, such as setting an error state
            }
        }
    }

    // Delete a Learning item by its itemId
    fun deleteLearningItemByItemId(itemId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                learningRepository.deleteLearningItemByItemId(itemId)
            }.onFailure {
                // Handle error if delete fails
            }
        }
    }

    // Get all Learning items by courseId
    fun getLearningItemsByCourseId(courseIds: List<Course>) {
        viewModelScope.launch {
            _learningItemsStateFlow.value =learningRepository.getLearningItemsByCourseIds(courseIds.map { it.id })
        }
    }

    // Get Learning items by sectionId and courseId
    fun getLearningItemsBySection(sectionId: Int, courseId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                learningRepository.getLearningItemsBySection(sectionId, courseId)
            }.onSuccess {
                _learningItemStateFlow.value = UiState.Success(it)
            }.onFailure {
                _learningItemStateFlow.value = UiState.Error(it)
            }
        }
    }

    // Get a specific Learning item by itemId, sectionId, and courseId
    fun getLearningItemById(itemId: Int, sectionId: Int, courseId: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                learningRepository.getLearningItemById(itemId, sectionId, courseId)
            }.onSuccess {
                _learningItemStateFlow.value = UiState.Success(it)
            }.onFailure {
                _learningItemStateFlow.value = UiState.Error(it)
            }
        }
    }

    fun checkItemsExist(courseId: Int, sectionId: List<Int>, itemIds: List<Int>) {
        viewModelScope.launch {
            val existenceMap = learningRepository.checkItemsExist(courseId, sectionId, itemIds)
            _itemsExistenceStateFlow.value = existenceMap
        }
    }
}