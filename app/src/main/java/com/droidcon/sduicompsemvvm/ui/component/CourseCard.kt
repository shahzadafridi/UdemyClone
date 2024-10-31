package com.droidcon.sduicompsemvvm.ui.component

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.droidcon.sduicompsemvvm.R
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Curriculum
import com.droidcon.sduicompsemvvm.data.local.model.LearningCourseItem
import com.droidcon.sduicompsemvvm.ui.screens.learning.LearningViewModel
import com.droidcon.sduicompsemvvm.ui.theme.Green
import com.droidcon.sduicompsemvvm.ui.theme.captionTextStyle
import com.droidcon.sduicompsemvvm.ui.theme.primaryLabelStyle
import com.droidcon.sduicompsemvvm.ui.theme.subTitleBarStyle
import com.droidcon.sduicompsemvvm.ui.theme.titleBarStyle

@Composable
fun CourseCard(data: Course, onClick: (Course) -> Unit) {
    Card(
        modifier = Modifier
            .clickable { onClick.invoke(data) }
            .shadow(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = .5f))
    ) {

        Column(modifier = Modifier) {

            AsyncImage(
                model = data.thumbnail,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    modifier = Modifier,
                    text = data.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = titleBarStyle.copy(fontSize = 16.sp)
                )

                Text(
                    modifier = Modifier,
                    text = data.author,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = captionTextStyle.copy(fontSize = 12.sp)
                )

                Text(
                    modifier = Modifier,
                    text = "RS ${data.cost}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = subTitleBarStyle.copy(fontSize = 16.sp)
                )

                Box(
                    modifier = Modifier
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
            }
        }
    }
}

@Composable
fun CartCourseCard(data: Course, onClick: (Course) -> Unit, onDelete: (Course) -> Unit) {
    Card(
        modifier = Modifier
            .height(120.dp)
            .clickable { onClick.invoke(data) }
            .shadow(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = .5f))
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
        ) {

            AsyncImage(
                model = data.thumbnail,
                modifier = Modifier
                    .width(105.dp)
                    .height(110.dp),
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    modifier = Modifier,
                    text = data.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = titleBarStyle.copy(fontSize = 16.sp)
                )

                Text(
                    modifier = Modifier,
                    text = data.author,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = captionTextStyle.copy(fontSize = 12.sp)
                )

                Text(
                    modifier = Modifier,
                    text = "RS ${data.cost}",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = subTitleBarStyle.copy(fontSize = 16.sp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
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

                    Icon(
                        modifier = Modifier
                            .clickable { onDelete.invoke(data) }
                            .size(24.dp),
                        painter = painterResource(R.drawable.baseline_delete_24),
                        contentDescription = "date"
                    )
                }
            }
        }
    }
}

@Composable
fun LearningCourseCard(data: Course, learningItems: State<Map<Int, List<LearningCourseItem>>>, onClick: (Course) -> Unit, viewModel: LearningViewModel) {
    val itemsCount = data.curriculum.lectures
    val existenceCount = learningItems.value[data.id]?.size ?: 0
    Log.e("xtest","Course ${data.title} , Progress: ${(existenceCount/itemsCount.toFloat())}")
    Card(
        modifier = Modifier
            .clickable { onClick.invoke(data) }
            .shadow(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(0.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        border = BorderStroke(1.dp, Color.Black.copy(alpha = .5f))
    ) {

        Column(modifier = Modifier) {

            AsyncImage(
                model = data.thumbnail,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.FillWidth,
                contentDescription = ""
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Text(
                    modifier = Modifier,
                    text = data.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = titleBarStyle.copy(fontSize = 16.sp)
                )

                Text(
                    modifier = Modifier,
                    text = data.author,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = captionTextStyle.copy(fontSize = 12.sp)
                )

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = "${data.curriculum.sections.size} sections . ${data.curriculum.lectures} lectures . ${data.curriculum.time} total length . $existenceCount completed",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = captionTextStyle.copy(fontSize = 12.sp)
                )

                LinearProgressIndicator(
                    progress = existenceCount/itemsCount.toFloat(),
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .fillMaxWidth()
                        .height(8.dp) // Custom height if needed
                        .clip(RoundedCornerShape(2.dp)),  // Apply rounded corners
                    color = Green,
                    trackColor = Color.LightGray,
                )

                Spacer(modifier = Modifier.padding(top = 2.dp))
            }
        }
    }
}

@Preview
@Composable
private fun Display() {

    val course = Course(
        id = 1,
        title = "Kotlin Multiplatform Masterclass - KMP, KMM - Andorid, IOS",
        description = "Master iOS and Android app development with KMP - Jetpack Compose, SwiftUI, Ktor, SQLDelight, Clean Architecture, MVI",
        thumbnail = "http://sfasdfadfda",
        updatedAt = "30-10-2023 9:00 PM",
        author = "Petros Efthymious",
        rating = 4.5f,
        totalRatings = 622,
        totalStudents = 3000,
        type = "Bestseller",
        language = "English",
        learns = arrayListOf(),
        requirements = arrayListOf(),
        cost = 4900f,
        curriculum = Curriculum(
            id = 1,
            lectures = 4,
            time = "4 hours",
            sections = arrayListOf()
        ),
        isFree = false
    )
    MaterialTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            CartCourseCard(data = course, onClick = {}, onDelete = {})
            CourseCard(data = course, {})
            LearningCourseCard(data = course, mutableStateOf(mapOf()), {}, viewModel =  hiltViewModel())
        }
    }
}