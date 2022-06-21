@file:OptIn(ExperimentalPagerApi::class)

package com.jamison.codeing.compose.screen.widget

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollDispatcher
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.*
import com.jamison.codeing.compose.R
import com.jamison.codeing.compose.model.TabItem
import com.jamison.codeing.compose.screen.common.ImagesViewModel
import com.jamison.codeing.compose.ui.theme.Blue500
import com.jamison.codeing.compose.ui.theme.DividerColor
import com.jamison.codeing.compose.ui.theme.Gray500
import com.jamison.codeing.compose.ui.theme.StatusBar
import com.jamison.codeing.compose.widget.AppTopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * @FileName InstagramScreen
 * @Description
 * @Author arjun
 * @Date 2022/6/20 15:18
 * @Version
 */

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun InstagramScreen(navController: NavController) {

    val tabs = listOf(
        TabItem(R.drawable.ic_grid, "GRID"),
        TabItem(R.drawable.ic_igtv, "IGTV"),
        TabItem(R.drawable.ic_mention, "Mention")
    )
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            AppTopBar(
                title = {
                    Text(text = "Instagram UI", color = Color.White, fontSize = 18.sp)
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
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            val screenHeight = maxHeight
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState)
            ) {

                ProfileSection()
                Column(modifier = Modifier.height(screenHeight)) {
                    TabView(tabs = tabs, pagerState, scope)
                    val nestedScrollConnection = remember {
                        object : NestedScrollConnection {
                            override fun onPreScroll(
                                available: Offset,
                                source: NestedScrollSource
                            ): Offset {

                                return takeIf {available.y > 0 && source == NestedScrollSource.Drag}?.let {
                                    Offset(
                                    x = 0f,
                                    y = -scrollState.dispatchRawDelta(-available.y)
                                )
                                }?: Offset.Zero
//                                return if (available.y > 0) Offset.Zero else Offset(
//                                    x = 0f,
//                                    y = -scrollState.dispatchRawDelta(-available.y)
//                                )
                            }
                        }
                    }
                    val nestedScrollDispatcher = remember { NestedScrollDispatcher() }
                    HorizontalPager(
                        state = pagerState,
                        count = tabs.size,
                        modifier = Modifier
                            .fillMaxHeight()
                            .nestedScroll(nestedScrollConnection, nestedScrollDispatcher)
                    ) {page->
                        ListDataContent(size =50 , title = tabs[page].title)
                    }
                }
            }
        }
    }
}


@Composable
fun TabView(tabs: List<TabItem>, pagerState: PagerState, scope: CoroutineScope) {
    val inactiveColor = Gray500
    val activeColor = Blue500
    androidx.compose.material.TabRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color.White,
        divider = {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp), color = DividerColor
            )
        },
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                color = activeColor

            )
        }
    ) {
        tabs.forEachIndexed { index, tabItem ->
            val isSelected = pagerState.currentPage == index
            Tab(selected = isSelected,
                onClick = {
                    scope.launch { pagerState.animateScrollToPage(index) }
                }) {
                Icon(
                    painter = painterResource(id = tabItem.icon),
                    contentDescription = tabItem.title,
                    tint = if (isSelected) activeColor else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(22.dp)
                )
            }
        }

    }
}

@Composable
private fun ProfileSection(modifier: Modifier = Modifier) {
    val viewModel: ImagesViewModel = viewModel()
    Surface(color = Color.White) {
        Column(modifier = modifier.fillMaxWidth()) {
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = viewModel.images[0]),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                        .aspectRatio(1f, matchHeightConstraintsFirst = true)
                        .weight(3f)
                        .border(
                            1.dp,
                            color = Color.LightGray,
                            shape = CircleShape
                        )
                        .padding(3.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop

                )
                Spacer(modifier = Modifier.width(16.dp))
                StatSection(modifier = Modifier.weight(7F))
            }

            Spacer(modifier = Modifier.height(12.dp))
            ProfileDescription(
                displayName = "ghasem",
                description = "UB \uD83D\uDCBB\uD83D\uDCF1\n" +
                        "My favorites \uD83C\uDFA5\uD83D\uDCD6\uD83C\uDFA8\n" +
                        "If they treat you like one of their choices, make them one of your memories.",
                followedBy = listOf("hassan__rouhani"),
                otherCount = 26
            )
        }
    }


}

@Composable
private fun StatSection(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ProfileStat(value = "9", title = "Posts")
        ProfileStat(value = "349", title = "Followers")
        ProfileStat(value = "303", title = "Following")
    }
}

@Composable
private fun ProfileStat(
    value: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = value,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
private fun ProfileDescription(
    displayName: String,
    description: String,
    followedBy: List<String>,
    otherCount: Int
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 20.sp
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = displayName,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = description,
            color = MaterialTheme.colors.onBackground,
            fontSize = 14.sp,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = "See Translation",
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        if (followedBy.isNotEmpty()) {
            Text(
                color = MaterialTheme.colors.onBackground,
                fontSize = 14.sp,
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Bold,
                    )
                    append("Followed by ")
                    followedBy.forEachIndexed { index, name ->
                        pushStyle(boldStyle)
                        append(name)
                        pop()
                        if (index < followedBy.size - 1) {
                            append(", ")
                        }
                    }
                    if (otherCount > 2) {
                        append(" and ")
                        pushStyle(boldStyle)
                        append("$otherCount others")
                    }
                },
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }
    }
}

@Composable
private fun ListDataContent(size: Int,title:String) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(size) { index ->
            Text(
                text = "$title Data Item: $index",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}


