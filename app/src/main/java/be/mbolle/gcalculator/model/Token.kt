package be.mbolle.gcalculator.model

sealed class Token {



    class Operator(var operatorType: OperatorType = OperatorType.UNDEFINED): Token() {

    }


    class Digit(val value: Double): Token() {

    }
}
