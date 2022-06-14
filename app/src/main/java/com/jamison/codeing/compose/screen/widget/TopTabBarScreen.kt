package com.jamison.codeing.compose.screen.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jamison.codeing.compose.ui.theme.Blue500
import com.jamison.codeing.compose.ui.theme.DividerColor
import com.jamison.codeing.compose.ui.theme.Gray500
import com.jamison.codeing.compose.ui.theme.StatusBar
import com.jamison.codeing.compose.widget.AppTopBar

/**
 * @FileName TopTabBarScreen
 * @Description
 * @Author arjun
 * @Date 2022/6/14 14:46
 * @Version
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopTabBarScreen(navController: NavController) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(text = "Top Tab Bar", color = Color.White, fontSize = 18.sp)
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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Spacer(modifier = Modifier.height(20.dp))
            TopTabBarDemo1()
            Spacer(modifier = Modifier.height(10.dp))
            TopTabBarDemo2()
        }

    }
}
@Preview
@Composable
fun TopTabBarDemo1() {

    val tabs = mutableListOf("选项卡1", "选项卡2", "选项卡3", "选项卡4", "选项卡5", "选项卡6", "选项卡7")
    var selectIndex by remember { mutableStateOf(0) }

    ScrollableTabRow(
        selectedTabIndex = selectIndex,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        containerColor = Color.White,
        edgePadding = 0.dp,
        indicator = { list ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(list[selectIndex]),
                color = Blue500
            )
        },
        divider = {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp), color = DividerColor
            )
        }
    ) {
        tabs.forEachIndexed { index, data ->
            val isSelected = index == selectIndex
            Tab(
                selected = isSelected,
                onClick = { selectIndex = index },
                //这里高度必须设置为最大高度，否则显示会不居中
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = data,
                    color = if (isSelected) Blue500 else Gray500,
                    fontSize = 14.sp
                )
            }

        }

    }

}

@Composable
fun TopTabBarDemo2() {
    val tabData = listOf(
        "选项内容",
        "选项内容",
        "选项内容",
        "选项内容",
    )

    var selectIndex by  remember { mutableStateOf(0) }

    TabRow(
        selectedTabIndex = selectIndex,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        containerColor = Color.White,
        indicator = { list ->
            TabRowDefaults.Indicator(
                Modifier.tabIndicatorOffset(list[selectIndex]),
                color = Blue500
            )
        },
        divider = {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp), color = DividerColor
            )
        }
    ) {
        tabData.forEachIndexed { index, data ->
            val isSelected = index == selectIndex
            Tab(
                selected = isSelected,
                onClick = { selectIndex = index },
            ) {
                Text(
                    text = data,
                    color = if (isSelected) Blue500 else Gray500,
                    fontSize = 14.sp
                )
            }

        }

    }

}