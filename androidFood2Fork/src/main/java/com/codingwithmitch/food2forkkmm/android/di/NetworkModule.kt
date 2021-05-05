package com.codingwithmitch.food2forkkmm.android.di

import com.codingwithmitch.food2forkkmm.datasource.network.KtorClientFactory
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeService
import com.codingwithmitch.food2forkkmm.datasource.network.RecipeServiceImpl
import com.codingwithmitch.food2forkkmm.datasource.network.model.RecipeDtoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient{
        return KtorClientFactory().build()
    }

    @Singleton
    @Provides
    fun provideDtoMapper(): RecipeDtoMapper {
        return RecipeDtoMapper()
    }

    @Singleton
    @Provides
    fun provideRecipeService(
        dtoMapper: RecipeDtoMapper
    ): RecipeService {
        return RecipeServiceImpl(
            recipeDtoMapper = dtoMapper,
            httpClient = KtorClientFactory().build(),
            baseUrl = RecipeServiceImpl.BASE_URL,
        )
    }
}






