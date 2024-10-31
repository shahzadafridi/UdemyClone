package com.droidcon.sduicompsemvvm.data.remote.service

import com.droidcon.sduicompsemvvm.data.remote.model.CourseDTO
import retrofit2.http.GET

interface CourseService {

    @GET("SDUI-Compose-MVVM-Example/main/example-responses/home-screen-components.json")
    suspend fun getCourses(): List<CourseDTO>

}