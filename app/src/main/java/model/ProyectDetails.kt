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
    val toGo: String? = null
)