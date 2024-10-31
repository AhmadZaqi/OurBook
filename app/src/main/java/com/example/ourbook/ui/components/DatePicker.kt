package com.example.ourbook.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerFormatter
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.example.ourbook.ui.theme.OurBookTheme
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked(
    date: String,
    onValueChange: (String)-> Unit,
    onDismiss: ()-> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            TextButton(onClick = {
                val selectedDate = datePickerState.selectedDateMillis?.let {
                    convertMillisToDate(it)
                }?: date
                onValueChange(selectedDate)
            }) {
                Text(text = "Ok")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Cancel")
            }
        }
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = false
        )
    }
}

@Composable
fun DatePickerTextField(
    text: String,
    onClick: ()-> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = {},
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = { onClick() }) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null
                )
            }
        },
        placeholder = { Text(text = "Tanggal Lahir")},
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    )
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    OurBookTheme {
        DatePickerDocked("",{}, {})
    }
}

