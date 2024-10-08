package com.example.finanzapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun MenuScreen(navController: NavHostController) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(16.dp)
        ) {
            Text(text = "Perfil del Usuario", modifier = Modifier.padding(16.dp))
            Text(text = "Notificaciones", modifier = Modifier.padding(16.dp))
            Text(text = "Seguridad", modifier = Modifier.padding(16.dp))
            Text(text = "Ayuda y Soporte", modifier = Modifier.padding(16.dp))
            Text(text = "Información", modifier = Modifier.padding(16.dp))
            Text(text = "Configuración", modifier = Modifier.padding(16.dp))
        }
    }

