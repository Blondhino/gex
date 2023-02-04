package com.ebiondic.model.convert

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun String.convertToUiDateFormat(): String {
  return try {
    val dateTime = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy 'at' HH:mm")
    dateTime.format(formatter)
  } catch (e: DateTimeParseException) {
    ""
  }
}