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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.blankj.utilcode.util.AppUtils
import com.blankj.utilcode.util.DeviceUtils
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jamison.codeing.compose.screen.common.ImagesViewModel
import com.jamison.codeing.compose.ui.theme.StatusBar
import com.jamison.codeing.compose.widget.Banner

/**
 * @FileName FavoriteTabScreen
 * @Description
 * @Author arjun
 * @Date 2022/6/7 17:16
 * @Version
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteTabScreen() {

    val imagesModel: ImagesViewModel = viewModel()
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
//            Image(
//                painter = rememberAsyncImagePainter(model = imagesModel.banners[0]),
//                contentDescription = null,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .aspectRatio(ratio = 2f, matchHeightConstraintsFirst = false),
//                contentScale = ContentScale.Crop
//            )

            Banner(modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ratio = 2f, matchHeightConstraintsFirst = false), data =imagesModel.banners)
        }
    }

}