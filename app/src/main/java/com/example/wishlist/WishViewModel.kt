package com.example.wishlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Delete
import com.example.wishlist.data.Wish
import com.example.wishlist.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository=Graph.wishRepository
):ViewModel() {

    var wishTitle by mutableStateOf("")
    var wishDescription by mutableStateOf("")

    fun onWishTitleChange(newChange:String){
        wishTitle=newChange
    }
    fun onWishDescriptionChange(newChange: String){
        wishDescription=newChange
    }

    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWishes()
        }

        fun addWish(wish:Wish){
            viewModelScope.launch(Dispatchers.IO) {
                wishRepository.addWish(wish)
            }

            fun getWishById(id:Long):Flow<List<Wish>>{
                return wishRepository.getWishById(id)
            }
            fun updateWish(wish:Wish){
                viewModelScope.launch(Dispatchers.IO) {
                    wishRepository.updateWish(wish)
                }
            }
            fun deleteWish(wish:Wish){
                viewModelScope.launch(Dispatchers.IO) {
                    wishRepository.deleteWish(wish)
                    getAllWishes=wishRepository.getAllWishes()
                }
            }



        }

    }

}