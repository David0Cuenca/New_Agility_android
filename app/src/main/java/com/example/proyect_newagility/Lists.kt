package com.example.proyect_newagility

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import model.ProyectDetails

@Composable
fun ListsProyectMain(navController: NavController) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Primary)
        .padding(9.dp)){
        Header(modifier = Modifier.align(Alignment.TopEnd))
        ListsBody(modifier = Modifier.align(Alignment.Center))

    }


}

@Composable
fun ListsBody(modifier: Modifier){

    val context = LocalContext.current
    val rvStater = rememberLazyListState()
    val coroutine = rememberCoroutineScope()

        Column(modifier
            .background(color = Color.Transparent)
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
    val showButton by remember {
        derivedStateOf { rvStater.firstVisibleItemIndex > 0 }
    }
    val offset by remember {
        derivedStateOf { rvStater.firstVisibleItemScrollOffset }
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
            .align(Alignment.CenterHorizontally)
            .padding(9.dp)){
            Text(text = proyectdetails.name)
            Spacer()
            Text(text = proyectdetails.endDate)

        }


    }
}

fun getProyectDetails(): List<ProyectDetails> {
    return listOf(
        ProyectDetails("Proyecto Amazon", "13/12/2022"),
        ProyectDetails("Proyecto Lidel", "14/12/2022"),
        ProyectDetails("Proyecto Aldi", "15/12/2022"),
        ProyectDetails("Proyecto Venezuela", "16/12/2022"),
    )
}

@Preview(showBackground = true)
@Composable
fun ListsPreview() {
    Proyect_NewAgilityTheme {
        ListsProyectMain(navController = rememberNavController())
    }
}