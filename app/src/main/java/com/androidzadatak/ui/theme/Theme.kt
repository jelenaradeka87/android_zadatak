import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.androidzadatak.ui.theme.AppColors
import com.androidzadatak.ui.theme.PrimaryColor
import com.androidzadatak.ui.theme.Typography


private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColor,
    secondary = AppColors.yellow,
    background = PrimaryColor,
    surface = AppColors.unselectedBackground,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}