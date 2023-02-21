package com.jamison.codeing.compose.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * @FileName AppTopBar
 * @Description 实现封装TopBar 增加了状态栏 这里的状态栏通过增加状态栏高度的占位实现
 * @Author arjun
 * @Date 2022/6/10 15:39
 * @Version
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    backgroundColor: Color,
    scrollBehavior: TopAppBarScrollBehavior? = null,
    statusBar: Color = backgroundColor,
    statusBarHide: Boolean = false
) {
    Column {
        if (!statusBarHide) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    //将高度设置为状态栏的高度
                    .windowInsetsTopHeight(WindowInsets.statusBars)
                    .background(color = statusBar)
            )
        }
        CenterAlignedTopAppBar(
            title = title,
            modifier = modifier,
            navigationIcon = navigationIcon,
            actions = actions,
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = backgroundColor
            ),
            scrollBehavior = scrollBehavior,
            windowInsets = WindowInsets.systemBars.only(WindowInsetsSides.Horizontal)
        )
    }

}