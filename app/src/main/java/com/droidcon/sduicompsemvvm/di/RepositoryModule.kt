package com.droidcon.sduicompsemvvm.di

import com.droidcon.sduicompsemvvm.data.local.CourseDao
import com.droidcon.sduicompsemvvm.data.remote.mapper.CourseMapper
import com.droidcon.sduicompsemvvm.data.remote.repository.CartRepository
import com.droidcon.sduicompsemvvm.data.remote.repository.CartRepositoryImp
import com.droidcon.sduicompsemvvm.data.remote.repository.HomeRepository
import com.droidcon.sduicompsemvvm.data.remote.repository.HomeRepositoryImp
import com.droidcon.sduicompsemvvm.data.remote.repository.LearningRepository
import com.droidcon.sduicompsemvvm.data.remote.repository.LearningRepositoryImp
import com.droidcon.sduicompsemvvm.data.remote.service.CourseService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesCourseMapper(): CourseMapper {
        return CourseMapper()
    }

    @Provides
    fun providesHomeRepository(courseService: CourseService, courseMapper: CourseMapper, courseDao: CourseDao): HomeRepository {
       return HomeRepositoryImp(courseService, courseMapper, courseDao)
    }

    @Provides
    fun providesCartRepository(courseDao: CourseDao): CartRepository {
       return CartRepositoryImp(courseDao)
    }

    @Provides
    fun providesLearningRepository(courseDao: CourseDao): LearningRepository {
        return LearningRepositoryImp(courseDao)
    }

}