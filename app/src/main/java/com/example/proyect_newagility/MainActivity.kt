package com.example.proyect_newagility

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.proyect_newagility.ui.theme.Proyect_NewAgilityTheme
import kotlinx.coroutines.launch
import model.NavigationItem
import model.Screens
import model.Usersingleton

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
                    MainApp()
                }
            }
        }
    }

}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainApp(){
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navigationController = rememberNavController()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            SpacerGeneral()
            ModalDrawerSheet {
                ModalDrawerItems(navigationController){scope.launch { drawerState.close() }}
            }
        },
        gesturesEnabled = true,
        content = {
                NavHost(navController = navigationController, startDestination = Screens.LoginScreen.route) {

                composable(
                    Screens.LoginScreen.route
                ) {
                    MainLogin(navigationController)
                }
                composable(
                    Screens.Dashboard.route,
                    arguments = listOf(navArgument("user"){type= NavType.StringType})
                    ) {
                    DashboardLayout(
                        navigationController,
                        drawerState,
                        it.arguments?.getString("user").orEmpty()
                    )
                }
                composable(
                    Screens.CreateProyect.route
                ) {
                    MainCreateProyect(navigationController)
                }
                composable(
                    Screens.Lists.route
                ) {
                    ListsLayout(navigationController,drawerState)
                }
                composable(
                    Screens.User.route
                ) {
                    UserLayout(navigationController, drawerState)
                }
            }
        }
    )
}

@Composable
fun ModalDrawerItems(navigationController: NavHostController, onCloseDrawer:() -> Unit) {
    val UserNow = Usersingleton.getUserValue()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val items = listOf(
        NavigationItem(
            title = "Dashboard",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            destination = Screens.Dashboard.createRoute(UserNow)
        ),
        NavigationItem(
            title = "Listas",
            selectedIcon = Icons.Filled.List,
            unselectedIcon = Icons.Outlined.List,
            destination = Screens.Lists.route
        ),
        NavigationItem(
            title = "Ajustes de Usuario",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
            destination = Screens.User.route)
    )
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically){
        ImageLogo(modifier = Modifier)
        Text(text = "New Agility",
            fontSize = 40.sp)
    }
    Divider()
    SpacerGeneral()
    items.forEachIndexed { index, item ->

        NavigationDrawerItem(
            label = {
                Text(text = item.title)
            },
            selected = index == selectedItemIndex,
            onClick = {
                onCloseDrawer()
                navigationController.navigate(item.destination)
                selectedItemIndex = index
            },
            icon = {
                Icon(
                    imageVector = if (index == selectedItemIndex) {
                        item.selectedIcon
                    } else item.unselectedIcon,
                    contentDescription = item.title,
                )
            },
            badge = {
                item.badgeCount?.let {
                    Text(text = item.badgeCount.toString())
                }
            },
            modifier = Modifier
                .padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }

}
