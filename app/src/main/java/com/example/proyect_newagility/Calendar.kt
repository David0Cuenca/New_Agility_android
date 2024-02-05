
package com.example.proyect_newagility
/*
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable


*/
/*data class Event(
    val title: String,
    val date: LocalDate,
    val type: ProjectType,
    val duration: Int?
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthlyCalendar(
    events: List<Event>,
    onDateSelected: (LocalDate) -> Unit
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CalendarHeader(selectedDate, onDateChange = { selectedDate = it })

        Spacer(modifier = Modifier.height(16.dp))

        CalendarGrid(selectedDate = selectedDate, events = events, onDateSelected = onDateSelected)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarHeader(
    selectedDate: LocalDate,
    onDateChange: (LocalDate) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                val newDate = selectedDate.minusMonths(1)
                onDateChange(newDate)
            }
        ) {
            Icon(imageVector = Icons.Outlined.ArrowBack, contentDescription = "Previous Month")
        }

        Text(
            text = "${selectedDate.month.getDisplayName(TextStyle.FULL, Locale("es","Es")).uppercase(Locale.ROOT)} ${selectedDate.year}",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        IconButton(
            onClick = {
                val newDate = selectedDate.plusMonths(1)
                onDateChange(newDate)
            }
        ) {
            Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "Next Month")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarGrid(
    selectedDate: LocalDate,
    events: List<Event>,
    onDateSelected: (LocalDate) -> Unit
) {
    val yearMonth = YearMonth.from(selectedDate)
    val firstDayOfMonth = yearMonth.atDay(1)
    val daysInMonth = yearMonth.lengthOfMonth()

    val days = buildList {
        var day = firstDayOfMonth
        repeat(daysInMonth) {
            add(day)
            day = day.plusDays(1)
        }
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(days) { day ->
            CalendarDay(
                date = day,
                isSelected = day == selectedDate,
                events = events.filter { it.date == day },
                onDateSelected = onDateSelected
            )
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarDay(
    date: LocalDate,
    isSelected: Boolean,
    events: List<Event>,
    onDateSelected: (LocalDate) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .size(48.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
            .clickable { onDateSelected(date) }
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            modifier = Modifier
                .padding(8.dp)
                .clip(MaterialTheme.shapes.small)
                .background(
                    if (isSelected) MaterialTheme.colorScheme.primary
                    else Color.Transparent
                ),
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        events.forEach { event ->
            Text(
                text = event.title,
                modifier = Modifier
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(MaterialTheme.colorScheme.secondary)
                    .offset(y = (events.indexOf(event) + 1) * 20.dp)
                    .padding(start = 4.dp, end = 4.dp),
                color = Color.White,
                textAlign = TextAlign.Start
            )
        }
    }
}*//*


*/
/*    val events = getProyectDetails().map {
        Event(it.name, LocalDate.parse(it.endDate, DateTimeFormatter.ofPattern("dd/MM/yyyy")), it.projectType, it.priority)
    }
    MonthlyCalendar(events = events, onDateSelected = { selectedDate ->
        println("Selected date: $selectedDate")
    })*//*

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)

fun MonthlyCalendarPreview() {

    */
/*Kalendar(
        currentDay = null,
        kalendarType = KalendarType.Firey,
        modifier = Modifier,
        showLabel = true,
        events = KalendarEvents(),
        kalendarHeaderTextKonfig = null,
        kalendarColors = KalendarColors.default(),
        kalendarDayKonfig = KalendarDayKonfig.default(),
        daySelectionMode = DaySelectionMode.Single,
        dayContent = null,
        headerContent = null
    )*//*

}*/
