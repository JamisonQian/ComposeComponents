package com.jamison.codeing.compose.screen.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jamison.codeing.compose.ui.theme.StatusBar
import com.jamison.codeing.compose.ui.theme.largeButton
import com.jamison.codeing.compose.ui.theme.smallButton
import com.jamison.codeing.compose.widget.AppTopBar
import com.jamison.codeing.compose.widget.SuperButton
import com.jamison.codeing.compose.widget.SuperButtonContentPadding

/**
 * @FileName InputScreen
 * @Description 输入框组件页面
 * @Author arjun
 * @Date 2022/6/13 14:28
 * @Version
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(text = "Button组件", color = Color.White, fontSize = 18.sp)
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
                .fillMaxWidth()
                .padding(it)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                SuperButton(
                    onClick = { },
                    modifier = Modifier
                        .padding(12.dp)
                        .largeButton(),
                ) {
                    Text(text = "主按钮", color = Color.White)
                }

                SuperButton(
                    onClick = { },
                    modifier = Modifier
                        .padding(12.dp)
                        .largeButton(),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Email, "",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(24.dp),
                            tint = Color.White
                        )
                        Text("带图标主按钮", color = Color.White)
                    }
                }


                SuperButton(
                    onClick = { },
                    modifier = Modifier
                        .padding(12.dp)
                        .smallButton(),
                    contentPadding = SuperButtonContentPadding.small
                ) {
                    Text(text = "主按钮", color = Color.White)
                }
                SuperButton(
                    onClick = { },
                    modifier = Modifier
                        .padding(12.dp)
                        .smallButton(),
                    contentPadding = SuperButtonContentPadding.small
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        androidx.compose.material.Icon(
                            Icons.Default.Email, "",
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .size(24.dp),
                            tint = Color.White
                        )
                        Text("带图标主按钮", color = Color.White)
                    }
                }
            }
        }
    }
}