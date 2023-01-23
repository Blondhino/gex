package com.ebiondic.details.action

sealed interface DetailsScreenEvent {
  object OnOpenOnlineRepositoryDetailsClicked : DetailsScreenEvent
  object OnOpenOnlineUserDetailsClicked : DetailsScreenEvent
}