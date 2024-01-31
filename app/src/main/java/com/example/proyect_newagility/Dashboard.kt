package com.example.proyect_newagility

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.proyect_newagility.ui.theme.Blue
import com.example.proyect_newagility.ui.theme.Pink
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
                NavigationDrawerDashboard(navigationController){scope.launch { drawerState.close() }}
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
                FooterDashboard (
                    navigationController = navigationController
                ) },
            floatingActionButtonPosition = FabPosition.End
        )
    }
}

@Composable
fun NavigationDrawerDashboard(navigationController: NavHostController, onCloseDrawer:() -> Unit) {
    val scope = rememberCoroutineScope()
    val selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val items = listOf(
        NavigationItem(
            title = "Dasboard",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            destination = Screens.Dashboard.route
        ),
        NavigationItem(
            title = "Lists",
            selectedIcon = Icons.Filled.List,
            unselectedIcon = Icons.Outlined.List,
            destination = Screens.Lists.route
        ))
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .wrapContentHeight(),
        Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically){
        ImageLogo(modifier = Modifier)
        Text(text = "New Agility",
            fontSize = 40.sp,
            fontFamily = FontFamily.Serif,
            color = Color.White)
    }
    Divider()
    Spacer()
    items.forEachIndexed { index, item ->

        NavigationDrawerItem(
            label = {
                Text(text = item.title)
                    },
            selected = index == selectedItemIndex,
            onClick = {
                onCloseDrawer()
                navigationController.navigate(item.destination)
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BodyDashboard(innerPaddingValues: PaddingValues){
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
        .padding(innerPaddingValues),
    verticalArrangement = Arrangement.spacedBy(15.dp)) {
        CardTotal()
    }
}

@Composable
fun FooterDashboard(navigationController: NavHostController) {
        FloatingActionButton(
            onClick = { navigationController.navigate(Screens.CreateProyect.route) },
            containerColor = Pink,
        ) {
            Icon(imageVector = Icons.Default.Add,contentDescription = null, tint = Primary)
        }
}

@Composable
fun CardTotal(){
    Card(modifier = Modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Blue
        )
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Row {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date",
                    tint = Color.Black
                )
                Spacer()
                Text(
                    text = "Reporte diario",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            Spacer()
            Row(modifier = Modifier) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null, Modifier.size(150.dp),
                    tint = Color.Black
                )
                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text(text = "Hay un total de trabajos: ", color = Color.Black)
                    Text(text = getProyectDetails().size.toString(), color = Color.Black)
                }
            }
        }
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

@Preview
@Composable
fun dashboardPreview(){
    ScaffoldMain(navigationController = rememberNavController())
}