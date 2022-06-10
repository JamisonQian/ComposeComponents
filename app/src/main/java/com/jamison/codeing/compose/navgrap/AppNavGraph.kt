package com.jamison.codeing.compose.navgrap

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jamison.codeing.compose.screen.HomeScreen
import com.jamison.codeing.compose.screen.LoginScreen

/**
 * @FileName AppNavGraph
 * @Description
 * @Author arjun
 * @Date 2022/6/7 16:16
 * @Version
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AppNavGraph() {
    val controller = rememberAnimatedNavController()
    AnimatedNavHost(navController = controller, startDestination = Routers.HOME_ROUTE,
        enterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(400))
        },
        exitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Left, animationSpec = tween(400))
        },
        popEnterTransition = {
            slideIntoContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(400))

        },
        popExitTransition = {
            slideOutOfContainer(AnimatedContentScope.SlideDirection.Right, animationSpec = tween(400))
        }
    ) {

        composable(
            Routers.HOME_ROUTE,
        ) { HomeScreen(navController = controller) }
        composable(
            Routers.LOGIN_ROUTE,
        ) { LoginScreen(navController = controller) }
    }
}