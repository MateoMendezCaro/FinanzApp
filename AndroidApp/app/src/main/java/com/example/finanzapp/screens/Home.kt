package com.example.finanzapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.R
import com.example.finanzapp.ui.theme.darkblue
import com.example.finanzapp.ui.theme.barsblue
import androidx.navigation.NavHostController
import com.example.finanzapp.Backgrounder
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Backgrounder {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
                    // Caja que muestra el gráfico y la cantidad
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(300.dp)
                            .clip(CircleShape)
                            .background(darkblue)
                    ) {
                        Text(
                            text = "000,000.000",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp
                        )
                        Image(
                            painter = painterResource(id = R.drawable.graficotorta),
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    // Botón flotante de agregar (Plus Button)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 16.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        FloatingActionButton(
                            onClick = { /* Acción */ },
                            modifier = Modifier.size(48.dp),
                            containerColor = Color.White,
                            contentColor = Color.Black
                        ) {
                            Icon(
                                painterResource(id = R.drawable.add_24px),
                                contentDescription = "Add"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botones para Ingresos, Egresos y Ahorros
                    Column(
                        modifier = Modifier.fillMaxWidth(0.9f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ActionButton(
                            text = "Ingresos",
                            iconId = R.drawable.plus,
                            color = Color(0xFF00C853),
                            onClick = { /* Acción de navegación */ }  // Navega a Movements
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ActionButton(
                            text = "Egresos",
                            iconId = R.drawable.minus,
                            color = Color(0xFFD50000),
                            onClick = { /* Acción de navegación */ }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        ActionButton(
                            text = "Ahorros",
                            iconId = R.drawable.savings_24px,
                            color = Color(0xFFFDD2AC),
                            onClick = { /* Acción de navegación */ }
                        )
                    }
                }
            }
        }



// Función para los botones de acción
@Composable
fun ActionButton(text: String, iconId: Int, color: Color, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(Color.LightGray)
            .clickable { onClick() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(
            onClick = { onClick() },
            modifier = Modifier.size(36.dp),
            containerColor = color,
            contentColor = Color.White
        ) {
            Icon(painterResource(id = iconId), contentDescription = text)
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black,
            textAlign = TextAlign.Start
        )
    }
}

// Contenido del menú lateral
@Composable
fun MenuContent(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(text = "Perfil del Usuario", modifier = Modifier.padding(16.dp))
        Text(text = "Notificaciones", modifier = Modifier.padding(16.dp))
        Text(text = "Seguridad", modifier = Modifier.padding(16.dp))
        Text(text = "Ayuda y Soporte", modifier = Modifier.padding(16.dp))
        Text(text = "Información", modifier = Modifier.padding(16.dp))
        Text(text = "Configuración", modifier = Modifier.padding(16.dp))
    }
}

// Barra superior con botón de menú
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onMenuClick: () -> Unit) {
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
            IconButton(onClick = { onMenuClick() }) {
                Icon(
                    painter = painterResource(id = R.drawable.menu_24px),
                    contentDescription = "Menu",
                    tint = Color.White
                )
            }
        }
    )
}
