package com.example.ooprvtask.di

import android.content.Context
import com.example.ooprvtask.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object module {



    @Singleton
    @Provides
    fun provideUserRepository(@ApplicationContext appContext: Context): UserRepository =
        UserRepository(appContext)




}