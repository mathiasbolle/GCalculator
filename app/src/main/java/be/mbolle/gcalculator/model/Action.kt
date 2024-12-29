package be.mbolle.gcalculator.model

/**
 * Register an action
 */
sealed class Action() {
    object Clear: Action()
    class Node(val node: Token): Action()
    object Calculate: Action()
}