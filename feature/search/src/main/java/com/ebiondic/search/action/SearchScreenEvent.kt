package com.ebiondic.search.action

import com.ebiondic.designsystem.component.SortCategory

sealed interface SearchScreenEvent {
  data class SearchTermChanged(val term: String) : SearchScreenEvent
  data class SortCategoryClicked(val category: SortCategory) : SearchScreenEvent
  object OnSortDirectionClicked : SearchScreenEvent
}