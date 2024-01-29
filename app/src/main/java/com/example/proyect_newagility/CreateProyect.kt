package com.example.proyect_newagility

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
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
import com.example.proyect_newagility.ui.theme.Blue
import com.example.proyect_newagility.ui.theme.Primary
import com.example.proyect_newagility.ui.theme.Proyect_NewAgilityTheme
import java.time.Instant
import java.time.ZoneId

class CreateProyect : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Proyect_NewAgilityTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PreviewCreateProyect()
                }
            }
        }
    }
}


@Composable
fun MainCreateProyect(navController: NavController){



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
fun BodyCreate(modifier: Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        NameCreate()
        Spacer()
        DatePickerUtility()
        Spacer()
        BtnConfirmCreate()
    }
}

@Composable
fun BtnConfirmCreate() {
    OutlinedButton(
        onClick = {
            /*navigationController.navigate(Screens.LoginScreen.route)*/
        })
    {
        Text(text = "Crear Proyecto", color=Blue)
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
            disabledTextColor = Blue,
            focusedContainerColor = Color.Gray,
            unfocusedContainerColor = Color.Transparent,
            unfocusedLabelColor = Blue,
            focusedLabelColor = Color.Black,

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
        Text(text = "Fecha final del proyecto", color=Blue)
    }
    if(showDialoge) {
        DatePickerDialog(
            onDismissRequest = { false },
            confirmButton = {
                OutlinedButton(
                    onClick = {
                        showDialoge = false
                    }) {
                    Text(text = "Mostrar Fecha")
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
@Composable
@Preview
fun PreviewCreateProyect(){

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Primary)
        .padding(8.dp)){
        HeaderCreate(modifier = Modifier.align(Alignment.TopEnd))
        BodyCreate(modifier = Modifier.align(Alignment.Center))
    }
}

