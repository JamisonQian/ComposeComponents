package com.jamison.codeing.compose.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.jamison.codeing.compose.ui.theme.buttonColor

/**
 * @FileName SuperButton
 * @Description 封装Button按钮
 * @Author arjun
 * @Date 2022/6/13 09:34
 * @Version
 */
@Composable
fun SuperButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    shape: Shape ?= null,
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(
        containerColor = MaterialTheme.colorScheme.buttonColor,
        disabledContainerColor =  MaterialTheme.colorScheme.buttonColor.copy(alpha = 0.5f),

        contentColor =  contentColorFor(MaterialTheme.colorScheme.buttonColor),
        disabledContentColor = contentColorFor(MaterialTheme.colorScheme.buttonColor.copy(alpha = 0.5f))
    ),
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    content: @Composable RowScope.() -> Unit
) {

    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        elevation = elevation,
        shape = shape ?: RoundedCornerShape(25.dp),
        border = border,
        colors = colors,
        contentPadding = contentPadding,
        content = content

    )

}

object SuperButtonContentPadding {
    val normal = PaddingValues(horizontal = 32.dp, vertical = 12.dp)

    val small = PaddingValues(horizontal = 16.dp, vertical = 4.dp)
}