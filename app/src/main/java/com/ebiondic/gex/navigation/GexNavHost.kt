package com.ebiondic.gex.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ebiondic.search.navigation.searchScreen
import com.ebiondic.search.navigation.searchScreenRoute


@Composable
fun GexNavHost(
  navHostController: NavHostController,
  modifier: Modifier = Modifier,
  startDestination: String = searchScreenRoute
) {
  NavHost(
    navController = navHostController,
    startDestination = startDestination,
    modifier = modifier
  ) {
    with(navHostController) {
      searchScreen(onRepositorySelected = {})
    }
  }
}