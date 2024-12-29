package be.mbolle.gcalculator.model

enum class OperatorType(val toString: String) {
    PLUS("+"),
    MIN("-"),
    MULTIPLY("*"),
    DIVIDE("/"),
    UNDEFINED("not yet defined")
}