package com.example.proyect_newagility

import android.os.Build
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
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
import com.example.proyect_newagility.ui.theme.Typography
import model.ProjectType
import model.Screens
import model.Usersingleton
import java.time.Instant
import java.time.ZoneId

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainCreateProyect(navController: NavController){

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Primary)
        .padding(8.dp)){
        HeaderCreate(modifier = Modifier.align(Alignment.TopEnd),navController)
        BodyCreate(modifier = Modifier.align(Alignment.Center),navController)
    }

}



@Composable
fun HeaderCreate(modifier: Modifier,navController: NavController) {
    Box (modifier){
        Icon(imageVector = Icons.Default.Close,
            contentDescription = "Close App",
            tint = Blue,
            modifier = Modifier.clickable { navController.navigate(Screens.Dashboard.createRoute(Usersingleton.getUserValue())) })
    }
}

//Body
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BodyCreate(modifier: Modifier, navController: NavController) {
    Column(
        modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround) {
        NameCreate()
        PriorityAndTypeChecker()
        DatePickerUtility()
        SpacerGeneral()
        BtnConfirmCreate(navController)
    }
}

@Composable
fun BtnConfirmCreate(navController: NavController) {
    OutlinedButton(
        onClick = {
            navController.navigate(Screens.Dashboard.createRoute(Usersingleton.getUserValue()))
        })
    {
        Icon(
            imageVector = Icons.Sharp.Add,
            contentDescription = "Create Proyect",
            tint = Blue
        )
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
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedLabelColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Blue,
            unfocusedLabelColor = Blue
        ),
        modifier = Modifier.fillMaxWidth(),
        )

}

@Composable
fun PriorityAndTypeChecker(){
    val checkedState = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                checkedState.value = !checkedState.value
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
        ) {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = {
                checkedState.value = it
            },
            colors = CheckboxDefaults.colors(
                checkedColor = Blue,
                checkmarkColor = Color.Black,
                uncheckedColor = Color.White
            )
        )
        SpacerGeneral()
        Text(
            "Prioridad",
            color = Blue,
            style = Typography.bodyLarge
        )

        SpacerGeneral()

        var expanded by remember { mutableStateOf(false) }
        var selectedProjectType by remember { mutableStateOf("Tipos") }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Button(
                onClick = { expanded = true },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                )
            ) {
                Text(
                    selectedProjectType,
                    color = Color.Black,
                    style = Typography.bodyLarge
                )
                SpacerGeneral()
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown",
                    tint = Color.Black
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                Modifier.width(135.dp)

            ) {
                ProjectType.values().forEach { projectType ->
                    DropdownMenuItem(
                        text = { Text(projectType.name) },
                        onClick = {
                            selectedProjectType = projectType.name
                            checkedState.value = false
                        }
                    )
                }
            }
        }
    }



    if (checkedState.value) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ){
            var sliderPositions by remember { mutableStateOf(5f) }
            Slider(
                value = sliderPositions,
                onValueChange = {sliderPositions = it},
                valueRange = 0f..10f,
                steps = 10,
                colors = SliderDefaults.colors(
                    activeTrackColor = Blue,
                    thumbColor = Blue
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = sliderPositions.toInt().toString(), color = Color.White)
        }
    }
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
        Text(text = "Fecha final del proyecto", color= Blue)
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
            DatePicker(
                state = stateDataPicker,
                colors = DatePickerDefaults.colors(
                    todayContentColor = Color.White,
                    todayDateBorderColor = Color.White,
                    selectedDayContainerColor = Blue,
                )
            )
        }
    }

    val date = stateDataPicker.selectedDateMillis
    date?.let {
        SpacerGeneral()
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
