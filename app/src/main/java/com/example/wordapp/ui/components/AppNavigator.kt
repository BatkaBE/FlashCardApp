package com.example.wordapp.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.wordapp.ViewModel.WordViewModel
import com.example.wordapp.ui.screen.MainScreen
import com.example.wordapp.ui.screen.SettingsScreen

@Composable
fun WordApp(
    navController: NavHostController,
    viewModel: WordViewModel,
    windowSize: WindowWidthSizeClass
) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        when (windowSize) {
            WindowWidthSizeClass.Compact -> {
                NavHost(
                    navController = navController,
                    startDestination = "mainScreen"
                ) {
                    composable("mainScreen") {
                        MainScreen(
                            navController = navController,
                            viewModel = viewModel,
                            showSettingsButton = false,
                        )
                    }
                    composable("settingsScreen") {
                        SettingsScreen(
                            navController = navController,
                            viewModel = viewModel,
                            showBackButton = true
                        )
                    }
                }
            }
            else -> {

                Row(Modifier.fillMaxSize()) {
                    MainScreen(
                        navController = navController,
                        viewModel = viewModel,
                        showSettingsButton = false
                    )
                    SettingsScreen(
                        navController = navController,
                        viewModel = viewModel,
                        showBackButton = true
                    )
                }
            }
        }
    }
}
