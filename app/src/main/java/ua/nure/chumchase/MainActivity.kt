package ua.nure.chumchase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ua.nure.chumchase.auth.presentation.LoginScreen
import ua.nure.chumchase.ui.theme.ChumChaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChumChaseTheme {
                LoginScreen()
            }
        }
    }
}