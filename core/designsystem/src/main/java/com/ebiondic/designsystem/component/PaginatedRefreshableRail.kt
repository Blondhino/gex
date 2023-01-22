package com.ebiondic.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T : Any> PaginatedRefreshableRail(
  modifier: Modifier = Modifier,
  listState: LazyListState = rememberLazyListState(),
  isFetching: Boolean,
  isRefreshIndicatorVisible: Boolean = false,
  items: List<T> = listOf(),
  isEndReached: Boolean = false,
  itemsSpacing: Dp = 20.dp,
  paginateAfterPercentageScrolled: Double = 0.8,
  loadMoreData: () -> Unit,
  onRefresh: () -> Unit,
  header: @Composable () -> Unit = {},
  composeItem: @Composable LazyItemScope.(item: T) -> Unit,
) {
  val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshIndicatorVisible, onRefresh = { onRefresh() })
  Box(
    Modifier
      .fillMaxSize()
      .pullRefresh(pullRefreshState)
  ) {
    LazyColumn(
      modifier = modifier.fillMaxSize(),
      state = listState,
      verticalArrangement = Arrangement.spacedBy(itemsSpacing)
    ) {
      item {
        header()
        Spacer(Modifier.height(itemsSpacing))
      }
      
      itemsIndexed(items = items) { index, item ->
        if (index >= ((paginateAfterPercentageScrolled) * (items.size)) && !isFetching && !isEndReached) {
          loadMoreData()
        }
        composeItem(item)
        
      }
      item {
        if (isFetching) {
          Row(
            modifier = Modifier
              .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
          ) {
            CircularProgressIndicator(
              modifier = Modifier.align(Alignment.CenterVertically),
              color = MaterialTheme.colorScheme.secondary,
              strokeWidth = 3.dp
            )
          }
        }
      }
      
    }
    
    PullRefreshIndicator(
      modifier = Modifier.align(Alignment.TopCenter),
      refreshing = isRefreshIndicatorVisible,
      state = pullRefreshState,
      backgroundColor = MaterialTheme.colorScheme.primary,
      contentColor = MaterialTheme.colorScheme.secondary
    )
  }
}
