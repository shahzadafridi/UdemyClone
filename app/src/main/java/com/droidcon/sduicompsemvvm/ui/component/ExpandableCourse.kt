package com.droidcon.sduicompsemvvm.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.droidcon.sduicompsemvvm.R
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.CurriculumItem
import com.droidcon.sduicompsemvvm.data.local.model.CurriculumSection
import com.droidcon.sduicompsemvvm.data.local.model.Learning
import com.droidcon.sduicompsemvvm.ui.screens.learning.LearningViewModel
import com.droidcon.sduicompsemvvm.ui.theme.Green
import com.droidcon.sduicompsemvvm.ui.theme.captionTextStyle
import com.droidcon.sduicompsemvvm.ui.theme.titleBarStyle

@Composable
fun ExpandableCourseSection(
    course: Course,
    section: CurriculumSection,
    isEnroll: Boolean,
    itemsExistence: State<List<Learning>>,
    learningViewModel: LearningViewModel
) {
    var isExpanded by remember { mutableStateOf(false) }
    // Map to hold the checked state for each item by item ID
    val checkedStates = remember { mutableStateMapOf<Int, Boolean>() }
    section.items.forEach { courseItem ->
        val item = itemsExistence.value.findLast { it.courseId == course.id && it.sectionId == section.id && it.itemId == courseItem.id}
        checkedStates[courseItem.id] = item != null
    }
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = section.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = captionTextStyle.copy(fontSize = 12.sp)
                )
                Icon(
                    modifier = Modifier.size(14.dp),
                    painter = painterResource(if (isExpanded) R.drawable.baseline_horizontal_rule_24 else R.drawable.baseline_add_24),
                    contentDescription = "date"
                )
            }
            if (isExpanded) {
                // Nested list of course items
                Column(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    section.items.forEach { courseItem ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                                text = "${courseItem.id}",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                style = titleBarStyle.copy(fontSize = 12.sp)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(
                                    modifier = Modifier.padding(start = 8.dp)
                                ) {
                                    Text(
                                        modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                                        text = courseItem.title,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        style = titleBarStyle.copy(fontSize = 12.sp)
                                    )
                                    Text(
                                        modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                                        text = "Video - ${courseItem.time}",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis,
                                        style = captionTextStyle.copy(fontSize = 10.sp)
                                    )
                                }

                                if (isEnroll) {
                                    Checkbox(
                                        modifier = Modifier.size(18.dp),
                                        checked = checkedStates[courseItem.id] ?: false,
                                        onCheckedChange = { isChecked ->
                                            checkedStates[courseItem.id] = isChecked
                                            if (isChecked) {
                                                learningViewModel.insertLearningItem(
                                                    learning = Learning(
                                                        courseId = course.id,
                                                        sectionId = section.id,
                                                        itemId = courseItem.id
                                                    )
                                                )
                                            } else {
                                                learningViewModel.deleteLearningItemByItemId(
                                                    courseItem.id
                                                )
                                            }
                                        },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = Green,
                                            uncheckedColor = Color.Gray
                                        ),
                                    )

                                } else {
                                    Icon(
                                        modifier = Modifier.size(18.dp),
                                        painter = painterResource(R.drawable.baseline_live_tv_24),
                                        contentDescription = "video"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun DisplayExpandableCourseSection() {
    val section = CurriculumSection(
        id = 5,
        title = "Dependency Injection in Android",
        items = arrayListOf(
            CurriculumItem(id = 3, title = "Using Hilt", time = "30 mins"),
            CurriculumItem(id = 4, title = "Best practices", time = "25 mins")
        )
    )

}