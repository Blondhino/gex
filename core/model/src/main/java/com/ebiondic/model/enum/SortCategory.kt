package com.ebiondic.model.enum

enum class SortCategory(val code: Int, val apiName: String) {
  STARS(0, "stars"),
  FORKS(1, "forks"),
  UPDATED(2, "updated"),
  NONE(3, "");
  
  companion object {
    fun fromCode(code: Int): SortCategory = SortCategory.values().find { it.code == code } ?: NONE
  }
}