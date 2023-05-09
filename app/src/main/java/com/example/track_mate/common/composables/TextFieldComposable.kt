package com.example.track_mate.common.composables

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.track_mate.util.Constants.MEDIUM_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    value: String,
    label: String,
    enabled: Boolean,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        label = { Text(text = label) },
        shape = RoundedCornerShape(MEDIUM_PADDING),
        enabled = enabled,
        onValueChange = {
            onValueChange(it)
        }
    )
}