package com.example.wishlist

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlin.reflect.typeOf

@Composable
fun Navigation(viewModel: WishViewModel,
               navController: NavHostController= rememberNavController()){

    NavHost(navController = navController, startDestination =Screen.homeScreen.route ) {

        composable(Screen.homeScreen.route){
            HomeView(viewModel = viewModel, navController = navController)
        }

        composable(Screen.addScreen.route+"/{id}",
            arguments = listOf(
                navArgument("id"){
                type=NavType.LongType
                defaultValue=0L
                nullable=false
            }
        ))
                {entry->
                    val id =if(entry.arguments!=null) entry.arguments!!.getLong("id") else 0L

                    AddView(id =id , viewModel =viewModel ,navController=navController)
                }


    }

}