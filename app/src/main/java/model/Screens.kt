package model

sealed class Screens (val route: String) {
    object LoginScreen: Screens("login_screen")
    object Dashboard:Screens("dashboard")
    object CreateProyect:Screens("create_proyect")
    object Lists:Screens("lists")
}