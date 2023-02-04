package com.ebiondic.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.ebiondic.designsystem.R
import com.ebiondic.designsystem.theme.smallPadding

@Composable
fun SearchAndSort(
  modifier: Modifier = Modifier,
  searchTerm: String,
  activeSortCategory: Int = STARS,
  onSearchTermChanged: (String) -> Unit,
  onSortCategorySelected: (Int) -> Unit = { },
  onSortDirectionClicked: () -> Unit = {},
  sortDirection: Int = DESCENDING,
) {
  
  Column(
    modifier = modifier.fillMaxWidth(),
    verticalArrangement = Arrangement.spacedBy(smallPadding)
  ) {
    InputTextField(
      modifier = Modifier.fillMaxWidth(),
      value = searchTerm,
      onValueChange = { onSearchTermChanged(it) },
      hint = stringResource(R.string.search_placeholder)
    )
    
    Text(
      text = stringResource(R.string.sort_by_label),
      style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.surface)
    )
    Row(
      modifier = Modifier
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Row(
        modifier = Modifier
          .fillMaxWidth(0.85f)
          .clip(RoundedCornerShape(30))
      ) {
        SortCategoryItem(
          modifier = Modifier.weight(1f),
          text = stringResource(id = R.string.sort_param_stars),
          isActive = activeSortCategory == STARS,
          onSelected = { onSortCategorySelected(STARS) }
        )
        SortCategoryItem(
          modifier = Modifier.weight(1f),
          text = stringResource(id = R.string.sort_params_forks),
          isActive = activeSortCategory == FORKS,
          onSelected = { onSortCategorySelected(FORKS) }
        )
        SortCategoryItem(
          modifier = Modifier.weight(1f),
          text = stringResource(id = R.string.sort_param_updated),
          isActive = activeSortCategory == UPDATED,
          onSelected = { onSortCategorySelected(UPDATED) }
        )
      }
      
      SortDirectionIndicator(
        sortDirection = sortDirection,
        onSortDirectionClick = { onSortDirectionClicked() }
      )
    }
    
  }
  
}

@Composable
@Preview
fun previewSearchAndSort() {
  SearchAndSort(
    searchTerm = "Repository",
    onSearchTermChanged = {},
  )
}
