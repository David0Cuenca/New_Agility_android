package com.example.proyect_newagility

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.proyect_newagility.ui.theme.Blue
import com.example.proyect_newagility.ui.theme.Primary
import com.example.proyect_newagility.ui.theme.Proyect_NewAgilityTheme
import model.Screens

@Composable
fun DashboardLayout(navigationController: NavHostController, orEmpty: String) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Primary)
        .padding(9.dp)
    ){
        Navbar(modifier = Modifier.align(Alignment.TopCenter))
        BodyDashboard(modifier = Modifier.align(Alignment.Center))
        Footer(modifier = Modifier.align(Alignment.BottomEnd),navigationController)
    }
}
@Composable
fun Navbar(modifier: Modifier){
    TopBarNav { }
}
@Composable
fun BodyDashboard(modifier: Modifier){
   ElevatedCard (modifier
       .fillMaxWidth(),
       elevation = CardDefaults.cardElevation(
           defaultElevation = 6.dp
       ),
       colors = CardDefaults.cardColors(
           containerColor = Blue
       )) {
       Column (modifier.padding(15.dp)){
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
           Row(modifier){
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null,Modifier.size(150.dp))
               Column(modifier.align(Alignment.CenterVertically)) {
                   Text(text = "Hay un total de trabajos: ")
                   Text(text = "10")
               }

           }
       }
   }
}

@Composable
fun Footer(modifier: Modifier,navigationController: NavHostController) {
    Box(modifier
        .fillMaxWidth()) {
        FloatingActionButton(
            onClick = { navigationController.navigate(Screens.CreateProyect.route) },
            modifier.align(Alignment.BottomEnd),
            containerColor = Blue,
        ) {
            Icon(imageVector = Icons.Default.Add,contentDescription = null, tint = Primary)
        }
    }

}


@Composable
fun MyNavigationDrawer(onCloseDrawer: () -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        repeat(5) {
            Text(
                text = "Opción ${it + 1}",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onCloseDrawer() }
            )
        }
    }
}


//Preview
/*
@Composable
@Preview (showBackground = true)
fun DashboardLayoutPreview(){
    DashboardLayout()
}
*/


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