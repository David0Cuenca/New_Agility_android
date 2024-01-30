package com.example.proyect_newagility

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyect_newagility.ui.theme.Blue
import com.example.proyect_newagility.ui.theme.Primary
import kotlinx.coroutines.launch
import model.NavigationItem
import model.Screens



@Composable
fun DashboardLayout(navigationController: NavHostController, /*orEmpty: String*/) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Primary)
        .padding(9.dp)
    ){
        ScaffoldMain(navigationController)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldMain(navigationController: NavHostController){
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Spacer()
            ModalDrawerSheet {
                NavigationDrawerDashboard{scope.launch { drawerState.close() }}
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                TopBarNav() {scope.launch { drawerState.open() }}},
            content = {innerPadding ->
                BodyDashboard(innerPadding)
            },
            floatingActionButton = {
                Footer (
                    navigationController = navigationController
                ) },
            floatingActionButtonPosition = FabPosition.End
        )
    }
}

@Composable
fun NavigationDrawerDashboard(onCloseDrawer:() -> Unit) {
    val scope = rememberCoroutineScope()
    val selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val items = listOf(
        NavigationItem(
            title = "All",
            selectedIcon = Icons.Filled.List,
            unselectedIcon = Icons.Outlined.List
        ),
        NavigationItem(
            title = "Urgent",
            selectedIcon = Icons.Filled.Info,
            unselectedIcon = Icons.Outlined.Info
        ))

    items.forEachIndexed { index, item ->

        NavigationDrawerItem(
            label = {
                Text(text = item.title)
                    },
            selected = index == selectedItemIndex,
            onClick = {
                onCloseDrawer()
            },
            icon = {
                Icon(
                    imageVector = if (index == selectedItemIndex) {
                        item.selectedIcon
                    } else item.unselectedIcon,
                    contentDescription = item.title
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BodyDashboard(innerPadding:PaddingValues){

   ElevatedCard (modifier = Modifier
       .fillMaxWidth()
       .consumeWindowInsets(innerPadding),
       elevation = CardDefaults.cardElevation(
           defaultElevation = 6.dp
       ),
       colors = CardDefaults.cardColors(
           containerColor = Blue
       )) {
       Column (modifier=Modifier.padding(15.dp)){
           Row {
               Icon(
                   imageVector = Icons.Default.DateRange,
                   contentDescription = null)
               Spacer()
               Text(
                   text = "Reporte diario",
                   fontWeight = FontWeight.Bold,
                   color = Primary
               )
           }
           Spacer()
           Row(modifier=Modifier) {
               Icon(
                   imageVector = Icons.Default.ShoppingCart,
                   contentDescription = null, Modifier.size(150.dp)
               )
               Column(modifier=Modifier.align(Alignment.CenterVertically)) {
                   Text(text = "Hay un total de trabajos: ")
                   Text(text = "10")
               }
           }
       }
   }
}

@Composable
fun Footer(navigationController: NavHostController) {
        FloatingActionButton(
            onClick = { navigationController.navigate(Screens.CreateProyect.route) },
            containerColor = Blue,
        ) {
            Icon(imageVector = Icons.Default.Add,contentDescription = null, tint = Primary)
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNav(onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text("Dashboard") },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Primary,
            actionIconContentColor = Color.White,
            titleContentColor = Color.White),
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(Icons.Filled.Menu,
                    contentDescription = "Desc",
                    tint = Color.White)
            }
        }
    )
}