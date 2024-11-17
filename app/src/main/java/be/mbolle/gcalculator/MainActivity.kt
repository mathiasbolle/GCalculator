package be.mbolle.gcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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



@Preview(showSystemUi = true)
@Composable
fun GCalculatorApp() {
    val wColor = Color.White
    val lemonchiffon = Color(0xFFF6FFCA)
    val greenYellow = Color(0xFFDDFF54)

    val value1 by remember { mutableIntStateOf(0) }
    val operator by remember { mutableStateOf(null) }
    val value2 by remember { mutableIntStateOf(0) }


    val onValue1Change = { value1: Int ->

    }

    val buttons = listOf(
        CalculationButton("AC", lemonchiffon, ActionType.Clear()),
        CalculationButton("+-", lemonchiffon, null),
        CalculationButton("%", lemonchiffon, null),
        CalculationButton("/", greenYellow, ActionType.Operator(OperatorType.DIVIDE)),

        CalculationButton("1", wColor, ActionType.Digit()),
        CalculationButton("2", wColor, ActionType.Digit()),
        CalculationButton("3", wColor, ActionType.Digit()),
        CalculationButton("+", greenYellow, ActionType.Digit()),

        CalculationButton("4", wColor, ActionType.Digit()),
        CalculationButton("5", wColor, ActionType.Digit()),
        CalculationButton("6", wColor, ActionType.Digit()),
        CalculationButton("-", greenYellow, null),

        CalculationButton("7", wColor, ActionType.Digit()),
        CalculationButton("8", wColor, ActionType.Digit()),
        CalculationButton("9", wColor, ActionType.Digit()),
        CalculationButton("x", greenYellow, null),

        CalculationButton("0", wColor, ActionType.Digit()),
        CalculationButton(".", wColor, ActionType.Digit()),
        CalculationButton("=", greenYellow, ActionType.Enter())
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(red = 232, green = 237, blue = 209))
            .safeDrawingPadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            GHeader(
                title = stringResource(R.string.app_name), mode = "Standard", modifier =
                Modifier
                    .fillMaxWidth()
            )
            GTextField("2", modifier = Modifier.height(10.dp))
            GButtonGrid(
                buttons = buttons,

            )
        }
    }
}

@Composable
fun GHeader(title: String, mode: String, modifier: Modifier = Modifier) {
    val calculatorVector = painterResource(R.drawable.calculator)
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
        Column {
            Text(text = title, fontWeight = FontWeight.ExtraBold, fontSize = 25.sp)
            Text(text = mode, fontSize = 17.sp)
        }
        Column {
            Image(painter = calculatorVector, contentDescription = "calculator mode")
        }
    }
}

@Composable
fun GTextField(input: String, modifier: Modifier = Modifier) {
    Column {
        Text(text = "1+1", fontSize = 17.sp)
        Spacer(modifier = modifier)
        Text(text = input, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun GButtonGrid(
    buttons: List<CalculationButton>,

    modifier: Modifier = Modifier
) {
    val gridButtons = buttons.subList(0, 16)
    val lastButtons = buttons.subList(16, buttons.size)
    val defaultSpacedItem = 4.dp



    Column(modifier = modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            verticalArrangement = Arrangement.spacedBy(defaultSpacedItem),
            horizontalArrangement = Arrangement.spacedBy(defaultSpacedItem),
        ) {
            items(gridButtons) { button ->
                GButton(
                    content = button.text,
                    color = button.color!!,
                    modifier = Modifier.aspectRatio(1f),
                    onClick = {}
                )
            }
        }

        Spacer(modifier = Modifier.height(defaultSpacedItem))

        Row(
            modifier = Modifier.height(IntrinsicSize.Max),
            horizontalArrangement = Arrangement.spacedBy(defaultSpacedItem)
        ) {
            GButton(
                content = lastButtons[0].text, color = lastButtons[0].color!!, modifier = Modifier
                    .weight(2f)
                    .fillMaxHeight(),
                onClick = {}
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
}

@Composable
fun GButton(
    content: String,
    onClick: () -> Unit = {},
    color: Color,
    modifier: Modifier = Modifier
) {
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
data class CalculationButton(
    val text: String,
    val color: Color? = Color.White,
    val actionType: ActionType?
)

sealed class ActionType {
    data class Operator(val operatorType: OperatorType) : ActionType()
    class Digit() : ActionType()
    class Clear() : ActionType()
    class Enter() : ActionType()
}

enum class OperatorType {
    PLUS,
    MIN,
    DIVIDE
}