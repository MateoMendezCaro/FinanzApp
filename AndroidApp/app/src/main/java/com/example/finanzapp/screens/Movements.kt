package com.example.finanzapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit
import com.example.finanzapp.Backgrounder
import com.example.finanzapp.BottomNavigationBar
import com.example.finanzapp.TopBar
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextLayoutInput

// Datos de ejemplo
data class Movement(val account: String, val amount: String, val isIncome: Boolean)

val incomes = listOf(
    Movement("NEQUI", "10.000", true),
    Movement("BANCOLOMBIA - AHORRO A LA MANO", "50.000", true),
    Movement("BANCOLOMBIA - CORRIENTE", "72.000", true)
)

val expenses = listOf(
    Movement("GRUPO PROVIDA", "543.000", false),
    Movement("CONFIAR - TÍTULO A FUTURO", "200.000", false),
    Movement("NEQUI", "10.000", false)
)

@Preview(showBackground = true)
@Composable
fun MovementsScreen() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Backgrounder {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {


                // Título de movimientos
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "MOVIMIENTOS",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .background(
                                brush =Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF009FFD),
                                    Color(0xFF2A2A72)
                                )
                                
                                    
                                ),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(12.dp)
                    )
                }

                // Ingresos
                MovementSection("INGRESOS", incomes)

                Spacer(modifier = Modifier.height(20.dp))

                // Egresos
                MovementSection("EGRESOS", expenses)
            }
        }
    }
}

@Composable
fun MovementSection(title: String, movements: List<Movement>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, bottom = 8.dp)
        )

        movements.forEach { movement ->
            MovementRow(movement)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MovementRow(movement: Movement) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(8.dp))
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = movement.account,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal
            )
            BasicText(
                text = if (movement.isIncome) "Ingreso" else "Egreso",
                fontSize = 12.sp,
                color = if (movement.isIncome) Color.Green else Color.Red
            )
                
            
        }

        Text(
            text = movement.amount,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = if (movement.isIncome) Color.Green else Color.Red
        )
    }
}

fun BasicText(text: String, fontSize: TextUnit, color: Color) {

}

