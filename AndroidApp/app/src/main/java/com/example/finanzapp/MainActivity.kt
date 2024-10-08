package com.example.finanzapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import com.example.finanzapp.ui.theme.FinanzappTheme
import com.example.finanzapp.ui.theme.barsblue
import com.example.finanzapp.screens.*
import kotlinx.coroutines.CoroutineScope

@SuppressLint("SuspiciousIndentation")
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { MenuScreen(navController) }
    ) {
        Scaffold(
            topBar = { TopBar(drawerState, scope) },
            bottomBar = { BottomNavigationBar(navController) }
        ) { innerPadding ->
            Backgrounder {
                NavHost(
                    navController = navController,
                    startDestination = "login",
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable("home") { HomeScreen(navController) }
                    composable("login") { InitialScreen(navController) }
                    composable("movements") { MovementsScreen(navController) }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState, scope: CoroutineScope) {
    TopAppBar(
        title = {
            Text(
                text = "02 DE AGOSTO DEL 2024",
                color = Color.White
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = barsblue
        ),
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        if (drawerState.isClosed) {
                            drawerState.open()
                        } else {
                            drawerState.close()
                        }
                    }
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_24px),
                    contentDescription = "Abrir menú",
                    tint = Color.White
                )
            }
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = barsblue,
        content = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.home_24px),
                        contentDescription = "Home",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { navController.navigate("login") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.pan_tool_alt_24px),
                        contentDescription = "Login",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { navController.navigate("movements") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.folder_open_24px),
                        contentDescription = "Movements",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { navController.navigate("menu") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_book_24px),
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }
            }
        }
    )
}
