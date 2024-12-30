package be.mbolle.gcalculator.model

import be.mbolle.gcalculator.model.OperatorType.*

/**
 * Represents the calculation
 */
class Expression(
    private val leftDigit: Double,
    private val operator: Token.Operator,
    private val rightDigit: Double
) {



    fun calculate(): Double {
        return when (operator.operatorType) {
            PLUS -> leftDigit + rightDigit
            MIN -> leftDigit - rightDigit
            MULTIPLY -> leftDigit * rightDigit
            DIVIDE -> leftDigit / rightDigit
            UNDEFINED -> 0.0
        }
    }
}