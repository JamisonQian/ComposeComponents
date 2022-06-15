package com.jamison.codeing.compose.widget

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.jamison.codeing.compose.ui.theme.Blue500
import com.jamison.codeing.compose.ui.theme.Gray500
import kotlinx.coroutines.delay
import kotlin.coroutines.cancellation.CancellationException

/**
 * @FileName Banner
 * @Description
 * @Author arjun
 * @Date 2022/6/15 10:10
 * @Version
 */
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(
    modifier: Modifier,
    data: List<String>,
    loopDuration: Long = 3000L
) {

    val pageCount = data.size
    //这里官方Demo 给的值是Int.Max_Value但是会报异常 https://github.com/google/accompanist/issues/1188
    val loopingCount = 100000
    val startIndex = loopingCount / 2
    val pagerState = rememberPagerState(initialPage = startIndex)


    fun pageMapper(index: Int): Int {
        return (index - startIndex).floorMod(pageCount)
    }

    Box(modifier = modifier) {
        HorizontalPager(
            count = loopingCount,
            modifier = Modifier.fillMaxSize(),
            state = pagerState
        ) { index ->
            val page = (index - startIndex).floorMod(pageCount)
            Image(
                painter = rememberAsyncImagePainter(model = data[page]),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            pageCount = pageCount,
            pageIndexMapping = ::pageMapper,
            activeColor = Blue500,
            inactiveColor = Gray500
        )
    }
    val loopState = remember { mutableStateOf(true) }
    var underDragging by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        pagerState.interactionSource.interactions.collect { interaction ->
            when (interaction) {
                is PressInteraction.Press -> underDragging = true
                is PressInteraction.Release -> underDragging = false
                is PressInteraction.Cancel -> underDragging = false
                is DragInteraction.Start -> underDragging = true
                is DragInteraction.Stop -> underDragging = false
                is DragInteraction.Cancel -> underDragging = false
            }
        }
    }
    val looping = loopState.value
    if (underDragging.not() && looping) {
        //启动一个协程
        LaunchedEffect(key1 = underDragging) {
            try {
                while (true) {
                    delay(loopDuration)
                    val current = pagerState.currentPage
                    val currentPos = pageMapper(current)
                    val nextPage = current + 1
                    if (underDragging.not()) {
                        val toPage = nextPage.takeIf { nextPage < pagerState.pageCount }
                            ?: (currentPos + startIndex + 1)
                        if (toPage > current) {
                            pagerState.animateScrollToPage(toPage)
                        } else {
                            pagerState.scrollToPage(toPage)
                        }
                    }
                }

            } catch (e: CancellationException) {

            }
        }
    }

}


private fun Int.floorMod(other: Int): Int = when (other) {
    0 -> this
    else -> this - floorDiv(other) * other
}

