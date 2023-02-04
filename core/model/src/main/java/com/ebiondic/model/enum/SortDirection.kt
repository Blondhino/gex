package com.ebiondic.model.enum

enum class SortDirection(val code: Int, val apiName: String) {
  ASCENDING(0, "asc"),
  DESCENDING(1, "desc");
  
  companion object {
    fun fromCode(code: Int): SortDirection = SortDirection.values().find { it.code == code } ?: DESCENDING
  }
}