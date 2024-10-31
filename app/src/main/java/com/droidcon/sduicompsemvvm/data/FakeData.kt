package com.droidcon.sduicompsemvvm.data

import com.droidcon.sduicompsemvvm.data.local.model.Course
import com.droidcon.sduicompsemvvm.data.local.model.Curriculum
import com.droidcon.sduicompsemvvm.data.local.model.CurriculumItem
import com.droidcon.sduicompsemvvm.data.local.model.CurriculumSection

object FakeData {
    val courses = listOf(
        Course(
            id = 1,
            title = "Kotlin Multiplatform Masterclass - KMP, KMM - Android, iOS",
            description = "Master iOS and Android app development with KMP - Jetpack Compose, SwiftUI, Ktor, SQLDelight, Clean Architecture, MVI.",
            thumbnail = "https://img-c.udemycdn.com/course/750x422/6174391_2c2b.jpg",
            updatedAt = "30-10-2023 9:00 PM",
            author = "Petros Efthymious",
            rating = 4.5f,
            totalRatings = 622,
            totalStudents = 3000,
            type = "Bestseller",
            language = "English",
            learns = arrayListOf("Compose UI", "KMM basics", "SwiftUI", "Ktor setup"),
            requirements = arrayListOf("Basic programming knowledge", "Passion for app development"),
            cost = 4900f,
            curriculum = Curriculum(
                id = 1,
                lectures = 2,
                time = "4 hours",
                sections = arrayListOf(
                    CurriculumSection(
                        id = 1,
                        title = "Introduction to KMM",
                        items = arrayListOf(
                            CurriculumItem(id = 1, title = "Setting up the environment", time = "15 mins"),
                            CurriculumItem(id = 2, title = "Overview of KMP", time = "20 mins")
                        )
                    )
                )
            ),
            isFree = false
        ),
        Course(
            id = 2,
            title = "Android Jetpack Compose Essentials",
            description = "Build beautiful UIs with Android's Jetpack Compose. A complete guide for both beginners and experienced developers.",
            thumbnail = "https://img-c.udemycdn.com/course/750x422/5681096_a327.jpg",
            updatedAt = "01-11-2023 10:00 AM",
            author = "Megan Smith",
            rating = 4.8f,
            totalRatings = 1200,
            totalStudents = 5000,
            type = "Top Rated",
            language = "English",
            learns = arrayListOf("Compose layouts", "State management", "Animations"),
            requirements = arrayListOf("Familiarity with Kotlin"),
            cost = 3500f,
            curriculum = Curriculum(
                id = 2,
                lectures = 6,
                time = "6 hours",
                sections = arrayListOf(
                    CurriculumSection(
                        id = 2,
                        title = "Compose Basics",
                        items = arrayListOf(
                            CurriculumItem(id = 1, title = "Setting up Compose project", time = "10 mins"),
                            CurriculumItem(id = 2, title = "Layout basics", time = "25 mins")
                        )
                    ),
                    CurriculumSection(
                        id = 3,
                        title = "Advanced UI Elements",
                        items = arrayListOf(
                            CurriculumItem(id = 3, title = "State management", time = "30 mins"),
                            CurriculumItem(id = 4, title = "Compose animations", time = "45 mins"),
                            CurriculumItem(id = 1, title = "Setting up the environment", time = "15 mins"),
                            CurriculumItem(id = 2, title = "Overview of KMP", time = "20 mins")
                        )
                    )
                )
            ),
            isFree = true
        ),
        Course(
            id = 3,
            title = "Advanced Android Architecture Patterns",
            description = "Deep dive into MVVM, Clean Architecture, and Jetpack libraries to build scalable Android applications.",
            thumbnail = "https://img-c.udemycdn.com/course/480x270/919872_ed54_6.jpg",
            updatedAt = "25-10-2023 5:00 PM",
            author = "John Doe",
            rating = 4.6f,
            totalRatings = 800,
            totalStudents = 3200,
            type = "Popular",
            language = "English",
            learns = arrayListOf("MVVM architecture", "Clean Architecture principles", "Dependency Injection"),
            requirements = arrayListOf("Intermediate Android knowledge", "Understanding of Kotlin"),
            cost = 4200f,
            curriculum = Curriculum(
                id = 3,
                lectures = 8,
                time = "5 hours",
                sections = arrayListOf(
                    CurriculumSection(
                        id = 4,
                        title = "Introduction to MVVM",
                        items = arrayListOf(
                            CurriculumItem(id = 1, title = "Overview of MVVM", time = "15 mins"),
                            CurriculumItem(id = 2, title = "Setting up ViewModel", time = "20 mins"),
                            CurriculumItem(id = 3, title = "State management", time = "30 mins"),
                            CurriculumItem(id = 4, title = "Compose animations", time = "45 mins"),
                        )
                    ),
                    CurriculumSection(
                        id = 5,
                        title = "Dependency Injection in Android",
                        items = arrayListOf(
                            CurriculumItem(id = 3, title = "Using Hilt", time = "30 mins"),
                            CurriculumItem(id = 4, title = "Best practices", time = "25 mins"),
                            CurriculumItem(id = 1, title = "Setting up the environment", time = "15 mins"),
                            CurriculumItem(id = 2, title = "Overview of KMP", time = "20 mins")
                        )
                    )
                )
            ),
            isFree = false
        ),
        Course(
            id = 4,
            title = "Mastering Coroutines and Flow in Kotlin",
            description = "Learn about asynchronous programming in Kotlin with coroutines and Flow for better performance and readability.",
            thumbnail = "https://img-c.udemycdn.com/course/750x422/5980708_d366_2.jpg",
            updatedAt = "20-10-2023 11:00 AM",
            author = "Jane Roe",
            rating = 4.7f,
            totalRatings = 950,
            totalStudents = 4000,
            type = "Bestseller",
            language = "English",
            learns = arrayListOf("Coroutine basics", "Flow API", "Error handling"),
            requirements = arrayListOf("Kotlin basics", "Understanding of Android development"),
            cost = 3800f,
            curriculum = Curriculum(
                id = 4,
                lectures = 6,
                time = "3 hours",
                sections = arrayListOf(
                    CurriculumSection(
                        id = 6,
                        title = "Introduction to Coroutines",
                        items = arrayListOf(
                            CurriculumItem(id = 1, title = "Setting up coroutines", time = "10 mins"),
                            CurriculumItem(id = 2, title = "Coroutine scopes", time = "15 mins")
                        )
                    ),
                    CurriculumSection(
                        id = 7,
                        title = "Understanding Flow",
                        items = arrayListOf(
                            CurriculumItem(id = 3, title = "Flow basics", time = "20 mins"),
                            CurriculumItem(id = 4, title = "Error handling with Flow", time = "25 mins"),
                            CurriculumItem(id = 3, title = "State management", time = "30 mins"),
                            CurriculumItem(id = 4, title = "Compose animations", time = "45 mins"),
                        )
                    )
                )
            ),
            isFree = false
        ),
        Course(
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
                lectures = 8,
                time = "6 hours",
                sections = arrayListOf(
                    CurriculumSection(
                        id = 8,
                        title = "SwiftUI Essentials",
                        items = arrayListOf(
                            CurriculumItem(id = 1, title = "Creating views", time = "15 mins"),
                            CurriculumItem(id = 2, title = "Handling state", time = "20 mins"),
                            CurriculumItem(id = 3, title = "Flow basics", time = "20 mins"),
                            CurriculumItem(id = 4, title = "Error handling with Flow", time = "25 mins"),
                        )
                    ),
                    CurriculumSection(
                        id = 9,
                        title = "Advanced SwiftUI Techniques",
                        items = arrayListOf(
                            CurriculumItem(id = 3, title = "Animations", time = "25 mins"),
                            CurriculumItem(id = 4, title = "Data handling", time = "30 mins"),
                            CurriculumItem(id = 3, title = "State management", time = "30 mins"),
                            CurriculumItem(id = 4, title = "Compose animations", time = "45 mins")
                        )
                    )
                )
            ),
            isFree = true
        )
    )
}