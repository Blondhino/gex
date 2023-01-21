package com.ebiondic.gex.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ebiondic.gex.navigation.GexNavHost
import com.ebiondic.search.navigation.searchScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GexApp(
  appState: GexAppState = rememberGexAppState()
) {
  val navController = appState.navController
  Scaffold(modifier = Modifier.fillMaxSize()) {
    GexNavHost(
      navHostController = navController,
      modifier = Modifier.padding(it),
      startDestination = searchScreenRoute
    )
  }
}