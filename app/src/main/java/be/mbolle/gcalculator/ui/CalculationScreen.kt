package be.mbolle.gcalculator.ui

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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import be.mbolle.gcalculator.R
import be.mbolle.gcalculator.model.Action
import be.mbolle.gcalculator.model.OperatorType
import be.mbolle.gcalculator.model.Token

@Preview(showSystemUi = true)
@Composable
fun GCalculatorApp(viewModel: CalculationViewModel = viewModel()) {
    val state = viewModel.uiState.collectAsState()


    val wColor = Color.White
    val lemonchiffon = Color(0xFFF6FFCA)
    val greenYellow = Color(0xFFDDFF54)

    val buttons = listOf(
        CalculationButton("AC", lemonchiffon, Action.Clear),
        CalculationButton("+-", lemonchiffon, Action.Node(Token.Operator(OperatorType.UNDEFINED))),
        CalculationButton("%", lemonchiffon, Action.Node(Token.Operator(OperatorType.UNDEFINED))),
        CalculationButton("/", greenYellow, Action.Node(Token.Operator(OperatorType.DIVIDE))),

        CalculationButton("1", wColor, Action.Node(Token.Digit(1.0))),
        CalculationButton("2", wColor, Action.Node(Token.Digit(2.0))),
        CalculationButton("3", wColor, Action.Node(Token.Digit(3.0))),
        CalculationButton("+", greenYellow, Action.Node(Token.Operator(OperatorType.PLUS))),

        CalculationButton("4", wColor, Action.Node(Token.Digit(4.0))),
        CalculationButton("5", wColor, Action.Node(Token.Digit(5.0))),
        CalculationButton("6", wColor, Action.Node(Token.Digit(6.0))),
        CalculationButton("-", greenYellow, Action.Node(Token.Operator(OperatorType.MIN))),

        CalculationButton("7", wColor, Action.Node(Token.Digit(7.0))),
        CalculationButton("8", wColor, Action.Node(Token.Digit(8.0))),
        CalculationButton("9", wColor, Action.Node(Token.Digit(9.0))),
        CalculationButton("x", greenYellow, Action.Node(Token.Operator(OperatorType.MULTIPLY))),

        CalculationButton("0", wColor, Action.Node(Token.Digit(0.0))),
        CalculationButton(".", wColor, Action.Node(Token.Operator(OperatorType.UNDEFINED))),
        CalculationButton("=", greenYellow, Action.Calculate)
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
            GTextField(
                value1 = state.value.rightDigit,
                value2 = state.value.leftDigit,
                operator = state.value.operator,
                state.value.inputResult, modifier = Modifier.height(10.dp))
            GButtonGrid(
                buttons = buttons,
                buttonClick = { action -> viewModel.execute(action)}
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
fun GTextField(value1: String, value2: String, operator: String, input: String, modifier: Modifier = Modifier) {
    Column {
        Text(text = "${value1}${operator}${value2}", fontSize = 17.sp)
        Spacer(modifier = modifier)
        Text(text = input, fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
    }
}

@Composable
fun GButtonGrid(
    buttons: List<CalculationButton>,
    buttonClick: (Action) -> Unit,

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
                    onClick = { buttonClick(button.action) }
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
                onClick = { buttonClick(lastButtons[0].action)}
            )
            GButton(
                content = lastButtons[1].text, color = lastButtons[1].color!!, modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                onClick = { buttonClick(lastButtons [1].action) }
            )
            GButton(
                content = lastButtons[2].text, color = lastButtons[2].color!!, modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                onClick = { buttonClick(lastButtons[2].action) }
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
    val action: Action
)

