package com.example.finanzapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

// Función para crear el fondo con degradado
@Composable
fun Backgrounder(content: @Composable () -> Unit) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFB2EBF2), Color(0xFF00796B), Color(0xFF9AD1DA))
    )
    // Aplicar el degradado a un Box
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        content()  // Aquí se pasa el contenido que se define en la pantalla
    }
}

// Pantalla inicial
@Composable
fun InitialScreen(navController: NavHostController) {
    var userName by remember { mutableStateOf("") }  // Estado para el nombre del usuario

    Backgrounder {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "FinanzApp",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = FontFamily.Default
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para ingresar el nombre
            TextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón de Iniciar Sesión
            Button(
                onClick = {
                    navController.navigate("home/$userName") {
                        launchSingleTop = true
                    }
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFACB6C7),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(8.dp)
            ) {
                Text(text = "Iniciar sesión")
            }

            Spacer(modifier = Modifier.height(120.dp))

            // Botón de Registrarse
            Button(
                onClick = { /* Acción para registrarse */ },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFACB6C7),
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(8.dp)
            ) {
                Text(text = "Registrarse")
            }
        }
    }
}