package com.jamison.codeing.compose.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.jamison.codeing.compose.ui.theme.Blue500

/**
 * @FileName Input
 * @Description
 * @Author arjun
 * @Date 2022/6/13 14:26
 * @Version
 */

@Composable
fun SearchInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    backgroundColor: Color = Color(0xFFF9FAFB),
    borderColor: Color = Color(0xFFE4E7EC),
    borderWidth: Dp = 1.dp,
    shape: Shape = RoundedCornerShape(CornerSize(50)),
    contentPadding: PaddingValues = PaddingValues(vertical = 6.dp, horizontal = 16.dp),
    hint: @Composable () -> Unit = {},
    leading: @Composable () -> Unit = {},
    trailing: @Composable () -> Unit = {},
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(Blue500),
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit =
        @Composable { innerTextField ->
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box {
                        if (value.isEmpty()) {
                            hint()
                        }
                        innerTextField()
                    }
                }
            }
        }
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.then(
            Modifier
                .background(
                    color = backgroundColor,
                    shape = shape
                )
                .border(
                    width = borderWidth,
                    color = borderColor,
                    shape = shape
                )
                .padding(contentPadding)
        )
    ) {

        leading()
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            keyboardOptions = keyboardOptions,
            cursorBrush = cursorBrush,
            singleLine = singleLine,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            onTextLayout = onTextLayout,
            interactionSource = interactionSource,
            decorationBox = decorationBox
        )
        trailing()
    }
}

@Composable
fun SuperInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    title: @Composable (() -> Unit)? = null,
    hint: @Composable (() -> Unit)? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
    divider: @Composable () -> Unit = {
        Divider(
            color = Color(0xFFE7E7E7),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 12.dp)
        )
    },
    helper: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = TextStyle.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    cursorBrush: Brush = SolidColor(Blue500),
    decorationBox: @Composable (innerTextField: @Composable () -> Unit) -> Unit =
        @Composable { innerTextField ->
            Box {
                if (value.isEmpty()) {
                    hint?.invoke()
                }
                innerTextField()
            }
        }
) {

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        title?.invoke()
        Row(verticalAlignment = Alignment.CenterVertically) {
            leading?.invoke()
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier.weight(1f),
                enabled = enabled,
                readOnly = readOnly,
                textStyle = textStyle,
                keyboardOptions = keyboardOptions,
                cursorBrush = cursorBrush,
                singleLine = singleLine,
                keyboardActions = keyboardActions,
                maxLines = maxLines,
                visualTransformation = visualTransformation,
                onTextLayout = onTextLayout,
                interactionSource = interactionSource,
                decorationBox = decorationBox
            )
            trailing?.invoke()
        }
        divider()
        helper?.invoke()
    }
}