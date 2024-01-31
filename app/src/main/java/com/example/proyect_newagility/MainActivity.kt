package com.example.proyect_newagility

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.proyect_newagility.ui.theme.Proyect_NewAgilityTheme
import model.Screens

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyect_NewAgilityTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(){
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = Screens.LoginScreen.route) {
        composable(Screens.LoginScreen.route) {
            MainLogin(navigationController)
        }
        composable(Screens.Dashboard.route,
            /*arguments = listOf(navArgument("name") { type = NavType.StringType }*/
           ) {
            DashboardLayout(navigationController/*, it.arguments?.getString("name").orEmpty()*/)
        }
        composable(Screens.CreateProyect.route) {
            MainCreateProyect(navigationController)
        }
        composable(Screens.Lists.route) {
            ListsLayout(navigationController)
        }
    }
}