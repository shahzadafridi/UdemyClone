package com.droidcon.sduicompsemvvm.ui.screens.course

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.droidcon.sduicompsemvvm.R
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Curriculum
import com.droidcon.sduicompsemvvm.data.local.model.CurriculumItem
import com.droidcon.sduicompsemvvm.data.local.model.CurriculumSection
import com.droidcon.sduicompsemvvm.ui.component.ExpandableCourseSection
import com.droidcon.sduicompsemvvm.ui.screens.Cart.CartViewModel
import com.droidcon.sduicompsemvvm.ui.screens.home.HomeViewModel
import com.droidcon.sduicompsemvvm.ui.screens.learning.LearningViewModel
import com.droidcon.sduicompsemvvm.ui.theme.Green
import com.droidcon.sduicompsemvvm.ui.theme.captionTextStyle
import com.droidcon.sduicompsemvvm.ui.theme.primaryLabelStyle
import com.droidcon.sduicompsemvvm.ui.theme.subTitleBarStyle
import com.droidcon.sduicompsemvvm.ui.theme.titleBarStyle
import com.droidcon.sduicompsemvvm.util.toast

@Composable
fun CourseDetailScreen(
    data: Course,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
    cartViewModel: CartViewModel = hiltViewModel(),
    learningViewModel: LearningViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        viewModel.isEnrollInCourse(data)
    }
    val isEnroll = viewModel.enrollInCourseStateFlow.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 22.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .clickable { navController.navigateUp() }
                    .size(24.dp),
                painter = painterResource(R.drawable.baseline_arrow_back_ios_new_24),
                contentDescription = "bacl"
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Course Detail",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = titleBarStyle.copy(fontSize = 22.sp),
                textAlign = TextAlign.Center
            )
        }

        AsyncImage(
            model = data.thumbnail,
            modifier = Modifier
                .padding(top = 32.dp, end = 16.dp, start = 16.dp)
                .fillMaxWidth()
                .height(180.dp),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = data.title,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = titleBarStyle
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
            text = data.description,
            style = captionTextStyle
        )

        Box(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .background(color = Color.Yellow, RoundedCornerShape(4.dp)),
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                text = data.type,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = primaryLabelStyle
            )
        }

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
            text = "(${data.totalRatings} ratings) ${data.totalStudents} students",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = captionTextStyle.copy(fontSize = 12.sp)
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 22.dp),
            text = buildAnnotatedString {
                append("Created by ")
                withStyle(style = SpanStyle(color = Color.Blue, fontWeight = FontWeight.Bold)) {
                    append(data.author) // Only this part will be blue and bold
                }
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = subTitleBarStyle.copy(fontSize = 14.sp)
        )

        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .clickable { navController.navigateUp() }
                    .size(14.dp),
                painter = painterResource(R.drawable.baseline_date_range_24),
                contentDescription = "date"
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Last update ${data.updatedAt}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = captionTextStyle.copy(fontSize = 12.sp)
            )

        }

        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                modifier = Modifier
                    .clickable { navController.navigateUp() }
                    .size(14.dp),
                painter = painterResource(R.drawable.baseline_language_24),
                contentDescription = "language"
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Language ${data.language}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = captionTextStyle.copy(fontSize = 12.sp)
            )

        }

        if (!isEnroll.value) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 22.dp),
                text = if (data.isFree) "Free!" else "Rs ${data.cost}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = titleBarStyle
            )

            Box(
                modifier = Modifier
                    .clickable {
                        if (data.isFree) {
                            viewModel.enrollInCourse(data) {
                                context.toast(it.first)
                            }
                        } else {
                            cartViewModel.addToCart(data) {
                                context.toast(it.first)
                            }
                        }
                    }
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .background(
                        color = if (data.isFree) Green else Color.Blue,
                        RoundedCornerShape(0.dp)
                    ),
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(vertical = 12.dp),
                    text = if (data.isFree) "Enroll now" else "Add to cart",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = subTitleBarStyle.copy(fontSize = 16.sp),
                    color = Color.White
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.05f),
                    shape = RoundedCornerShape(3.dp)
                )

        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp),
                text = "What you'll learn",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                style = titleBarStyle.copy(fontSize = 18.sp)
            )

            data.learns.forEach {
                Row(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable { navController.navigateUp() }
                            .size(18.dp),
                        painter = painterResource(R.drawable.baseline_check_24),
                        contentDescription = "language"
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = primaryLabelStyle
                    )

                }
            }
            Spacer(modifier = Modifier.padding(8.dp))
        }

        Text(
            modifier = Modifier.padding(start = 16.dp, top = 8.dp),
            text = "Curriculum",
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            style = titleBarStyle.copy(fontSize = 18.sp)
        )
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 4.dp),
            text = "${data.curriculum.sections.size} sections . ${data.curriculum.lectures} lectures . ${data.curriculum.time} total length",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = captionTextStyle.copy(fontSize = 12.sp)
        )

        Spacer(modifier = Modifier.padding(8.dp))

        ExpandableCourseList(data, isEnroll.value, learningViewModel)

        Spacer(modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun ExpandableCourseList(
    course: Course,
    isEnroll: Boolean,
    learningViewModel: LearningViewModel,
) {
    val itemsExistence = learningViewModel.itemsExistenceStateFlow.collectAsState()
    // Only trigger the call if isEnroll is true
    LaunchedEffect(isEnroll) {
        learningViewModel.checkItemsExist(
            courseId = course.id,
            sectionId = course.curriculum.sections.map { it.id },
            itemIds = course.curriculum.sections.flatMap { it.items.map { it.id } }
        )
    }
    course.curriculum.sections.forEach { section ->
        ExpandableCourseSection(course, section, isEnroll, itemsExistence, learningViewModel)
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
        val navController = rememberNavController()
        CourseDetailScreen(data = course, navController = navController)
    }
}