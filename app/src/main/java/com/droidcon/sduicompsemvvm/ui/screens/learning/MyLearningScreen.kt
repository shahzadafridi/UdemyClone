package com.droidcon.sduicompsemvvm.ui.screens.learning


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.LearningCourseItem
import com.droidcon.sduicompsemvvm.ui.component.LearningCourseCard
import com.droidcon.sduicompsemvvm.ui.theme.titleBarStyle
import com.droidcon.sduicompsemvvm.util.UiState


@Composable
fun MyLearningScreen(innerPadding: PaddingValues, viewModel: LearningViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getEnrollCourses()
    }
    val courses = viewModel.enrollCoursesStateFlow.collectAsState()
    val learningItems = viewModel.learningItemsStateFlow.collectAsState()
    when (val state = courses.value) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            viewModel.getLearningItemsByCourseId(state.data)
            MyLearningScreenContent(
                innerPadding = innerPadding,
                data = state.data,
                viewModel = viewModel,
                learningItems = learningItems
            )
        }
        is UiState.Error -> {}
    }
}

@Composable
fun MyLearningScreenContent(
    innerPadding: PaddingValues = PaddingValues(),
    data: List<Course>,
    viewModel: LearningViewModel = hiltViewModel(),
    learningItems: State<Map<Int, List<LearningCourseItem>>>
) {
    Box(modifier = Modifier
        .padding(innerPadding)
        .fillMaxSize()) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                text = "My Learning",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = titleBarStyle,
                textAlign = TextAlign.Center
            )

            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(data) {
                    LearningCourseCard(
                        data = it,
                        onClick = { },
                        learningItems = learningItems,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}