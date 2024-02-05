package com.example.proyect_newagility

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.proyect_newagility.ui.theme.Blue
import com.example.proyect_newagility.ui.theme.Pink
import com.example.proyect_newagility.ui.theme.Primary
import com.example.proyect_newagility.ui.theme.Typography
import kotlinx.coroutines.launch
import model.Screens
import model.Usersingleton
import model.getProyectDetails


@Composable
fun DashboardLayout(
    navigationController: NavHostController,
    drawerState: DrawerState,
    user: String
) {
    Usersingleton.setUser(user)
    val UserNow = Usersingleton.getUserValue()


    ScaffoldMain(navigationController,drawerState,UserNow)
}

@Composable
fun ScaffoldMain(
    navigationController: NavHostController,
    drawerState: DrawerState,
    user: String
){
    val scope = rememberCoroutineScope()
        Scaffold(
            topBar = {
                TopBarNav(onClickDrawer = { scope.launch { drawerState.open() } })
                     },
            content = {innerPadding ->
                BodyDashboard(innerPadding, user)
            },
            floatingActionButton = {
                FooterDashboard (
                    navigationController = navigationController
                ) },
            floatingActionButtonPosition = FabPosition.End
        )
}


@Composable
fun BodyDashboard(innerPaddingValues: PaddingValues, user: String){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(color = Color.Black)
            .padding(innerPaddingValues),
        horizontalAlignment = Alignment.CenterHorizontally){

        Text(
            text = "Bienvenido ${user.capitalize()}",
            modifier = Modifier.padding(bottom = 10.dp),
            style = Typography.titleMedium,
            fontSize = 30.sp,
            color = Color.White
        )
        Column(verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            CardTotal()
            CardCalendar()
            CardPriority()
        }
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
        Column(modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()) {
            TitleCard()
            BodyCard()

        }
    }
}

@Composable
fun TitleCard() {
    Row {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = "Date",
            tint = Color.Black,
            modifier = Modifier.fillMaxHeight()
        )
        SpacerGeneral()
        Text(
            text = "Reporte",
            style = Typography.titleMedium,
            color = Color.Black
        )
    }
}

@Composable
fun BodyCard() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null, Modifier.size(150.dp),
            tint = Color.Black
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxWidth()
        ) {
            Text(text = "Proyectos Pendientes: ",
                style = Typography.titleMedium,
                color = Color.Black
            )
            Text(text = getProyectDetails().size.toString(),
                style = Typography.titleLarge,
                color = Color.Black,
            )
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



/*
@Preview
@Composable
fun DashboardPreview() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        ScaffoldMain(navigationController = rememberNavController(), drawerState = drawerState, user = user.getUser)
    }
*/
