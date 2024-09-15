package com.example.finanzapp.screens

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

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomNavigationBar() }
        ) { innerPadding ->
            Backgrounder{
                Text(
                    text = "contenido de la pantalla de casa",
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                )
            }
        }
    }
