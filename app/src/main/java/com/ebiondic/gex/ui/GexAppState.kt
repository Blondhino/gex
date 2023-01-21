package com.ebiondic.gex.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberGexAppState(
  navController: NavHostController = rememberNavController(), coroutineScope: CoroutineScope = rememberCoroutineScope()
): GexAppState {
  return remember(navController, coroutineScope) {
    GexAppState(navController, coroutineScope)
  }
}

class GexAppState(
  val navController: NavHostController, val coroutineScope: CoroutineScope
) {
  val currentDestination: NavDestination?
    @Composable get() = navController.currentBackStackEntryAsState().value?.destination
}