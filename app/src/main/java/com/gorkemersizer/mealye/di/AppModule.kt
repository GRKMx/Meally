package com.gorkemersizer.mealye.di

import com.gorkemersizer.mealye.data.repo.YemeklerDaoRepository
import com.gorkemersizer.mealye.retrofit.ApiUtils
import com.gorkemersizer.mealye.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerDaoRepository(ydao: YemeklerDao): YemeklerDaoRepository {
        return YemeklerDaoRepository(ydao)
    }

    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao {
        return ApiUtils.getYemeklerDao()
    }
}
