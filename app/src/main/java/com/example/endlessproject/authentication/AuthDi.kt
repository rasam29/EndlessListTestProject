package com.example.endlessproject.authentication

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthDi {


    @Provides
    @Singleton
    fun providesNetwork(retrofit: Retrofit): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun providesRepository(network: AuthService): AuthRepository {
        return AuthRepositoryImpl(network)
    }

}

enum class ListKey {
    All_New,
    All_Data,
    Top_New
}