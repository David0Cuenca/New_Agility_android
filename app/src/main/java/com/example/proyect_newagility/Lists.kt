package com.example.proyect_newagility

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.proyect_newagility.ui.theme.Blue
import com.example.proyect_newagility.ui.theme.Primary
import com.example.proyect_newagility.ui.theme.Typography
import kotlinx.coroutines.launch
import model.ProyectDetails

@Composable
fun ListsLayout(navController: NavController, drawerState: DrawerState) {

  ScaffoldList(navigationController = navController,drawerState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldList(navigationController: NavController,drawerState: DrawerState){
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopBarList() {scope.launch { drawerState.open() }}},
        content = {innerPadding ->
            BodyList(innerPadding)
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarList(onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text("Lista de Proyectos") },
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BodyList(innerPaddingValues: PaddingValues){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(innerPaddingValues),
        verticalArrangement = Arrangement.spacedBy(15.dp)) {
        ListsBody(Modifier.fillMaxWidth())
    }
}

@Composable
fun ListsBody(modifier: Modifier){

    val context = LocalContext.current
    val rvStater = rememberLazyListState()
    val coroutine = rememberCoroutineScope()

        Column(modifier
            .background(color = Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn (modifier
                .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                state = rvStater
            ){

                items(getProyectDetails()) {
                    ItemProyect(it){
                        Toast.makeText(context, it.name, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
}

@Composable
fun ItemProyect(proyectdetails: ProyectDetails, onItemSelected: (ProyectDetails) -> Unit) {
    Card(colors = CardDefaults.cardColors(containerColor = Blue),
        shape = RoundedCornerShape(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemSelected(proyectdetails) }){

        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(9.dp),
            horizontalAlignment = Alignment.CenterHorizontally){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(modifier = Modifier.size(100.dp),
                    imageVector = Icons.Outlined.Build,
                    contentDescription = "icon")
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Nombre del proyecto",
                    style = Typography.titleMedium,
                    color = Color.Black)
                Text(text = "Final del proyecto",
                    style = Typography.titleMedium,
                    color = Color.Black)
            }
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = proyectdetails.name,
                    style = Typography.bodyLarge,
                    color = Color.Black)
                Text(text = proyectdetails.endDate,
                    style = Typography.bodyLarge,
                    color = Color.Black)
            }
        }
    }
}

fun getProyectDetails(): List<ProyectDetails> {
    return listOf(
        ProyectDetails("Amazon", "13/12/2022"),
        ProyectDetails("Lidel", "14/12/2022"),
        ProyectDetails("Aldi", "15/12/2022"),
        ProyectDetails("Venezuela", "16/12/2022"),
        ProyectDetails("Venezuela", "16/12/2022"),
        ProyectDetails("Venezuela", "16/12/2022"),
        ProyectDetails("Venezuela", "16/12/2022")
    )
}

/*
@Preview(showBackground = true)
@Composable
fun ListsPreview() {
    Proyect_NewAgilityTheme {
        ListsLayout(navController = rememberNavController(), drawerState = DrawerValue)
    }
}
*/
