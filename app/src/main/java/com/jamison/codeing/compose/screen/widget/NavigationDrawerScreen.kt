package com.jamison.codeing.compose.screen.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jamison.codeing.compose.ui.theme.StatusBar
import com.jamison.codeing.compose.widget.AppTopBar
import com.jamison.codeing.compose.widget.SuperButton
import kotlinx.coroutines.launch

/**
 * @FileName NavigationDrawerScreen
 * @Description 侧滑栏组件
 * @Author arjun
 * @Date 2022/6/14 17:03
 * @Version
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(text = "侧滑菜单组件", color = Color.White, fontSize = 18.sp)
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
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            ModalNavigationDrawerSample()
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalNavigationDrawerSample() {

    //侧滑菜单状态
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    //获取一个协程
    val scope = rememberCoroutineScope()

    val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
    val selectedItem = remember { mutableStateOf(items[0]) }
    //如果想要菜单栏从右侧显示则加入以下代码CompositionLocalProvider就可
//  CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            items.forEach { item ->
                NavigationDrawerItem(
                    label = { Text(text = item.name) },
                    selected = item == selectedItem.value,
                    onClick = {
                        scope.launch { drawerState.close() }
                        selectedItem.value = item
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }

        }) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = if(drawerState.isClosed) ">>> Swipe >>>>" else "<<< Swipe <<<<")
            Spacer(modifier = Modifier.height(20.dp))

            SuperButton(onClick = {
                scope.launch {
                    drawerState.open()
                }
            }) {
                Text(text = "Click to Open", color = Color.White)
            }

        }
    }
// }

}