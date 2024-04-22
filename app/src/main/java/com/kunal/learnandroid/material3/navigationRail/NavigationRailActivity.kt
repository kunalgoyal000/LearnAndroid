package com.kunal.learnandroid.material3.navigationRail

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Chat
import androidx.compose.material.icons.automirrored.outlined.Chat
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
class NavigationRailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                val items = listOf(
                    NavigationItem(
                        title = "Home",
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                        hasNews = false
                    ),
                    NavigationItem(
                        title = "Chat",
                        selectedIcon = Icons.AutoMirrored.Filled.Chat,
                        unselectedIcon = Icons.AutoMirrored.Outlined.Chat,
                        hasNews = false,
                        badgeCount = 45
                    ),
                    NavigationItem(
                        title = "Settings",
                        selectedIcon = Icons.Filled.Settings,
                        unselectedIcon = Icons.Outlined.Settings,
                        hasNews = true
                    ),
                )

                val windowClass = calculateWindowSizeClass(this)
                val showNavigationRail = windowClass.widthSizeClass != WindowWidthSizeClass.Compact
                var selectedItemIndex by rememberSaveable {
                    mutableIntStateOf(0)
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        bottomBar = {
                            if (!showNavigationRail) {
                                //NavigationBar()
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    ) { values ->
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(values)
                                .padding(
                                    start = if (showNavigationRail) 80.dp else 0.dp
                                )
                        ) {
                            items(100) {
                                Text(
                                    text = "Item$it",
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
                if (showNavigationRail) {
                    NavigationSideBar(
                        items = items,
                        selectedItemIndex = selectedItemIndex,
                        onNavigate = { selectedItemIndex = it },
                    )
                }
            }
        }
    }
}


@Composable
private fun NavigationSideBar(
    items: List<NavigationItem>,
    selectedItemIndex: Int,
    onNavigate: (Int) -> Unit
) {
    NavigationRail(
        header = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
            FloatingActionButton(
                onClick = { /*TODO*/ },
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        },
        modifier = Modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .offset((-1).dp)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Bottom)
        ) {
            items.forEachIndexed { index, item ->
                NavigationRailItem(
                    selected = selectedItemIndex == index,
                    onClick = { onNavigate(index) },
                    icon = {
                        NavigationIcon(item = item, selected = selectedItemIndex == index)
                    },
                    label = {
                        Text(text = item.title)
                    }
                )
            }
        }
    }
}

@Composable
private fun NavigationIcon(
    item: NavigationItem,
    selected: Boolean
) {
    BadgedBox(
        badge = {
            if (item.badgeCount != null) {
                Badge {
                    Text(text = item.badgeCount.toString())
                }
            } else if (item.hasNews) {
                Badge()
            }
        }
    ) {
        Icon(
            imageVector = if (selected) {
                item.selectedIcon
            } else {
                item.unselectedIcon
            },
            contentDescription = item.title
        )
    }
}