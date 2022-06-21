package com.jamison.codeing.compose.model

import androidx.annotation.DrawableRes

/**
 * @FileName TabItem
 * @Description
 * @Author arjun
 * @Date 2022/6/20 15:54
 * @Version
 */
data class TabItem(
    @DrawableRes val icon:Int,
    val title:String
)
