package com.droidcon.sduicompsemvvm.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.droidcon.sduicompsemvvm.R
import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.ui.component.CourseCard
import com.droidcon.sduicompsemvvm.ui.theme.captionTextStyle
import com.droidcon.sduicompsemvvm.ui.theme.titleBarStyle
import com.droidcon.sduicompsemvvm.util.UiState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToCourseDetail: (Course) -> Unit,
    innerPadding: PaddingValues
){
    LaunchedEffect(Unit) {
        viewModel.onGetHomeComponents()
    }
    val components = viewModel.coursesStateFlow.collectAsState()
    when(val state = components.value){
        is UiState.Loading -> {
            
        }
        is UiState.Success -> {
            HomeScreen(innerPadding, state.data, navigateToCourseDetail)
        }
        is UiState.Error -> {

        }
    }
}

@Composable
fun HomeScreen(innerPadding: PaddingValues, data: List<Course>, navigateToCourseDetail: (Course) -> Unit){
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {

        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = R.drawable.ic_launcher_background,
                modifier = Modifier
                    .size(24.dp),
                contentDescription = ""
            )

            Text(
                modifier = Modifier,
                text = "Shahzad Afridi",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = titleBarStyle.copy(fontSize = 16.sp)
            )
        }

        AsyncImage(
            model = "https://img-c.udemycdn.com/notices/featured_carousel_slide/image/be8e9e3d-1eb4-48fb-8b84-c14b88ffbcdd.jpg",
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .height(180.dp),
            contentDescription = "",
            contentScale = ContentScale.FillBounds
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, top =  16.dp),
            text = "Learnings that fits",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = titleBarStyle
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, top =  4.dp),
            text = "Skills for your present (and future)",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = captionTextStyle.copy(fontSize = 14.sp)
        )

        Text(
            modifier = Modifier.padding(start = 16.dp, top =  4.dp),
            text = "Recommended for you",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = titleBarStyle.copy(fontSize = 22.sp)
        )

        Spacer(modifier = Modifier.padding(4.dp))

        data.forEach {
            Box(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                CourseCard(data = it, onClick = navigateToCourseDetail)
            }
        }

    }
}


