package com.ebiondic.testing.di

import com.ebiondic.common.decoder.network.Dispatcher
import com.ebiondic.common.decoder.network.GexDispatchers.*
import com.ebiondic.common.decoder.network.di.DispatchersModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


@Module
@TestInstallIn(
  components = [SingletonComponent::class],
  replaces = [DispatchersModule::class]
)
object TestDispatchersModule {
  
  @Provides
  @Dispatcher(IO)
  fun providesIODispatcher() : CoroutineDispatcher = Dispatchers.IO
}