package com.jamison.codeing.compose.screen.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jamison.codeing.compose.ui.theme.StatusBar
import com.jamison.codeing.compose.widget.AppTopBar
import kotlin.random.Random

/**
 * @FileName PickImageScreen
 * @Description
 * @Author arjun
 * @Date 2022/6/13 15:44
 * @Version
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GridScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(text = "Gird", color = Color.White, fontSize = 18.sp)
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = StatusBar
            )

        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                LazyVerticalGrid(
                    //固定两列
                    columns = GridCells.Fixed(2),
                    //添加水平间距
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    //添加垂直间距
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                    //添加内容间距
                    contentPadding = PaddingValues(4.dp),
                    content ={
                        items(12){
                            RandomColorBox(modifier = Modifier.height(200.dp))
                        }
                } )

            }
        }
    }
}


@Composable
fun RandomColorBox(modifier: Modifier) {
    Box(modifier = modifier.background(rememberRandomColor()))
}

fun randomColor() = Color(Random.nextInt(255),Random.nextInt(255),Random.nextInt(255))

@Composable
fun rememberRandomColor() = rememberSaveable(
    saver = Saver(
        save = {
                value -> value.toArgb()
        },
        restore = {
            Color(it)
        }
    )
) {
    randomColor()
}

