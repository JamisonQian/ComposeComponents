package com.jamison.codeing.compose.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jamison.codeing.compose.ui.theme.StatusBar
import com.jamison.codeing.compose.ui.theme.largeButton
import com.jamison.codeing.compose.widget.AppTopBar
import com.jamison.codeing.compose.widget.SuperButton

/**
 * @FileName LoginScreen
 * @Description
 * @Author arjun
 * @Date 2022/6/6 16:18
 * @Version
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(text = "登录", color = Color.White, fontSize = 18.sp)
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
        Column(
            modifier = Modifier.padding(it),
        ) {

            Spacer(modifier = Modifier.height(40.dp))
            SuperButton(onClick = {}, modifier = Modifier
                .largeButton()
                .padding(horizontal = 16.dp)) {
                Text(text = "Login", fontSize = 16.sp, color = Color.White)
            }

        }
    }
}