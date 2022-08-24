package com.jonechka.tasknavigation.domain.util

sealed class OrderType {
    object Ascending: OrderType()
    object Descending: OrderType()
}
