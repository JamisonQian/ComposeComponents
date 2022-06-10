package com.jamison.codeing.compose.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jamison.codeing.compose.ui.theme.StatusBar

/**
 * @FileName FavoriteTabScreen
 * @Description
 * @Author arjun
 * @Date 2022/6/7 17:16
 * @Version
 */
private const val imageUrl =
    "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTabScreen() {
    Scaffold{ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(ratio = 2f, matchHeightConstraintsFirst = false),
                contentScale = ContentScale.Crop
            )
        }
    }

}