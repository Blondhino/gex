package com.ebiondic.search.action

sealed interface SearchScreenEvent {
  data class SearchTermChanged(val term: String) : SearchScreenEvent
}