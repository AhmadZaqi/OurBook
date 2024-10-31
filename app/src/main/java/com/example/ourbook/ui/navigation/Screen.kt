package com.example.ourbook.ui.navigation

import com.example.ourbook.model.DataUser

sealed class Screen(val route: String){
    data object Home: Screen("home")
    data object Add: Screen("save")
    data object About: Screen("about")
    data object Edit: Screen("save/{existingDataUserID}"){
        fun createRoute(id: Int) = "save/$id"
    }
    data object Detail: Screen("detail/{id}"){
        fun createRoute(id: Int) = "detail/$id"
    }
}

