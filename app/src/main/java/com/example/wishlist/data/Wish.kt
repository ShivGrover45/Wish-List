package com.example.wishlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.annotation.processing.Generated


@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true)
    val id:Long=0L,
    @ColumnInfo("wish-title")
    val wish:String="",
    @ColumnInfo("wish-desc")
    val description:String=""
)
    object DummyWish {
        val wishList = listOf(
            Wish(id=1L,wish = "Noise Fit Pro", description = "An Android Smart Watch"),
            Wish(id = 2L, wish = "Deadpool 3",
                description = "Watch latest Deadpool and Wolverine")
        )
    }