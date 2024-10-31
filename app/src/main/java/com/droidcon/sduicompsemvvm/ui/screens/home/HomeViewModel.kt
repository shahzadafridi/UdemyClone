package com.droidcon.sduicompsemvvm.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.sduicompsemvvm.data.remote.repository.HomeRepository
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _coursesStateFlow = MutableStateFlow<UiState<List<Course>>>(UiState.Loading)
    val coursesStateFlow: StateFlow<UiState<List<Course>>> = _coursesStateFlow


    private val _enrollInCourseStateFlow = MutableStateFlow<Boolean>(false)
    val enrollInCourseStateFlow: StateFlow<Boolean> = _enrollInCourseStateFlow

    fun onGetHomeComponents() {
        viewModelScope.launch {
             homeRepository.getCourses()
                 .onSuccess {
                     _coursesStateFlow.value = UiState.Success(it)
                 }
                 .onFailure {
                     _coursesStateFlow.value = UiState.Error(it)
                 }
        }
    }

    fun enrollInCourse(course: Course) {
        viewModelScope.launch {
            val enroll = homeRepository.IsEnrollInCourse(course.id)
            if (enroll?.courseId != course.id ) {
                homeRepository.enrollInCourse(course)
                _enrollInCourseStateFlow.update { true }
            }
        }
    }

    fun isEnrollInCourse(course: Course) {
        viewModelScope.launch {
            val enroll = homeRepository.IsEnrollInCourse(course.id)
            _enrollInCourseStateFlow.update { enroll?.courseId == course.id }
        }
    }

}