package model

import androidx.compose.ui.graphics.vector.ImageVector


data class ProyectDetails(
    var name: String,
    var endDate: String
)

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val destination: String
)

fun getProyectDetails(): List<ProyectDetails> {
    return listOf(
        ProyectDetails("Amazon", "13/12/2022"),
        ProyectDetails("Lidel", "14/12/2022"),
        ProyectDetails("Aldi", "15/12/2022"),
        ProyectDetails("Venezuela", "16/12/2023"),
        ProyectDetails("Venezuela", "16/1/2023"),
        ProyectDetails("Venezuela", "16/10/2024"),
        ProyectDetails("Venezuela", "16/12/2024")
    )
}
