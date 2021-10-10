package com.example.weatherapp.di

import com.example.weatherapp.network.mapbox.MapboxContract
import com.example.weatherapp.network.mapbox.MapboxService
import com.example.weatherapp.network.open_weather_map.OpenWeatherMapContract
import com.example.weatherapp.network.open_weather_map.OpenWeatherMapService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideWeatherService(): OpenWeatherMapService {

        val loggingInterceptor by lazy {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        val tokenInterceptor by lazy {
            object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val originalRequest = chain.request()

                    val requestWithToken = originalRequest.newBuilder()
                        .url(
                            originalRequest.url.toString()
                                    + "&appid=${OpenWeatherMapContract.API_TOKEN}"
                        )
                        .build()

                    return chain.proceed(requestWithToken)
                }
            }
        }

        val httpClient by lazy {
            OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(tokenInterceptor)
                .build()
        }

        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(OpenWeatherMapContract.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OpenWeatherMapService::class.java)
    }

    @Singleton
    @Provides
    fun providePlacesService(): MapboxService {

        val loggingInterceptor by lazy {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        val tokenInterceptor by lazy {
            object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val originalRequest = chain.request()

                    val requestWithToken = originalRequest.newBuilder()
                        .url(
                            originalRequest.url.toString()
                                    + "&access_token=${MapboxContract.API_TOKEN}"
                        )
                        .build()

                    return chain.proceed(requestWithToken)
                }
            }
        }

        val httpClient by lazy {
            OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(tokenInterceptor)
                .build()
        }

        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(MapboxContract.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MapboxService::class.java)
    }

}