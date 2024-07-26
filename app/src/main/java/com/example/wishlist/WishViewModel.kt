package com.example.wishlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class WishViewModel:ViewModel() {

    var wishTitle by mutableStateOf("")
    var wishDescription by mutableStateOf("")

    fun onWishTitleChange(newChange:String){
        wishTitle=newChange
    }
    fun onWishDescriptionChange(newChange: String){
        wishDescription=newChange
    }

}