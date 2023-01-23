package com.ebiondic.details.navigation

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ebiondic.common.decoder.StringDecoder
import com.ebiondic.details.DetailsRoute

const val detailsScreenRoute = "details_screen"
internal const val repositoryIdArg = "repositoryId"

internal class DetailsArgs(val repositoryId: String) {
  constructor(savedStateHandle: SavedStateHandle, stringDecoder: StringDecoder) :
    this(stringDecoder.decodeString(checkNotNull(savedStateHandle[repositoryIdArg])))
}

fun NavController.navigateToDetailsScreen(repositoryId: Int) {
  val encodedString = Uri.encode(repositoryId.toString())
  this.navigate("$detailsScreenRoute/$encodedString")
}

fun NavGraphBuilder.detailsScreen() {
  composable(
    route = "$detailsScreenRoute/{$repositoryIdArg}",
    arguments = listOf(
      navArgument(repositoryIdArg) { type = NavType.StringType }
    )
  ) {
    DetailsRoute()
  }
}
