package com.jamison.codeing.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.jamison.codeing.compose.navgrap.AppNavGraph
import com.jamison.codeing.compose.screen.HomeScreen
import com.jamison.codeing.compose.screen.LoginScreen
import com.jamison.codeing.compose.ui.theme.ComposeComponentsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window,false)

        lifecycleScope.launch {
            delay(200)
            setContent {
                ComposeComponentsTheme {
                    AppNavGraph()
                }
            }
        }

    }
}


