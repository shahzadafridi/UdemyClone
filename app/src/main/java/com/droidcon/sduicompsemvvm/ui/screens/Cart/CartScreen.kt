package com.droidcon.sduicompsemvvm.ui.screens.Cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Curriculum
import com.droidcon.sduicompsemvvm.data.local.model.CurriculumItem
import com.droidcon.sduicompsemvvm.data.local.model.CurriculumSection
import com.droidcon.sduicompsemvvm.ui.component.CartCourseCard
import com.droidcon.sduicompsemvvm.ui.screens.home.HomeViewModel
import com.droidcon.sduicompsemvvm.ui.theme.Green
import com.droidcon.sduicompsemvvm.ui.theme.primaryLabelStyle
import com.droidcon.sduicompsemvvm.ui.theme.subTitleBarStyle
import com.droidcon.sduicompsemvvm.ui.theme.titleBarStyle
import com.droidcon.sduicompsemvvm.util.UiState


@Composable
fun CartScreen(innerPadding: PaddingValues, viewModel: CartViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.getCartCourses()
    }
    val courses = viewModel.cartCoursesStateFlow.collectAsState()
    when (val state = courses.value) {
        is UiState.Loading -> {

        }

        is UiState.Success -> {
            CartScreenContent(innerPadding = innerPadding, data = state.data, viewModel = viewModel)
        }

        is UiState.Error -> {

        }
    }
}

@Composable
fun CartScreenContent(innerPadding: PaddingValues = PaddingValues(), data: List<Course>, viewModel: CartViewModel = hiltViewModel()) {
    Box(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, top = 16.dp),
                text = "Cart",
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
                    CartCourseCard(
                        data = it,
                        onClick = { },
                        onDelete = { viewModel.deleteCartCourse(it) }
                    )
                }
            }
        }

        if (data.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .zIndex(1f) // Bring this element to the front
                    .background(color = Color.Black, shape = RoundedCornerShape(2.dp)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 8.dp)
                        .weight(1f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Total",
                            style = primaryLabelStyle,
                            color = Color.White
                        )

                        Text(
                            modifier = Modifier,
                            text = "Rs ${data.sumOf { it.cost.toDouble() }}",
                            style = titleBarStyle.copy(fontSize = 22.sp),
                            color = Color.LightGray,
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier,
                            text = "Tax",
                            style = primaryLabelStyle,
                            color = Color.White
                        )

                        Text(
                            modifier = Modifier,
                            text = "0%",
                            style = titleBarStyle.copy(fontSize = 22.sp),
                            color = Color.LightGray,
                        )
                    }
                }

                Card(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = Green),
                    shape = RoundedCornerShape(50.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        text = "Buy now",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = subTitleBarStyle.copy(fontSize = 16.sp),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Preview
@Composable
private fun Display() {

    val course = Course(
        id = 5,
        title = "iOS App Development with SwiftUI",
        description = "Develop iOS applications with the modern SwiftUI framework. Covers the fundamentals and advanced techniques.",
        thumbnail = "https://img-c.udemycdn.com/course/480x270/959700_8bd2_12.jpg",
        updatedAt = "15-10-2023 3:00 PM",
        author = "Alex Johnson",
        rating = 4.4f,
        totalRatings = 700,
        totalStudents = 2500,
        type = "Bestseller",
        language = "English",
        learns = arrayListOf("SwiftUI basics", "Animations", "Data handling in SwiftUI"),
        requirements = arrayListOf("Basic Swift knowledge"),
        cost = 4500f,
        curriculum = Curriculum(
            id = 5,
            lectures = 7,
            time = "6 hours",
            sections = arrayListOf(
                CurriculumSection(
                    id = 8,
                    title = "SwiftUI Essentials",
                    items = arrayListOf(
                        CurriculumItem(id = 1, title = "Creating views", time = "15 mins"),
                        CurriculumItem(id = 2, title = "Handling state", time = "20 mins")
                    )
                ),
                CurriculumSection(
                    id = 9,
                    title = "Advanced SwiftUI Techniques",
                    items = arrayListOf(
                        CurriculumItem(id = 3, title = "Animations", time = "25 mins"),
                        CurriculumItem(id = 4, title = "Data handling", time = "30 mins")
                    )
                )
            )
        ),
        isFree = false
    )
    MaterialTheme {
        CartScreenContent(data = arrayListOf(course))
    }
}
