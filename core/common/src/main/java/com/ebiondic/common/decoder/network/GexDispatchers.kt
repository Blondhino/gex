package com.ebiondic.common.decoder.network

import javax.inject.Qualifier
import kotlin.annotation.AnnotationRetention.RUNTIME

@Qualifier
@Retention(RUNTIME)
annotation class Dispatcher(val gexDispatcher: GexDispatchers)

enum class GexDispatchers {
    IO
}
