package com.example.wishlist

import android.view.View
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.rememberDismissState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalModifierNode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlist.data.Wish
import kotlinx.coroutines.processNextEventInCurrentThread

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(
    viewModel: WishViewModel,
    navController: NavController
){

    val context= LocalContext.current

    Scaffold(
        topBar = {AppBarView(title = "WishList") {
            Toast.makeText(context, "Button Clicked", Toast.LENGTH_SHORT).show()
        }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { Toast.makeText(context, "Button Clicked",
                Toast.LENGTH_SHORT).show()
                navController.navigate(Screen.addScreen.route +"/0L")
                                           },
                modifier = Modifier.padding(all = 20.dp)
                ,contentColor = Color.White, backgroundColor = colorResource(id = R.color.app_bar_col)) {

                Icon(imageVector = Icons.Default.Add, contentDescription =null )
            }
        }
    ) {

        val wishlist=viewModel.getAllWishes.collectAsState(initial = listOf())

        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            items(wishlist.value, key = {wish->wish.id}){
                wish ->

                val dismissState= rememberDismissState(
                    confirmStateChange = {
                        if(it==DismissValue.DismissedToEnd||it==DismissValue.DismissedToStart){
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )



                SwipeToDismiss(state = dismissState, background ={
                    val color by animateColorAsState(targetValue = if (dismissState.dismissDirection==DismissDirection.EndToStart)
                        Color.Green
                    else
                        Color.Transparent
                    ,
                        label = "")

                    val alignment=Alignment.CenterEnd

                       Box(modifier = Modifier
                           .fillMaxSize()
                           .background(color)
                           .padding(horizontal = 24.dp)
                           , contentAlignment = alignment
                       ) {
                           Icon(imageVector = Icons.Default.Delete, contentDescription ="Delete",
                               tint = Color.Black)
                       }
                                                                 },
                    dismissThresholds = {FractionalThreshold(0.25f)},
                    directions = setOf(
                         DismissDirection.EndToStart
                    ),
                    dismissContent = {
                        WishItem(wish = wish){
                            val id=wish.id
                            navController.navigate(Screen.addScreen.route + "/$id")
                        }
                    }
                    )


            }
        }
    }
    }

@Composable
fun WishItem(
    wish: Wish,
    onClick:()->Unit
){

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp, end = 8.dp)
            .clickable {
                onClick()
            },
        elevation =CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ),

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wish.wish, fontWeight = FontWeight.ExtraBold, color = Color.Black)
            Text(text = wish.description,  color = Color.Black)
        }
    }
}