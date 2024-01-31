package model

sealed class Screens (val route: String) {
    object LoginScreen: Screens("login_screen")
    object Dashboard:Screens("dashboard/{username}"){
        fun createRoute(username:String)= "dashboard/$username"
    }
    object CreateProyect:Screens("create_proyect")
    object Lists:Screens("lists")
}