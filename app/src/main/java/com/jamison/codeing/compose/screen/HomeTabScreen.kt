package com.jamison.codeing.compose.screen

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsHeight
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jamison.codeing.compose.ui.theme.StatusBar

/**
 * @FileName HomeTabScreen
 * @Description
 * @Author arjun
 * @Date 2022/6/7 17:16
 * @Version
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTabScreen() {

    val paddingValues = WindowInsets.statusBars.only(WindowInsetsSides.Horizontal+ WindowInsetsSides.Top).asPaddingValues()

    Log.d("TAG", paddingValues.toString())
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "主页", color = Color.White, fontSize = 18.sp)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = StatusBar
                ),
            )

        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding), contentAlignment = Alignment.Center
        ) {
            Text(text = "Home")
        }
    }


}