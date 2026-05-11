package org.example.project

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.example.project.articles.ArticlesViewModel
import androidx.navigation.compose.NavHost
import org.example.project.articles.Article
import org.example.project.screens.AboutScreen
import org.example.project.screens.ArticlesScreen
import org.example.project.screens.Screen
import androidx.navigation.compose.composable


@Composable
fun ApplicationScaffold() {
    var navController = rememberNavController()

    Scaffold {
        AppNavHost (
            navController = navController,
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
        )
    }

}

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ARTICLES.route,
        modifier = modifier,
    ) {
        composable(Screen.ARTICLES.route) {
            ArticlesScreen(
                onAboutButtonClick = { navController.navigate(Screen.ABOUT_DEVICE.route) },
            )
        }

        composable(Screen.ABOUT_DEVICE.route) {
            AboutScreen(onUpButtonClick = { navController.popBackStack() })
        }
    }
}