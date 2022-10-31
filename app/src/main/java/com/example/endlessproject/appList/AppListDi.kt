package com.example.endlessproject.appList

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppListDi {

    @Provides
    fun provideRetrofit(
        client: OkHttpClient
    ): AppListService {
        return Retrofit.Builder()
            .baseUrl("https://apiserver.myket.ir/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client( client )
            .build()
            .create(AppListService::class.java)
    }

    @Provides
    fun provideRepository(network: AppListService): AppListRepository {
        return AppListRepositoryImpl(network)
    }
}
