package com.example.finanzapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.finanzapp.ui.theme.FinanzappTheme
@Composable
fun Backgrounder(content:  @Composable () -> Unit) {
    val backgroundImage = painterResource(id = R.drawable.fondo)
        Image(
            painter = backgroundImage,
            contentDescription = "Imagen de fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        content()
    }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinanzappTheme {
                MainScreen()
            }

        }

    }
}

@Composable
fun MainScreen() {
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomNavigationBar() }
        ) { innerPadding ->
            Backgrounder {
                Text(
                    text = "Main Screen Content",
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }
        }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Text(
                text = "02 DE AGOSTO DEL 2024",
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF3C5A99)
        ),
        navigationIcon = {
            IconButton(onClick = { /* TODO: Handle menu action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_24px),
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun BottomNavigationBar() {
    BottomAppBar(
        containerColor = Color(0xFF3C5A99),
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.home_24px),
                        contentDescription = "Home",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.pan_tool_alt_24px),
                        contentDescription = "Click",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.folder_open_24px),
                        contentDescription = "Folder",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { /* TODO */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_book_24px),
                        contentDescription = "Book",
                        tint = Color.White
                    )
                }
            }
        }
    )
}