package be.mbolle.gcalculator.ui

import androidx.lifecycle.ViewModel
import be.mbolle.gcalculator.model.Action
import be.mbolle.gcalculator.model.Expression
import be.mbolle.gcalculator.model.OperatorType
import be.mbolle.gcalculator.model.Token

class CalculationViewModel() : ViewModel() {
    fun execute(action: Action) {
        when (action) {
            is Action.Calculate -> {
                val calculation = Expression(
                    listOf(),
                    Token.Operator(OperatorType.MULTIPLY),
                    listOf())
            }
            is Action.Clear -> {

            }
            is Action.Node -> {
                when (action.node) {
                    is Token.Digit -> {
                        TODO()
                    }
                    is Token.Operator -> {
                        TODO()
                    }
                }
            }
        }
    }
}