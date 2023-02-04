package com.ebiondic.gex.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.ebiondic.details.navigation.detailsScreen
import com.ebiondic.gex.R
import com.ebiondic.search.navigation.searchScreen
import com.ebiondic.search.navigation.searchScreenRoute


@Composable
fun GexNavHost(
  navHostController: NavHostController,
  modifier: Modifier = Modifier,
  startDestination: String = searchScreenRoute
) {
  val context = LocalContext.current
  val upgradeToProMessage = stringResource(R.string.upgrade_to_pro_message)
  NavHost(
    navController = navHostController,
    startDestination = startDestination,
    modifier = modifier
  ) {
    with(navHostController) {
      searchScreen(onRepositorySelected = { _, _ ->
        Toast.makeText(context, upgradeToProMessage, Toast.LENGTH_SHORT).show()
      }
      )
      detailsScreen()
    }
  }
}