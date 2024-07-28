package com.example.wishlist

import android.icu.text.AlphabeticIndex.Bucket.LabelType
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wishlist.data.Wish
import kotlinx.coroutines.launch
import java.util.UUID

@Composable
fun AddView(
    id:Long,
    viewModel: WishViewModel,
    navController: NavController,
){

    var snackMessage= remember {
        mutableStateOf("")
    }

    var scope= rememberCoroutineScope()

    var scaffoldState= rememberScaffoldState()

    if(id!=0L) {
       var wishValue=viewModel.getAWishById(id).collectAsState(initial = Wish(0,"",""))
        viewModel.wishTitleState=wishValue.value.wish
        viewModel.wishDescriptionState=wishValue.value.description

    }
    else{
        viewModel.wishTitleState=""
        viewModel.wishDescriptionState=""
    }
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ){
        Scaffold(
            scaffoldState=scaffoldState,
            topBar = {
                AppBarView(title = if (id!=0L){
                    stringResource(id = R.string.update_wish)
                }else{
                    stringResource(id = R.string.add_wish)
                }){
                    navController.navigateUp()
                }

            }
        ) {
            Column(modifier = Modifier
                .padding(it)
                .wrapContentSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                WishTextField(label = "Title",value=viewModel.wishTitleState, onValueChange = {
                    viewModel.onWishTitleChanged(it)
                })
                WishTextField(label = "Description", value =viewModel.wishDescriptionState
                    , onValueChange = {
                        viewModel.onWishDescriptionChanged(it)
                    })

                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = { if(viewModel.wishTitleState.isNotEmpty()
                    && viewModel.wishDescriptionState
                        .isNotEmpty()){

                    // TODO UpdateWish && TODO Add Wish

                    if(id!=0L){

                        viewModel.updateWish(
                            Wish(
                                id=id,
                                wish = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )

                    }else{

                        viewModel.addWish(
                            Wish(
                                wish=viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value="Wish added"
                    }

                }
                else{
                    //Displaying message for Entering values in respective fields

                    snackMessage.value="Enter Title and Description for your wish"

                }

                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                        navController.navigateUp()
                    }

                }) {
                    Text(text = if (id!=0L)
                        stringResource(id = R.string.update_wish)
                    else stringResource(id = R.string.add_wish)
                    )
                }

            }
        }

    }
    }



@Composable
fun WishTextField(
    label:String,
    value:String,
    onValueChange:(String)->Unit,

){
    Surface(
        modifier = Modifier.background(Color.Black)
    ) {
        OutlinedTextField(value = value, onValueChange = onValueChange
            , label = { Text(text = label, color = Color.Black, fontWeight = FontWeight.Bold)}
            , modifier = Modifier.fillMaxWidth()
            , keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                backgroundColor=Color.White ,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                cursorColor = Color.DarkGray,
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.DarkGray
            )
        )
    }


}

@Preview
@Composable
fun AddPreview(){
    WishTextField(label = "Text", value = "Shikha", onValueChange = {})
}