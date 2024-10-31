package com.droidcon.sduicompsemvvm.ui.screens.Cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.remote.repository.CartRepository
import com.droidcon.sduicompsemvvm.util.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _cartCoursesStateFlow = MutableStateFlow<UiState<List<Course>>>(UiState.Loading)
    val cartCoursesStateFlow: StateFlow<UiState<List<Course>>> = _cartCoursesStateFlow


    fun addToCart(course: Course, onResult: (Pair<String,Boolean>) -> Unit) {
        viewModelScope.launch {
            val result = cartRepository.addToCart(course)
            onResult.invoke(result)
        }
    }

    fun getCartCourses() {
        viewModelScope.launch {
            cartRepository.getCartCourses().collectLatest {
                _cartCoursesStateFlow.value = UiState.Success(it.map { it.course })
            }
        }
    }

    fun deleteCartCourse(course: Course) {
        viewModelScope.launch {
            cartRepository.deleteCartCourse(course.id)
        }
    }

}