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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.R
import com.example.finanzapp.ui.theme.darkblue
import com.example.finanzapp.Backgrounder
import androidx.compose.runtime.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(userName: String?, showDialog: Boolean, onDialogDismiss: () -> Unit) {
    Backgrounder {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Hola $userName",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = onDialogDismiss,
                    title = { Text("Saludo") },
                    text = { Text("Hola, $userName") },
                    confirmButton = {
                        TextButton(onClick = onDialogDismiss) {
                            Text("Cerrar")
                        }
                    }
                )
            }

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

            Spacer(modifier = Modifier.height(16.dp))

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

            // Columna de botones de acción con scroll
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .verticalScroll(rememberScrollState()), // Habilitar scroll vertical
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ActionButton(
                    text = "Ingresos",
                    iconId = R.drawable.plus,
                    color = Color(0xFF00C853),
                    onClick = { /* Acción de navegación */ }
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
