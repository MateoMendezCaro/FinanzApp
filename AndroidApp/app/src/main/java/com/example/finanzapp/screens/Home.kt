package com.example.finanzapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.finanzapp.Backgrounder
import com.example.finanzapp.BottomNavigationBar
import com.example.finanzapp.TopBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
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


@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = { TopBar() },
        bottomBar = { BottomNavigationBar() }
    ) { innerPadding ->
        Backgrounder {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                // Circle balance display at the top
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(300.dp)  // Increase size of the circle
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

                // Plus Button aligned to the right
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),  // Adjust padding to avoid touching the edges
                    horizontalArrangement = Arrangement.End
                ) {
                    FloatingActionButton(
                        onClick = { /* Action */ },
                        modifier = Modifier.size(48.dp),  // Smaller size for plus button
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ) {
                        Icon(painterResource(id = R.drawable.add_24px), contentDescription = "Add")
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))  // Reduced spacing between plus button and action button

                // Buttons for Ingresos, Egresos, Ahorros
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ActionButton(text = "Ingresos", iconId = R.drawable.plus, color = Color(0xFF00C853))
                    Spacer(modifier = Modifier.height(16.dp))
                    ActionButton(text = "Egresos", iconId = R.drawable.minus, color = Color(0xFFD50000))
                    Spacer(modifier = Modifier.height(16.dp))
                    ActionButton(text = "Ahorros", iconId = R.drawable.savings_24px, color = Color(0xFFFDD2AC))
                }
            }
        }
    }
}

@Composable
fun ActionButton(text: String, iconId: Int, color: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .background(Color.LightGray)
            .clickable { /* Action */ }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FloatingActionButton(
            onClick = { /* Action */ },
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