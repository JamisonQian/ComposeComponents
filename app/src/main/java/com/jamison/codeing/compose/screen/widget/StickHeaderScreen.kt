package com.jamison.codeing.compose.screen.widget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.jamison.codeing.compose.screen.common.ImagesViewModel
import com.jamison.codeing.compose.ui.theme.StatusBar
import com.jamison.codeing.compose.widget.AppTopBar

/**
 * @FileName StickHeaderScreen
 * @Description
 * @Author arjun
 * @Date 2022/6/14 15:23
 * @Version
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun StickHeaderScreen(navController: NavController) {
    val viewModel: ImagesViewModel = viewModel()
    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(text = "粘性标题", color = Color.White, fontSize = 18.sp)
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
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            viewModel.images.forEachIndexed { index, data ->
                val isStick = index % 2 == 0
                if (isStick) {
                    stickyHeader {
                        Column(
                            modifier = Modifier
                                .height(50.dp)
                                .background(Color.White),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "我是粘性标题",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
                item { 
                    ImageCard(imageUrl = data)
                }
            }

        }

    }
}


@Composable
fun ImageCard(imageUrl: String) {
    Image(
        painter = rememberAsyncImagePainter(model = imageUrl),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(ratio = 2f, matchHeightConstraintsFirst = false),
        contentScale = ContentScale.Crop
    )
}