package com.ebiondic.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun InputTextField(
  modifier: Modifier = Modifier,
  value: String,
  onValueChange: (String) -> Unit,
  hint: String = "",
  errorMessage: String = "",
  keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
  focusRequester: FocusRequester = FocusRequester()
) {
  val focusedColor = MaterialTheme.colorScheme.secondary
  val unfocusedColor = MaterialTheme.colorScheme.surface
  val focusedTextColor = MaterialTheme.colorScheme.onPrimary
  val unfocusedTextColor = MaterialTheme.colorScheme.onSecondary
  val focusedHintOffset = MaterialTheme.typography.labelMedium.fontSize.value.dp + 10.dp
  val unfocusedHintFontSize = MaterialTheme.typography.labelMedium.fontSize.value
  val focusedHintFontSize = (MaterialTheme.typography.labelMedium.fontSize.value - 2)
  val unfocusedHintOffset = 3.dp
  val textOffsetFromBottomLine = 8.dp
  val bottomLineHeight = 0.8.dp
  val totalHeight = MaterialTheme.typography.labelMedium.fontSize.value.dp + focusedHintOffset + textOffsetFromBottomLine
  var passwordVisible: Boolean by remember {
    mutableStateOf(keyboardOptions.keyboardType != KeyboardType.Password)
  }
  val passwordIcon = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
  
  var focused by remember { mutableStateOf(false) }
  val hintOffset by animateDpAsState(
    targetValue = if (focused) focusedHintOffset else if (!focused && value.isEmpty()) unfocusedHintOffset else focusedHintOffset,
    tween(durationMillis = 500)
  )
  val color by animateColorAsState(targetValue = if (focused) focusedColor else unfocusedColor, tween(durationMillis = 500))
  val textColor by animateColorAsState(
    targetValue = if (focused) focusedTextColor else unfocusedTextColor, tween(durationMillis = 500)
  )
  val hintFontSize by animateFloatAsState(
    targetValue = if (focused) focusedHintFontSize else if (!focused && value.isEmpty()) unfocusedHintFontSize else focusedHintFontSize,
    tween(durationMillis = 500)
  )
  
  val customTextSelectionColors = TextSelectionColors(
    handleColor = MaterialTheme.colorScheme.secondary,
    backgroundColor = MaterialTheme.colorScheme.secondary,
  )
  
  Column() {
    ConstraintLayout(
      modifier = modifier
        .height(totalHeight)
        .fillMaxWidth()
    ) {
      val (hintText, textField, bottomLine) = createRefs()
      CompositionLocalProvider(
        LocalTextSelectionColors provides customTextSelectionColors,
      ) {
        Row(
          modifier =
          Modifier
            .constrainAs(textField) {
              bottom.linkTo(parent.bottom, textOffsetFromBottomLine)
              end.linkTo(parent.end)
              start.linkTo(parent.start)
              width = Dimension.fillToConstraints
            },
          verticalAlignment = Alignment.Bottom
        ) {
          BasicTextField(
            modifier = Modifier
              .onFocusChanged { focused = it.isFocused }
              .weight(if (keyboardOptions.keyboardType == KeyboardType.Password) 0.9F else 1F)
              .focusRequester(focusRequester),
            maxLines = 1,
            value = value,
            onValueChange = { onValueChange(it) },
            textStyle = MaterialTheme.typography.labelMedium.copy(color = textColor),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.secondary),
            keyboardOptions = keyboardOptions,
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            
            )
          if (keyboardOptions.keyboardType == KeyboardType.Password) {
            Icon(
              imageVector = passwordIcon, tint = MaterialTheme.colorScheme.onSecondary, contentDescription = "",
              modifier = Modifier
                .padding(horizontal = 12.dp)
                .size(20.dp)
                .clip(CircleShape)
                .clickable { passwordVisible = !passwordVisible }
            )
          }
        }
        
      }
      Text(text = hint,
        style = MaterialTheme.typography.labelMedium.copy(color = color, fontSize = hintFontSize.sp),
        modifier = Modifier
          .constrainAs(hintText) {
            start.linkTo(textField.start)
            bottom.linkTo(textField.bottom)
          }
          .offset(y = -hintOffset))
      Spacer(modifier = Modifier
        .background(color = color)
        .constrainAs(bottomLine) {
          bottom.linkTo(parent.bottom)
          end.linkTo(parent.end)
          start.linkTo(parent.start)
          width = Dimension.fillToConstraints
        }
        .height(bottomLineHeight))
    }
    
    Row(modifier = modifier
      .offset(y = 5.dp)
      .fillMaxWidth()) {
      AnimatedVisibility(visible = errorMessage.isNotEmpty(), enter = expandVertically(), exit = shrinkVertically()) {
        Text(errorMessage, style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.secondary))
      }
    }
  }
  
}

@Composable
@Preview
fun previewTextField() {
  var text by remember { mutableStateOf("") }
  InputTextField(value = text, onValueChange = { text = it }, modifier = Modifier.fillMaxWidth(), errorMessage = "error text")
}