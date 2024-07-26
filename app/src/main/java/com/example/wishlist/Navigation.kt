package com.example.wishlist

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(viewModel: WishViewModel,
               navController: NavHostController= rememberNavController()){

    NavHost(navController = navController, startDestination =Screen.homeScreen.route ) {

        composable(Screen.homeScreen.route){
            HomeView(viewModel = viewModel, navController = navController)
        }

        composable(Screen.addScreen.route){
            AddView(id =0L , viewModel =viewModel ,navController=navController, color = MaterialTheme
                .colorScheme.background)
        }

    }

}