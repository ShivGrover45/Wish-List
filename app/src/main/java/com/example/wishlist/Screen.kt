package com.example.wishlist

sealed class Screen(val route:String) {

    object homeScreen:Screen("home-screen")
    object addScreen:Screen("add-screen")

}