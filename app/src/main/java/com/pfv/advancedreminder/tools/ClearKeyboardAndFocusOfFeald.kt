package com.pfv.advancedreminder.tools

import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController

fun clearKeyboardAndFocusOfField(
    keyboardController: SoftwareKeyboardController?,
    focusManager: FocusManager
) {

    keyboardController?.hide()
    focusManager.clearFocus()
}