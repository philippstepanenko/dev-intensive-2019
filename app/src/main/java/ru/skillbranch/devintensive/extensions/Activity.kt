package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.content.res.Configuration
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager

fun Activity.hideKeyboard() {
    val focusedView = this.currentFocus
    if (focusedView != null) {
        val inputMethodManager = this.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(focusedView.windowToken, 0)
    }
}

fun Activity.isKeyboardOpen(): Boolean{

    val rect = Rect()
    this.window.decorView.getWindowVisibleDisplayFrame(rect)

    val screenHeight = when{
        this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT -> this.window.decorView.height
        else                                                                           -> this.window.decorView.width
    }
    val keypadHeight = screenHeight - rect.bottom

    return keypadHeight > 0
}

fun Activity.isKeyboardClosed(): Boolean{
    return isKeyboardOpen().not()
}