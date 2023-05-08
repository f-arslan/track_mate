package com.example.track_mate.common.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.track_mate.util.Constants.MEDIUM_PADDING

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDropDownMenu(
    data: List<String>,
    @StringRes label: Int,
    onSelectedItem: (String) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedOption by rememberSaveable { mutableStateOf(data[0]) }

    ExposedDropdownMenuBox(modifier = Modifier.padding(bottom = MEDIUM_PADDING),
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }) {
        OutlinedTextField(value = selectedOption,
            label = { Text(text = stringResource(label)) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            readOnly = true,
            shape = RoundedCornerShape(MEDIUM_PADDING),
            onValueChange = {})
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }) {
            data.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption = option
                        onSelectedItem(option)
                        expanded = false
                    },
                    text = {
                        Text(
                            text = option,
                            fontWeight = FontWeight.SemiBold
                        )
                    })
            }
        }
    }
}