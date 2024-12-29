package be.mbolle.gcalculator.model

sealed class Token {



    class Operator(val operatorType: OperatorType): Token() {

    }


    class Digit(val value: Double): Token() {

    }
}
