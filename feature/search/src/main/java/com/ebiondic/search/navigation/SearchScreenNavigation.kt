package com.ebiondic.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.ebiondic.search.SearchRoute

const val searchScreenRoute = "search_screen"

fun NavController.navigateToSearchScreen() {
  this.navigate(searchScreenRoute)
}

fun NavGraphBuilder.searchScreen(
  onRepositorySelected: (repositoryName: String, ownerName: String) -> Unit
) {
  composable(searchScreenRoute) {
    SearchRoute(
      onRepositorySelected = { repositoryName, ownerName -> onRepositorySelected(repositoryName, ownerName) }
    )
  }
}