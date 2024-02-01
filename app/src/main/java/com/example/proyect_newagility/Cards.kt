
package com.example.proyect_newagility

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.proyect_newagility.ui.theme.Blue
import com.example.proyect_newagility.ui.theme.Typography
import model.getProyectDetails

@Composable
@Preview (showBackground = true)
fun showeverything(){
    Box(modifier = Modifier.wrapContentSize()){

        CardCalendar()
    }
}

@Composable
fun CardPriority() {
    Card(modifier = Modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Blue
        )
    ) {
        Column(modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()) {
            TitleBodyCardPriority()

        }
    }
}


@Composable
fun CardCalendar(){
    Card(modifier = Modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Blue
        )
    ) {
        Column(modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()) {
            TitleBodyCardCalendar()

        }
    }
}


@Composable
fun TitleBodyCardPriority() {
    var expanded by remember { mutableStateOf(false) }
    var sortOrder by remember { mutableStateOf(true) }
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Row (Modifier.weight(1f)){
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Date",
                tint = Color.Black,
            )
            SpacerGeneral()
            Text(
                text = "Prioridad de los proyectos",
                style = Typography.titleMedium,
                color = Color.Black
            )
        }
        Row {
            IconButton(
                onClick = { expanded = true }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown",
                    tint = Color.Black
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Menor a mayor") },
                    onClick = {
                        expanded = false
                        sortOrder = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Mayor a menor") },
                    onClick = {
                        expanded = false
                        sortOrder = true
                    }
                )
            }
        }
    }

    Divider(color = Color.Black)
    BodyCardPriority(sortOrder)
}

@Composable
fun BodyCardPriority(sortOrder: Boolean) {
    val proyectDetails = if (sortOrder) {
        getProyectDetails().sortedByDescending { it.priority?.toInt() }.take(4)
    } else {
        getProyectDetails().sortedBy { it.priority?.toInt() }.take(4)
    }

    proyectDetails.forEach { proyectItem ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = proyectItem.name,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            Text(
                text = proyectItem.priority?.toString()?: "0",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
    }
}


@Composable
fun TitleBodyCardCalendar() {
    var expanded by remember { mutableStateOf(false) }
    var sortOrder by remember { mutableStateOf(false) }
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Row (Modifier.weight(1f)){
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Date",
                tint = Color.Black,
            )
            SpacerGeneral()
            Text(
                text = "Proyectos a la espera",
                style = Typography.titleMedium,
                color = Color.Black
            )
        }
        Row {
            IconButton(
                onClick = { expanded = true }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "Dropdown",
                    tint = Color.Black
                    )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Menor a mayor") },
                    onClick = {
                        expanded = false
                        sortOrder = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Mayor a menor") },
                    onClick = {
                        expanded = false
                        sortOrder = true
                    }
                )
            }
        }
    }

    Divider(color = Color.Black)
    BodyCardCalendar(sortOrder)
}

@Composable
fun BodyCardCalendar(sortOrder: Boolean) {
    val proyectDetails = if (sortOrder) {
        getProyectDetails().take(4).sortedByDescending { it.endDate }
    } else {
        getProyectDetails().take(4).sortedBy { it.endDate }
    }

        proyectDetails.forEach { proyectItem ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = proyectItem.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black
                )
                Text(
                    text = proyectItem.endDate,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
            }
    }
}