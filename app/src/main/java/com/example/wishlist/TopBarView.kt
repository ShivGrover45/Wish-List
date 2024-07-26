package com.example.wishlist

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}
) {
    val navigationIcon:(@Composable () -> Unit)= {

        if (!title.contains("WishList")){
            IconButton(onClick = onBackNavClicked) {
                Icon(imageVector =Icons.AutoMirrored.Filled.ArrowBack, contentDescription =null,
                    tint =Color.LightGray  )
            }
        }
        else{
            null
        }


    }




        TopAppBar(title = { Text(text = title, modifier = Modifier.padding(16.dp))
        },
            navigationIcon = navigationIcon,
            colors =TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = colorResource(id = R.color.app_bar_col)
            ),
            modifier = Modifier
                .padding(start = 4.dp, end = 4.dp)
                .heightIn(24.dp)
                .shadow(elevation = 3.dp),

        )
    }





