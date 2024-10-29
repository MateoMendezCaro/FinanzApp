package com.example.finanzapp

import MenuScreen
import ReportsScreen
import SavesScreen
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import com.example.finanzapp.ui.theme.FinanzappTheme
import com.example.finanzapp.ui.theme.barsblue
import com.example.finanzapp.screens.*
import kotlinx.coroutines.CoroutineScope
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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

    // Estados para el nombre de usuario y el diálogo
    val userName = remember { mutableStateOf<String?>(null) }
    var showDialog by remember { mutableStateOf(true) }  // Estado persistente del diálogo

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuScreen(userName.value)  // Pasamos `userName.value` a `MenuScreen`
        }
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
                    composable("home/{userName}") { backStackEntry ->
                        userName.value = backStackEntry.arguments?.getString("userName")
                        HomeScreen(userName.value, showDialog) { showDialog = false }  // Pasamos showDialog y el callback para cerrarlo
                    }
                    composable("login") { InitialScreen(navController) }
                    composable("movements") { MovementsScreen() }
                    composable("saves") { SavesScreen() }
                    composable("reports") { ReportsScreen() }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(drawerState: DrawerState, scope: CoroutineScope) {
    val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd 'de' MMMM 'del' yyyy"))
    TopAppBar(
        title = {
            Text(
                text = currentDate,
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
                IconButton(onClick = {
                    navController.navigate("home/${navController.previousBackStackEntry?.arguments?.getString("userName")}")
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.home_24px),
                        contentDescription = "Home",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { navController.navigate("movements") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.pan_tool_alt_24px),
                        contentDescription = "Movements",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { navController.navigate("saves") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.folder_open_24px),
                        contentDescription = "Saves",
                        tint = Color.White
                    )
                }
                IconButton(onClick = { navController.navigate("reports") }) {
                    Icon(
                        painter = painterResource(id = R.drawable.menu_book_24px),
                        contentDescription = "Reports",
                        tint = Color.White
                    )
                }
            }
        }
    )
}