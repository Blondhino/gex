package com.ebiondic.network

import com.ebiondic.model.utils.SafeApiCall
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
  const val BASE_URL = "https://api.github.com/"
  
  @Provides
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      this.level = HttpLoggingInterceptor.Level.BODY
    }
  }
  
  @Provides
  fun provideOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor
  ): OkHttpClient {
    return OkHttpClient()
      .newBuilder()
      .addInterceptor(httpLoggingInterceptor)
      .build()
  }
  
  @Provides
  fun provideGithubApi(okHttpClient: OkHttpClient): GithubApi {
    return Retrofit.Builder()
      .baseUrl(BASE_URL)
      .client(okHttpClient)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
      .create(GithubApi::class.java)
  }
  
  @Provides
  fun provideSafeApiCall(): SafeApiCall = SafeApiCall()
  
}