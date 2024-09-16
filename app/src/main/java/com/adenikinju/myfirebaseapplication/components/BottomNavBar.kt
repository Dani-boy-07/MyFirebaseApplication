package com.adenikinju.myfirebaseapplication.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.adenikinju.myfirebaseapplication.Screen.Screen

@Composable
fun BottomNavBar(navController: NavHostController) {
    val items = listOf(
        Screen.Movies,
        Screen.Shows,
        Screen.Popular
    )
    BottomNavigation(
        backgroundColor = Color(21,20,20),
        contentColor = Color.White
    ) {
        val navBackStackEntry = navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry.value?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { screen.icon?.let { ImageVector.vectorResource(id = it) }
                    ?.let { Icon(imageVector = it, contentDescription = null) } },
                label = { screen.title?.let { Text(it) } },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        // Avoid multiple copies of the same destination
                        launchSingleTop = true
                        // Pop up to the start destination of the graph to avoid building up a large stack of destinations
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        // Prevents popping up to the start destination when clicking the bottom navigation item again
                        restoreState = true
                    }
                }
            )
        }
    }
}
