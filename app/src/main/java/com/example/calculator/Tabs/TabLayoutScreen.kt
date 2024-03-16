package com.example.calculator.Tabs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayoutScreen() {
    val tabs = getTabs()
    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val coroutineScope = rememberCoroutineScope()

    Scaffold(bottomBar = {
        TabRow(selectedTabIndex = pagerState.currentPage) {
            tabs.forEachIndexed { index, tabItem ->
                Tab(
                    selected = index == pagerState.currentPage,
                    text = { Text(text = tabItem.title) },
                    icon = {
                        Icon(
                            imageVector = tabItem.icon,
                            contentDescription = ""
                        )
                    },
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    }
                )
            }
        }
    }) { paddingValues ->
        HorizontalPager(
            state = pagerState, modifier = Modifier
                .padding(paddingValues)
        ) {
            tabs[pagerState.currentPage].screen()
        }
    }
}

private fun getTabs() = listOf(
    TabItem(
        title = "Account",
        icon = Icons.Filled.AccountBox,
        screen = { TabScreen(content = "Account Page") }
    ),
    TabItem(
        title = "Favorite",
        icon = Icons.Filled.Favorite,
        screen = { TabScreen(content = "Favorite list") }
    ),
    TabItem(
        title = "Place",
        icon = Icons.Filled.Place,
        screen = { TabScreen(content = "Places") }
    )
)