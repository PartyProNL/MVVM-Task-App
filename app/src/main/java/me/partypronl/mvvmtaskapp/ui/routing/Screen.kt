package me.partypronl.mvvmtaskapp.ui.routing

sealed class Screen(val route: String) {
    object Home: Screen("home")
}