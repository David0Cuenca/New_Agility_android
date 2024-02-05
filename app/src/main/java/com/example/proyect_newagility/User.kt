package com.example.proyect_newagility

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyect_newagility.ui.theme.Blue
import com.example.proyect_newagility.ui.theme.Primary
import com.example.proyect_newagility.ui.theme.Proyect_NewAgilityTheme
import kotlinx.coroutines.launch
import model.Usersingleton

@Composable
fun UserLayout(navController: NavController, drawerState: DrawerState) {

    ScaffoldUser(navController,drawerState)
}

@Composable
fun ScaffoldUser(navigationController: NavController,drawerState: DrawerState){
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopBarUser() {scope.launch { drawerState.open() }}
        },
        content = {innerPadding ->
            BodyUser(innerPadding)
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarUser(onClickDrawer: () -> Unit) {
    TopAppBar(
        title = { Text("Cuenta") },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Primary,
            actionIconContentColor = Color.White,
            titleContentColor = Color.White),
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }) {
                Icon(
                    Icons.Filled.Menu,
                    contentDescription = "Desc",
                    tint = Color.White)
            }
        }
    )
}

@Composable
fun BodyUser(innerPaddingValues: PaddingValues){
    Column(
        modifier = Modifier
            .padding(innerPaddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Blue
            )
        ) {
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Icon for the user",
                        Modifier.size(200.dp),
                        tint = Color.Black
                    )
                }
                TextField(
                    value = Usersingleton.getUserValue(),
                    onValueChange = {},
                    enabled = false,
                    label = { Text("Nombre") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black,
                        unfocusedLabelColor = Color.Black
                    )
                )
                TextField(
                    value = "",
                    onValueChange = {},
                    enabled = false,
                    label = { Text("Segundo Nombre") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black,
                        unfocusedLabelColor = Color.Black
                    )
                )
                TextField(
                    value = Usersingleton.getUserValue()+"@gmail.com",
                    onValueChange = {},
                    enabled = false,
                    label = { Text("Email") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black,
                        unfocusedLabelColor = Color.Black
                    )
                )
                val context = LocalContext.current
                Button(
                    onClick = { Toast.makeText(context, "Saliendo...", Toast.LENGTH_SHORT).show() }
                ) {
                    Text(text = "Cerrar Sesión")
                    Spacer(modifier = Modifier.size(5.dp))
                    Icon(imageVector = Icons.Filled.ExitToApp, contentDescription = "Logo salir")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserPreview() {
    Proyect_NewAgilityTheme {
        UserLayout(navController = rememberNavController(), drawerState = rememberDrawerState(
            initialValue = DrawerValue.Closed))
    }
}

