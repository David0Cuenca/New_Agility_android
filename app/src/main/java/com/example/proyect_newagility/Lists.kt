package com.example.proyect_newagility

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyect_newagility.ui.theme.Blue
import com.example.proyect_newagility.ui.theme.Primary
import com.example.proyect_newagility.ui.theme.Proyect_NewAgilityTheme
import com.example.proyect_newagility.ui.theme.Typography
import model.ProyectDetails

@Composable
fun ListsLayout(navController: NavController) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Primary)
        .padding(9.dp)){
        Header(modifier=Modifier.align(Alignment.TopEnd), navigationController = navController)
        ListsBody(modifier = Modifier.align(Alignment.Center))

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
            Text(
                text = "Lista de Proyectos",
                style = Typography.titleLarge,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier =Modifier.padding(10.dp)
            )
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

@Preview(showBackground = true)
@Composable
fun ListsPreview() {
    Proyect_NewAgilityTheme {
        ListsLayout(navController = rememberNavController())
    }
}