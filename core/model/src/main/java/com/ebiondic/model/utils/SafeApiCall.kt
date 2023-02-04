package com.ebiondic.model.utils

class SafeApiCall() {
  suspend operator fun <SuccessModel : Any> invoke(
    apiCall: suspend () -> SuccessModel
  ): Result<SuccessModel> {
    return try {
      Result.success(apiCall())
    } catch (throwable: Throwable) {
      Result.failure(throwable)
    }
  }
}