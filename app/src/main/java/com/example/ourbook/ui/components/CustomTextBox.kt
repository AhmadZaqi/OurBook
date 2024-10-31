package com.example.ourbook.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ourbook.ui.theme.OurBookTheme

@Composable
fun CustomTextBox(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String)-> Unit,
    hint: String
) {
    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        placeholder = { Text(text = hint)},
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        singleLine = true,
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    OurBookTheme {
        CustomTextBox(text = "Tes", onTextChanged = {}, hint = "Lagi")
    }
}