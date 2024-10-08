package com.example.finanzapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    Backgrounder {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center, // Alinear al centro verticalmente
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título "FinanzApp"
            Text(
                text = "FinanzApp",
                style = TextStyle(
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = FontFamily.Default // Aplica la fuente personalizada
                )
            )

            Spacer(modifier = Modifier.height(190.dp))  // Espacio entre el título y los botones

            // Botón de Iniciar Sesión
            Button(
                onClick = { navController.navigate("home") }, // Navega a la pantalla de inicio
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFACB6C7),  // Color del fondo del botón
                    contentColor = Color.Black          // Color del texto del botón
                ),
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(8.dp)
            ) {
                Text(text = "Iniciar sesión")
            }

            // Espacio de 120 dp (aproximadamente 3 cm) entre los botones
            Spacer(modifier = Modifier.height(120.dp))

            // Botón de Registrarse
            Button(
                onClick = { /* Acción para registrarse */ }, // Agrega la acción de navegación para registrarse
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFACB6C7),  // Color del fondo del botón
                    contentColor = Color.Black          // Color del texto del botón
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
