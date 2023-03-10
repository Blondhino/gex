package com.ebiondic.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ebiondic.designsystem.R
import com.ebiondic.designsystem.theme.largePadding
import com.ebiondic.designsystem.theme.smallPadding
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T : Any> PaginatedRefreshableRail(
  modifier: Modifier = Modifier,
  isEnabledScrollToTopButton: Boolean = false,
  listState: LazyListState = rememberLazyListState(),
  isFetching: Boolean,
  isRefreshIndicatorVisible: Boolean = false,
  items: List<T> = listOf(),
  isEndReached: Boolean = false,
  itemsSpacing: Dp = 20.dp,
  paginateAfterPercentageScrolled: Double = 0.5,
  loadMoreData: () -> Unit,
  onRefresh: () -> Unit,
  header: @Composable () -> Unit = {},
  composeItem: @Composable LazyItemScope.(item: T) -> Unit,
) {
  val pullRefreshState = rememberPullRefreshState(refreshing = isRefreshIndicatorVisible, onRefresh = { onRefresh() })
  val shouldShowScrollToTopButtonIfIsEnabled = remember { derivedStateOf { listState.firstVisibleItemIndex > 0 } }.value
  val coroutineScope = rememberCoroutineScope()
  
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
        if (isFetching && items.isNotEmpty()) {
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
    AnimatedVisibility(
      modifier = Modifier
        .align(Alignment.BottomEnd)
        .clip(RoundedCornerShape(50))
        .clickable {
          coroutineScope.launch {
            listState.scrollToItem(0)
          }
        }
        .background(MaterialTheme.colorScheme.secondary),
      visible = isEnabledScrollToTopButton && shouldShowScrollToTopButtonIfIsEnabled
    ) {
      Box(
        modifier = Modifier.padding(largePadding)
      )
      Icon(
        modifier = Modifier
          .size(50.dp)
          .padding(smallPadding)
          .clip(CircleShape),
        painter = painterResource(id = R.drawable.ic_scroll_to_top),
        contentDescription = null
      )
    }
    
  }
}
