package be.mbolle.gcalculator.ui

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import be.mbolle.gcalculator.model.Action
import be.mbolle.gcalculator.model.Expression
import be.mbolle.gcalculator.model.OperatorType
import be.mbolle.gcalculator.model.Token
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.drop

class CalculationViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow(CalculationUiState())
    val uiState = _uiState.asStateFlow()

    val rightTokenDigits: MutableList<Token.Digit> = mutableListOf()
    val leftTokenDigits: MutableList<Token.Digit> = mutableListOf()
    val operator: MutableState<Token.Operator> = mutableStateOf(Token.Operator())

    fun execute(action: Action) {



        when (action) {
            is Action.Calculate -> {

                val calculation = Expression(
                    rightTokenDigits.toDouble(),
                    operator.value,
                    leftTokenDigits.toDouble()).calculate()

                _uiState.value = _uiState.value.copy(
                    inputResult = calculation.toString()
                )

                Log.d("CalculationViewModel", calculation.toString())
                rightTokenDigits.clear()
                leftTokenDigits.clear()
                operator.value.operatorType = OperatorType.UNDEFINED

            }
            is Action.Clear -> {
            }
            is Action.Node -> {
                when (action.node) {
                    is Token.Digit -> {
                        val digit = Token.Digit(action.node.value)

                        if (operator.value.operatorType == OperatorType.UNDEFINED) {
                            Log.d("CalcluationViewModel", "executed?")
                            rightTokenDigits.add(digit)
                            _uiState.value = _uiState.value.copy(
                                rightDigit = rightTokenDigits.toDouble().toString()
                            )
                        } else {
                            leftTokenDigits.add(digit)
                            _uiState.value = _uiState.value.copy(
                                leftDigit = leftTokenDigits.toDouble().toString()
                            )
                        }

                    }
                    is Token.Operator -> {
                        operator.value.operatorType = action.node.operatorType

                        _uiState.value = _uiState.value.copy(
                            operator = action.node.operatorType.toString
                        )

                    }
                }
            }
        }

        Log.d("CalculationViewModel", rightTokenDigits.toList().toString())
        Log.d("CalculationViewModel", leftTokenDigits.toList().toString())
        Log.d("CalculationViewModel", operator.value.operatorType.toString)
    }

    private fun  MutableList<Token.Digit>.toDouble(): Double {
        return this.map { digits -> digits.value.toString() } .reduce { acc, digit -> acc+ digit }
            .toDouble()
    }
}