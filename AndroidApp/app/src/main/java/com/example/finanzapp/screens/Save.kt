import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finanzapp.R
import com.example.finanzapp.Backgrounder

@Composable
fun SavesScreen() {
    Backgrounder {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Título de guardados
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "GUARDADOS",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(11.dp)
                        .align(Alignment.Center)
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color(0xFF0066CC),
                                    Color(0xFF6D8AA8)
                                )
                            ),
                            shape = RoundedCornerShape(5.dp)
                        )
                        .padding(12.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp)) // Ajusta el espacio vertical entre el título y los íconos

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp), // Reducido para menos espacio entre filas
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButtonWithText(iconRes = R.drawable.folder_open_24px, label = "Facturas")
                    IconButtonWithText(iconRes = R.drawable.folder_open_24px, label = "Fotos")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp), // Ajusta también este padding
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    IconButtonWithText(iconRes = R.drawable.folder_open_24px, label = "Documentos")
                    IconButtonWithText(iconRes = R.drawable.folder_open_24px, label = "Comprobante")
                }
            }
        }
    }
}
@Composable
fun IconButtonWithText(iconRes: Int, label: String) {
    Column(
        modifier = Modifier
            .size(150.dp)
            .clickable { /* Acción del botón */ },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = Color.White,
            modifier = Modifier
                .size(100.dp)
                .background(Color(0xFF70A5F6), shape = RoundedCornerShape(12.dp)) // Redondear bordes
                .padding(10.dp)
        )


        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}