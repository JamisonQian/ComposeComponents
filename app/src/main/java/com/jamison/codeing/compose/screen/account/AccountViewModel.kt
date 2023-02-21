package com.jamison.codeing.compose.screen.account

import androidx.compose.foundation.lazy.LazyListState
import androidx.lifecycle.ViewModel
import com.jamison.codeing.compose.navgrap.Routers

/**
 * @FileName AccountViewModel
 * @Description
 * @Author arjun
 * @Date 2022/6/8 10:00HO
 * @Version
 */
class AccountViewModel :ViewModel(){

    val list = mutableListOf(
        WidgetModel("Input",Routers.INPUT_ROUTE),
        WidgetModel("Button",Routers.Button_ROUTE),
        WidgetModel("Grid",Routers.GRID_ROUTE),
        WidgetModel("TopTabBar",Routers.TOP_TAB_ROUTE),
        WidgetModel("StickHeader",Routers.STICK_HEADER_ROUTE),
        WidgetModel("NavigationDrawer",Routers.NAVIGATION_DRAWER_ROUTE),
        WidgetModel("BottomSheet",Routers.BOTTOM_SHEET_ROUTE),
        WidgetModel("Instagram UI",Routers.INSTAGRAM_ROUTE),
        WidgetModel("Dialog",Routers.DIALOG_ROUTE),
    )

}

data class WidgetModel(
    val name:String,
    val route:String
)