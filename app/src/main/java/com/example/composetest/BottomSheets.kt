package com.example.composetest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Bottom Sheet Which Automatically Expands on Startup !
@ExperimentalMaterialApi
@Composable
fun BottomSheetWithNav(bottomState: BottomSheetScaffoldState) {
    BottomSheetScaffold(
        sheetPeekHeight = 80.dp,
        scaffoldState = bottomState,
        sheetContent = {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "first") {
                composable("first") {
                    FirstScreen {
                        navController.navigate(route = "second")
                    }
                }
                composable("second") {
                    SecondScreen {
                        navController.navigateUp()
                    }
                }
            }
        },
        content = {
            Column(Modifier.fillMaxSize()) {
                Text(text = "Hello from Main Screen!")
            }
        }
    )
}


// Working Bottom Sheet With Expected Behaviour
@ExperimentalMaterialApi
@Composable
fun NormalBottomSheet(bottomState: BottomSheetScaffoldState) {
    BottomSheetScaffold(
        sheetPeekHeight = 80.dp,
        scaffoldState = bottomState,
        sheetContent = {
            FirstScreen()
        },
        content = {
            Column(Modifier.fillMaxSize()) {
                Text(text = "Hello from Main Screen!")
            }
        }
    )
}