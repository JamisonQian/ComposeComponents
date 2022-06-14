package com.jamison.codeing.compose.screen.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jamison.codeing.compose.ui.theme.*
import com.jamison.codeing.compose.widget.AppTopBar
import com.jamison.codeing.compose.widget.SuperInput

/**
 * @FileName InputScreen
 * @Description 输入框组件页面
 * @Author arjun
 * @Date 2022/6/13 14:28
 * @Version
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(text = "Input组件", color = Color.White, fontSize = 18.sp)
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
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                InputNormal()
                Spacer(modifier = Modifier.height(10.dp))
                InputError()
                Spacer(modifier = Modifier.weight(1f))
                LoginTextInput()
//                Spacer(modifier = Modifier.height(60.dp))
            }
        }
    }
}


@Composable
fun InputNormal() {
    var text by remember { mutableStateOf("") }
    var focused by remember { mutableStateOf(false) }
    SuperInput(
        value = text,
        modifier = Modifier.onFocusChanged { focused = it.isFocused },
        onValueChange = { text = it },
        textStyle = MaterialTheme.typography.body1.copy(color = Gray900),
        title = {
            Text(
                "标题内容非必填",
                style = MaterialTheme.typography.body2,
                color = Gray900,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        },
        hint = {
            Text(
                "请输入内容",
                style = MaterialTheme.typography.body1.copy(color = Gray400)
            )
        },
        trailing = {
            Icon(
                Icons.Default.Clear,
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier
                    .alpha(if (text.isNotEmpty()) 1f else 0f)
                    .clickable { if (text.isNotEmpty()) text = "" }
            )
        },
        divider = {
            Divider(
                color = if (focused) Blue500 else Color(0xFFE7E7E7),
                thickness = 1.dp,
                modifier = Modifier.padding(top = 12.dp)
            )
        },
        helper = {
            androidx.compose.material.Text(
                "描述文字描述文字描述文字描述文字描述文字描述文字描述文字描述文字描述文字描述文字描述文字不得超过两行",
                style = MaterialTheme.typography.caption.copy(color = Gray500),
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    )
}

@Composable
fun InputError() {
    var text by remember { mutableStateOf("") }

    SuperInput(
        value = text,
        onValueChange = { text = it },
        textStyle = MaterialTheme.typography.body1.copy(color = Gray900),
        cursorBrush = SolidColor(Error500),
        title = {
            Text(
                "标题内容非必填",
                style = MaterialTheme.typography.body2,
                color = Gray900,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        },
        hint = {
            Text(
                "请输入内容",
                style = MaterialTheme.typography.body1.copy(color = Gray400)
            )
        },
        trailing = {
            Icon(
                Icons.Default.Clear,
                contentDescription = "",
                tint = Color.Unspecified,
                modifier = Modifier
                    .alpha(if (text.isNotEmpty()) 1f else 0f)
                    .clickable { if (text.isNotEmpty()) text = "" }
            )
        },
        divider = {
            androidx.compose.material.Divider(
                color = Error500,
                thickness = 1.dp,
                modifier = Modifier.padding(top = 12.dp)
            )
        },
        helper = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 4.dp)
            ) {
                Icon(
                    Icons.Default.Warning,
                    contentDescription = "",
                    tint = Error500,
                    modifier = Modifier.padding(end = 4.dp)
                )
                Text(
                    "这里是错误提示",
                    style = MaterialTheme.typography.caption.copy(color = Error500),
                )
            }
        }
    )
}

@Composable
fun LoginTextInput() {
    var text by remember { mutableStateOf("") }
    var focused by remember { mutableStateOf(false) }
    SuperInput(
        value = text,
        modifier = Modifier.imePadding().onFocusChanged { focused = it.isFocused },
        onValueChange = {
            text = it
        },
        textStyle = MaterialTheme.typography.body1.copy(color = Gray900),
        title = {
            Text(
                "用户名",
                style = MaterialTheme.typography.body2,
                color = Gray900,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        },
        hint = {
            Text(
                "请输入登录用户名",
                style = MaterialTheme.typography.body1.copy(color = Gray400)
            )
        },
        divider = {
            Divider(
                color = if (focused) Blue500 else Color(0xFFE7E7E7),
                thickness = 1.dp,
                modifier = Modifier.padding(top = 12.dp)
            )
        },
    )
}