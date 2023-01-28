package com.ebiondic.search.action

sealed interface SearchScreenEvent {
  data class SearchTermChanged(val term: String) : SearchScreenEvent
  data class SortCategoryClicked(val sortCategory: Int) : SearchScreenEvent
  object OnSortDirectionClicked : SearchScreenEvent
  object OnLoadMoreData : SearchScreenEvent
  object OnRefresh : SearchScreenEvent
}