package com.example.track_mate.common.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.track_mate.model.Student
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.R.drawable as AppIcon
import com.example.track_mate.R.string as AppText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSearchBar(
    value: String,
    trailingIconState: Boolean,
    onValueChange: (String) -> Unit,
    onCancel: () -> Unit,
) {
    val localFocusManager = LocalFocusManager.current

    OutlinedTextField(value = value,
        shape = RoundedCornerShape(MEDIUM_PADDING),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Search,
                contentDescription = stringResource(AppText.search)
            )
        },
        trailingIcon = {
            when (trailingIconState) {
                true -> {
                    Icon(
                        painter = painterResource(AppIcon.outline_cancel_24),
                        modifier = Modifier.clickable {
                            onValueChange("")
                            onCancel()
                            localFocusManager.clearFocus()
                        },
                        contentDescription = stringResource(AppText.cancel)
                    )
                }

                false -> {}
            }
        },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(onSearch = {
            localFocusManager.clearFocus()
        }),
        placeholder = { Text(text = stringResource(AppText.search)) },
        onValueChange = {
            onValueChange(it)
        })
}

@Composable
fun AppSearchList(
    firstTenStudents: List<Student>,
    onStudentClick: (Student) -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier.padding(
            start = MEDIUM_HIGH_PADDING,
            end = MEDIUM_HIGH_PADDING,
            top = HIGH_PADDING,
            bottom = HIGH_PADDING
        ),
        verticalArrangement = Arrangement.spacedBy(MEDIUM_PADDING)
    ) {
        items(items = firstTenStudents, key = {
            it.id
        }) {
            Text(
                modifier = Modifier.fillMaxWidth().clickable {
                    onStudentClick(it)
                },
                text = it.name,
            )
        }
    }
}