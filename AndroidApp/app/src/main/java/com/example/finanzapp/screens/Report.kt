import androidx.compose.foundation.background
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
fun ReportsScreen() {
    Backgrounder {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp) // Espacio lateral para toda la pantalla
                .verticalScroll(rememberScrollState())
        ) {
            // Título de movimientos
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "REPORTES",
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

            // Selector de año
            SelectorYear(year = 2023)

            // Lista de reportes
            ReportsList()
        }
    }
}

@Composable
fun SelectorYear(year: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Text(text = "Año", fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color.Black)
        Spacer(modifier = Modifier.width(8.dp)) //Espacio entre texto y valor año
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
            Text(text = year.toString(), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black)
        }
    }
}

@Composable
fun ReportsList() {
    val reports = listOf("30 de Junio", "31 de Julio", "31 de Agosto", "30 de Septiembre", "31 de Octubre")

    Column {
        reports.forEachIndexed { index, date ->
            ReportsItem(date = date)
            if (index < reports.size - 1) {
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}

@Composable
fun ReportsItem(date: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp), // Agrega un padding para separar el contenido del borde
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.unnamed), // asegúrate de tener este recurso en tu proyecto
            contentDescription = null,
            tint = Color(0xFF0B8035),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = date,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black // Cambia el color del texto a negro
        )
    }
}