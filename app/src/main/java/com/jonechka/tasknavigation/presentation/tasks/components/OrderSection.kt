package com.jonechka.tasknavigation.presentation.tasks.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jonechka.tasknavigation.domain.util.OrderType
import com.jonechka.tasknavigation.domain.util.TaskOrder

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    taskOrder: TaskOrder,
    onOrderChange: (TaskOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                isSelected = taskOrder is TaskOrder.Title,
                onSelect = { onOrderChange(TaskOrder.Title(taskOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Timestamp",
                isSelected = taskOrder is TaskOrder.Timestamp,
                onSelect = { onOrderChange(TaskOrder.Timestamp(taskOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                isSelected = taskOrder is TaskOrder.Color,
                onSelect = { onOrderChange(TaskOrder.Color(taskOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                isSelected = taskOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(taskOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                isSelected = taskOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(taskOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}