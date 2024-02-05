package model

sealed class Screens (val route: String) {
    object LoginScreen: Screens("login_screen")
    object Dashboard:Screens("dashboard/{user}") {
        fun createRoute(user: String) = "dashboard/$user"
    }
    object CreateProyect:Screens("create_proyect")
    object Lists:Screens("lists")
    object User:Screens("User")
}