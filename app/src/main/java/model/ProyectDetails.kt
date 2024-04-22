package model

import androidx.compose.ui.graphics.vector.ImageVector

data class ProyectDetails(
    var name: String,
    var endDate: String,
    var projectType: ProjectType,
    var priority: Int?
)

object Usersingleton{
    private var actualuser: String = ""

    fun setUser(newUser:String){
        actualuser = newUser
    }
    fun getUserValue(): String{
        return actualuser
    }
}

enum class ProjectType {
    GRUPAL,
    PERSONAL,
    PROTOTIPO,
    CONCEPTUAL,
    OTROS
}

data class NavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val destination: String
)

fun getProyectDetails(): List<ProyectDetails> {
    return listOf(
        ProyectDetails("Amazon", "13/12/2022", ProjectType.GRUPAL, 8),
        ProyectDetails("Lidel", "14/12/2022", ProjectType.PERSONAL, 5),
        ProyectDetails("Aldi", "15/12/2022", ProjectType.PROTOTIPO, null),
        ProyectDetails("Venezuela", "16/12/2023", ProjectType.CONCEPTUAL, 7),
        ProyectDetails("Clase", "16/01/2023", ProjectType.OTROS, 3),
        ProyectDetails("Investigacion", "16/10/2024", ProjectType.GRUPAL, 9),
        ProyectDetails("Regetton 2", "16/12/2024", ProjectType.PERSONAL, null)
    )
}
