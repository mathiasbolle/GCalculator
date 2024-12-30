package be.mbolle.gcalculator.ui

data class CalculationUiState(
    val inputResult: String = "",
    val leftDigit: String = "",
    val rightDigit: String = "",
    val operator: String = ""
)