package com.jamison.codeing.compose.screen.account


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jamison.codeing.compose.navgrap.Routers
import com.jamison.codeing.compose.ui.theme.DividerColor
import com.jamison.codeing.compose.ui.theme.StatusBar
import com.jamison.codeing.compose.widget.AppTopBar

/**
 * @FileName AccountTabScreen
 * @Description
 * @Author arjun
 * @Date 2022/6/7 17:17
 * @Version
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountTabScreen(viewModel: AccountViewModel = viewModel(), navController: NavHostController) {

    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(text = "Account", color = Color.White, fontSize = 18.sp)
                },
                backgroundColor = StatusBar,
            )

        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding)
        ) {
            viewModel.list.forEach {
                item {
                    Text(text = it.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(it.route)
                            }
                            .padding(vertical = 20.dp),
                        textAlign = TextAlign.Center
                    )
                    Divider(modifier = Modifier.height(1.dp).fillMaxWidth(), color = DividerColor)
                }
            }
        }
    }


}


/**
 * 1、代码混淆加密
 * 2、菜单访问权限：建议在限制菜单上实施服务器端访问控制验证，以确保只有授权用户才能访问该功能。
 * 3、文件上传没有类型限制：Jagawana 建议控制可能在服务器端上传的文件类型。
 * 4、网络协议  https 证书校验
 *
 *
 *
 *
 */