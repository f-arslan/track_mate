package com.example.track_mate.common.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.example.track_mate.core.model.Student
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.SMALL_MEDIUM_PADDING
import com.example.track_mate.util.Constants.SMALL_PADDING
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

    SurfaceWrapper(tonalElevation = SMALL_MEDIUM_PADDING) {
        OutlinedTextField(value = value,
            shape = RoundedCornerShape(HIGH_PADDING),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(AppText.search)
                )
            },
            trailingIcon = {
                when (trailingIconState) {
                    true -> {
                        IconButton(onClick = {
                            onValueChange("")
                            onCancel()
                            localFocusManager.clearFocus()
                        }) {
                            Icon(
                                painterResource(AppIcon.outline_cancel_24),
                                null
                            )
                        }
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
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
            ),
            keyboardActions = KeyboardActions(onSearch = {
                localFocusManager.clearFocus()
            }),

            placeholder = {
                Text(
                    text = stringResource(AppText.search),
                    Modifier.alpha(0.6f)
                )
            },
            onValueChange = {
                onValueChange(it)
            })
    }
}

@Composable
fun AppSearchList(
    firstTenStudents: List<Student>, onStudentClick: (Student) -> Unit = {}
) {
    val studentLabel = if (firstTenStudents.size < 2) "student" else "students"
    Column {
        Text(
            text = "${firstTenStudents.size} $studentLabel found",
            Modifier.alpha(0.6f).padding(start = SMALL_MEDIUM_PADDING),
            style = MaterialTheme.typography.bodyMedium
        )
        SurfaceWrapper(tonalElevation = SMALL_MEDIUM_PADDING) {
            LazyColumn(
                modifier = Modifier.padding(MEDIUM_PADDING),
                verticalArrangement = Arrangement.spacedBy(SMALL_PADDING)
            ) {
                items(items = firstTenStudents,
                    key = {
                        it.id
                    }) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onStudentClick(it)
                            },
                        text = it.name,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppSearchBarPreview() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppSearchBar(value = "",
            trailingIconState = false,
            onValueChange = {},
            onCancel = {})
    }
}