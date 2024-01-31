package com.example.proyect_newagility

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.proyect_newagility.ui.theme.Blue
import com.example.proyect_newagility.ui.theme.Primary
import model.Screens
import java.time.Instant
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainCreateProyect(navController: NavController){

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Primary)
        .padding(8.dp)){
        HeaderCreate(modifier = Modifier.align(Alignment.TopEnd))
        BodyCreate(modifier = Modifier.align(Alignment.Center),navController)
    }

}



@Composable
fun HeaderCreate(modifier: Modifier) {
    Box (modifier){
        Icon(imageVector = Icons.Default.Close,
            contentDescription = "Close App",
            tint = Blue,
            modifier = Modifier.clickable { })
    }
}

//Body
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BodyCreate(modifier: Modifier, navController: NavController) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        NameCreate()
        Spacer()
        DatePickerUtility()
        Spacer()
        BtnConfirmCreate(navController)
    }
}

@Composable
fun BtnConfirmCreate(navController: NavController) {
    OutlinedButton(
        onClick = {

            navController.navigate(Screens.Dashboard.route)
        })
    {
        Text(text = "Crear Proyecto", color= Color.White)
    }
}



//Body Components

@Composable
fun NameCreate(){
    var name by rememberSaveable { mutableStateOf("") }
    TextField(
        value = name,
        onValueChange ={ name = it },
        maxLines = 1,
        singleLine = true,
        label = { Text("Nombre del Proyecto") },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.Transparent,
            unfocusedLabelColor = Color.White,
            focusedLabelColor = Color.White,

            ),
        modifier = Modifier.fillMaxWidth(),
        )

}


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerUtility() {
    val stateDataPicker = rememberDatePickerState()
    var showDialoge by remember {
        mutableStateOf(false)
    }
    OutlinedButton(
        onClick = {
            showDialoge = true
        }) {
        Text(text = "Fecha final del proyecto", color= Color.White)
    }
    if(showDialoge) {
        DatePickerDialog(
            onDismissRequest = { false },
            confirmButton = {
                OutlinedButton(
                    onClick = {
                        showDialoge = false
                    }) {
                    Text(text = "Mostrar Fecha", color = Color.White)
                }
            })
        {
            DatePicker(state = stateDataPicker)
        }
    }

    val date = stateDataPicker.selectedDateMillis
    date?.let {
        Spacer()
        val localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
        Text(text = "Fecha Seleccionada: ${localDate.dayOfMonth}/${localDate.monthValue}/${localDate.year}",
            color = Blue)
    }

}
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MainCreateProyectPreview(){
    MainCreateProyect(navController = rememberNavController())
}
