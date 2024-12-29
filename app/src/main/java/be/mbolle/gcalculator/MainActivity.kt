package be.mbolle.gcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import be.mbolle.gcalculator.ui.GCalculatorApp
import be.mbolle.gcalculator.ui.theme.GCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCalculatorTheme {
                GCalculatorApp()
            }
        }
    }
}
