package com.jamison.codeing.compose.ui.theme

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Colors
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

fun Modifier.largeButton(): Modifier {
    return defaultMinSize(minHeight = 48.dp).fillMaxWidth()
}

fun Modifier.mediumButton(): Modifier {
    return defaultMinSize(minHeight = 48.dp)
}

fun Modifier.smallButton() = this