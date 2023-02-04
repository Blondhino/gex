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
internal const val repositoryNameArg = "repositoryName"
internal const val ownerNameArg = "ownerName"

internal class DetailsArgs(val repositoryName: String, val ownerName: String) {
  constructor(savedStateHandle: SavedStateHandle, stringDecoder: StringDecoder) :
    this(
      repositoryName = stringDecoder.decodeString(checkNotNull(savedStateHandle[repositoryNameArg])),
      ownerName = stringDecoder.decodeString(checkNotNull(savedStateHandle[ownerNameArg])),
    )
}

fun NavController.navigateToDetailsScreen(repositoryName: String, ownerName: String) {
  val encodedStringRepositoryName = Uri.encode(repositoryName)
  val encodedStringOwnerName = Uri.encode(ownerName)
  this.navigate("$detailsScreenRoute/$encodedStringRepositoryName/$encodedStringOwnerName")
}

fun NavGraphBuilder.detailsScreen(onScreenError: () -> Unit) {
  composable(
    route = "$detailsScreenRoute/{$repositoryNameArg}/{$ownerNameArg}",
    arguments = listOf(
      navArgument(repositoryNameArg) { type = NavType.StringType },
      navArgument(ownerNameArg) { type = NavType.StringType }
    )
  ) {
    DetailsRoute(onScreenError = { onScreenError() })
  }
}
