package com.example.wishlist.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {

    suspend fun addWish(wish:Wish){
        wishDao.addAWish(wish)
    }

    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }

    fun getAllWishes():Flow<List<Wish>> = wishDao.getAllWishes()

    fun getWishById(id:Long):Flow<List<Wish>> = wishDao.getWishById(id)

    suspend fun deleteWish(wish: Wish){
        wishDao.deleteWish(wish)
    }

}