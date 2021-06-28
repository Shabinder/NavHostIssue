package com.example.composetest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composetest.ui.theme.ComposeTestTheme

@OptIn(ExperimentalMaterialApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    val bottomState = rememberBottomSheetScaffoldState(
                        bottomSheetState = rememberBottomSheetState(
                            initialValue = BottomSheetValue.Collapsed
                        )
                    )

                    BottomSheetWithNav(bottomState = bottomState) // Issue is here
                    // NormalBottomSheet(bottomState = bottomState) // This is Working with Expected Behaviour
                }
            }
        }
    }

}

@Composable
fun FirstScreen(goToSecondScreen: (() -> Unit)? = null) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Hello from First Screen! its a Bottom Sheet and Is Automatically Expanding !",Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
        if (goToSecondScreen != null)
            Icon(
                Icons.Default.ArrowForward,
                contentDescription = "Go To Second Screen",
                Modifier
                    .fillMaxWidth()
                    .clickable { goToSecondScreen() }
            )
    }
}

@Composable
fun SecondScreen(goBack: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Text(text = "Hello from Second Screen!",Modifier.fillMaxWidth(),textAlign = TextAlign.Center)
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "Go To First Screen",
            Modifier
                .fillMaxWidth()
                .clickable { goBack() }
        )
    }
}