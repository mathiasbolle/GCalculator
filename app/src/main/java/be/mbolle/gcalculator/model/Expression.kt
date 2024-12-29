package be.mbolle.gcalculator.model

import be.mbolle.gcalculator.model.OperatorType.*

/**
 * Represents the calculation
 */
class Expression(
    private val leftDigit: List<Token.Digit>,
    private val operator: Token.Operator,
    private val rightDigit: List<Token.Digit>
) {
    fun calculate(): Double {
        val leftDigitValue = leftDigit.map { value -> value.toString() }.reduce { acc, s -> acc + s }
            .toDouble()
        val rightDigitValue = rightDigit.map { value -> value.toString() }.reduce {acc, s -> acc + s}
            .toDouble()

        return when (operator.operatorType) {
            PLUS -> leftDigitValue + rightDigitValue
            MIN -> leftDigitValue - rightDigitValue
            MULTIPLY -> leftDigitValue * rightDigitValue
            DIVIDE -> leftDigitValue / rightDigitValue
            UNDEFINED -> 0.0
        }
    }
}