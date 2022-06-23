package com.jamison.codeing.compose.widget.scroll

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.Layout
import kotlin.math.roundToInt


@Composable
 fun NestedStickScrollView(
    modifier: Modifier = Modifier,
    state: NestedScrollViewState,
    header: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    Layout(
        modifier = modifier.nestedScroll(state.nestedScrollConnectionHolder),
        content = {
            Box {
                header.invoke()
            }
            Box {
                content.invoke()
            }
        },
    ) { measures, constraints ->
        layout(constraints.maxWidth, constraints.maxHeight) {
            val headerPlaceable = measures[0].measure(constraints.copy())
            headerPlaceable.place(0, state.offset.roundToInt())
            state.updateBounds(-(headerPlaceable.height.toFloat()))
            val contentPlaceable =
                measures[1].measure(constraints.copy(maxHeight = constraints.maxHeight))
            contentPlaceable.place(
                0,
                state.offset.roundToInt() + headerPlaceable.height
            )
        }
    }
}
