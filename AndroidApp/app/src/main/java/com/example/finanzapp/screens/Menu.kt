import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import com.example.finanzapp.R

@Composable
fun MenuScreen(userName: String?) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val menuWidth = screenWidth * 0.8f
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(menuWidth)
            .background(Color(0xFF668FD4))
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.person_24px),
                    contentDescription = "Foto de perfil",
                    tint = Color.Gray,
                    modifier = Modifier.size(50.dp)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = userName ?: "Usuario",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        MenuItem(text = "Perfil del Usuario", icon = painterResource(R.drawable.person_24px))
        MenuItem(text = "Notificaciones", icon = painterResource(R.drawable.notifications_24px))
        MenuItem(text = "Seguridad", icon = painterResource(R.drawable.security_24px))
        MenuItem(text = "Ayuda y Soporte", icon = painterResource(R.drawable.help_24px))
        MenuItem(text = "Información", icon = painterResource(R.drawable.info_24px))
        MenuItem(text = "Configuración", icon = painterResource(R.drawable.settings_24px))

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            SocialIcon(icon = painterResource(R.drawable.sms_24px))
            SocialIcon(icon = painterResource(R.drawable.photo_camera_24px))
            SocialIcon(icon = painterResource(R.drawable.groups_24px))
        }
    }
}

@Composable
fun MenuItem(text: String, icon: Painter) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = text,
            tint = Color.Black,
            modifier = Modifier
                .size(60.dp)
                .padding(end = 18.dp)
        )
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.Black,
            fontWeight = FontWeight.SemiBold
        )
    }
}
@Composable
fun SocialIcon(icon: Painter) {
    Icon(
        painter = icon,
        contentDescription = "Ícono de red social",
        tint = Color.White,
        modifier = Modifier.size(40.dp)
    )
}
