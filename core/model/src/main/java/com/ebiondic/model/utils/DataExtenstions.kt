package com.ebiondic.model.utils

fun <T : Any> unknownError(): Result<T> {
  return Result.failure(Throwable("Unknown error !"))
}