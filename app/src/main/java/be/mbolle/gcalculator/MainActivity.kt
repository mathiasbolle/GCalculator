package be.mbolle.gcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

//aspect ratio

@Preview(showSystemUi = true)
@Composable
fun GCalculatorApp() {
    val wColor = Color.White
    val lemonchiffon = Color(0xFFF6FFCA)
    val greenYellow = Color(0xFFDDFF54)

    val buttons = listOf(
        CalculationButton("AC", lemonchiffon),
        CalculationButton("+-", lemonchiffon),
        CalculationButton("%", lemonchiffon),
        CalculationButton("/", greenYellow),

        CalculationButton("1", wColor),
        CalculationButton("2", wColor),
        CalculationButton("3", wColor),
        CalculationButton("+", greenYellow),

        CalculationButton("4", wColor),
        CalculationButton("5", wColor),
        CalculationButton("6", wColor),
        CalculationButton("-", greenYellow),

        CalculationButton("7", wColor),
        CalculationButton("8", wColor),
        CalculationButton("9", wColor),
        CalculationButton("x", greenYellow),

        CalculationButton("0", wColor),
        CalculationButton(".", wColor),
        CalculationButton("=", greenYellow)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(red = 232, green = 237, blue = 209))
    ) {

        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Bottom) {
            GButtonGrid(
                buttons = buttons,
                modifier = Modifier.padding(start = 15.dp, top = 4.dp, end = 15.dp)
            )
        }
    }
}

@Composable
fun GButtonGrid(buttons: List<CalculationButton>, modifier: Modifier = Modifier) {
    val gridButtons = buttons.subList(0, 16)
    val lastButtons = buttons.subList(16, buttons.size)

    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier

    ) {
        items(gridButtons) { button ->
            GButton(content = button.text, color = button.color!!, modifier = Modifier.aspectRatio(1f))
        }
    }

    Row(
        modifier = modifier.height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        GButton(
            content = lastButtons[0].text, color = lastButtons[0].color!!, modifier = Modifier
                .weight(2f)
                .fillMaxHeight()
        )
        GButton(
            content = lastButtons[1].text, color = lastButtons[1].color!!, modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        )
        GButton(
            content = lastButtons[2].text, color = lastButtons[2].color!!, modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        )
    }
}

@Composable
fun GButton(content: String, onClick: () -> Unit = {}, color: Color, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        border = null,
        colors = ButtonDefaults.outlinedButtonColors(containerColor = color),
        shape = CircleShape
    ) {
        Text(
            text = content, fontSize = 27.sp, color = Color.Black, textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold
        )
    }
}

@Preview
@Composable
fun GButtonPreview() {
    //GButton("1", {})
}

// Model of a calculation button
data class CalculationButton(val text: String, val color: Color? = Color.White)