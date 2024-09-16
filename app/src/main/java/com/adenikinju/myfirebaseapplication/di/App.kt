package com.adenikinju.myfirebaseapplication.di

import com.adenikinju.myfirebaseapplication.Repository.MovieRepository.MovieRepository
import com.adenikinju.myfirebaseapplication.Repository.MovieRepository.MovieRepositoryImpl
import com.adenikinju.myfirebaseapplication.data.ApiInterface
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private val TOKEN =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3NmVkNzA4YjZiYTE0MDQ4ZjQ5MGYzNTNiYTYzNDYxZiIsIm5iZiI6MTcyNjQwNTE3My42MzU3MTksInN1YiI6IjY2ZTY2YmRhMzc2OGE3M2Y4ZDkxMmZiYSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.jVteFospg25y9PJM00UIfHOh64fWsT2NwDacXLF9sQ4"

@Module
@InstallIn(SingletonComponent::class)
object App {

    @Provides
    fun getRetrofit(
        client: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/discover/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun getGson(): Gson {
        return Gson()
    }

    @Provides
    fun getHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", "Bearer $TOKEN")
                    .header("Content-Type", "application/json;charset=utf-8")
                    .header("Accept", "application/json")
                    .method(original.method, original.body)

                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    fun getRepository(
        apiClient: ApiInterface
    ): MovieRepository {
        return MovieRepositoryImpl(apiClient)
    }

    @Provides
    fun getApiInterface(
        retrofit: Retrofit
    ): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}