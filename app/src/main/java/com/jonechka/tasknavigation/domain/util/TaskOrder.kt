package com.jonechka.tasknavigation.domain.util

sealed class TaskOrder(val orderType: OrderType) {
    class Title(orderType: OrderType): TaskOrder(orderType)
    class Date(orderType: OrderType): TaskOrder(orderType)
    class Timestamp(orderType: OrderType): TaskOrder(orderType)

    fun copy(orderType: OrderType): TaskOrder {
        return when (this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Timestamp -> Timestamp(orderType)
        }
    }
}