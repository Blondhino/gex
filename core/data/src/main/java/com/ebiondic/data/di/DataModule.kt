package com.ebiondic.data.di

import com.ebiondic.data.GithubRepoRepository
import com.ebiondic.data.GithubRepoRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
  
  @Binds
  fun provideGithubRepoRepository(githubRepoRepositoryImpl: GithubRepoRepositoryImpl): GithubRepoRepository
  
}