package com.jamison.codeing.compose.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jamison.codeing.compose.screen.account.AccountTabScreen
import com.jamison.codeing.compose.ui.theme.StatusBar
import kotlinx.coroutines.launch

/**
 * @FileName HomeScreen
 * @Description
 * @Author arjun
 * @Date 2022/6/6 16:15
 * @Version
 */

sealed class TabScreen(val route: String, val imageVector: ImageVector) {
    object HOME : TabScreen("Home", Icons.Default.Home)
    object FAVORITE : TabScreen("Favorite", Icons.Default.Favorite)
    object ACCOUNT : TabScreen("Account", Icons.Default.Person)
}

private val items = listOf(TabScreen.HOME, TabScreen.FAVORITE, TabScreen.ACCOUNT)


@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalPagerApi::class
)
@Composable
fun HomeScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    Scaffold(
        bottomBar = {
            BottomNavigation(backgroundColor = Color.White) {
                items.forEachIndexed() { index, screen ->
                    val isSelected = pagerState.currentPage == index
                    BottomNavigationItem(
                        selected = isSelected,
                        onClick = {
                            if(pagerState.currentPage != index){
                                scope.launch {
                                    pagerState.scrollToPage(index)
                                }
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = screen.imageVector,
                                contentDescription = null,
                                tint = if (isSelected) Color.Green else Color.Gray
                            )
                        },
                        label = {
                            Text(
                                text = screen.route,
                                fontSize = 12.sp,
                                color = if (isSelected) Color.Green else Color.Gray
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        HorizontalPager(
            count = items.size,
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
            when(page){
                0->{
                    HomeTabScreen()
                }
                1->{
                    FavoriteTabScreen()
                }
                2->{
                    AccountTabScreen(navController = navController)
                }
            }
        }
    }
}
