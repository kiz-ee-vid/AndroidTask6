package com.example.task_6.presentation.di.modules

import com.example.task_6.data.ApiService
import com.example.task_6.data.RepositoryImpl
import com.example.task_6.domain.IRepository
import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class])
object ReposModule {
    @Provides
    fun provideRepository(apiService: ApiService): IRepository =
        RepositoryImpl(apiService)
}