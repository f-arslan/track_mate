package com.example.track_mate.ui.screens.tablet.screens.setting_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.model.DeleteListItem
import com.example.track_mate.ui.screens.view_models.DeleteInformationViewModel

@Composable
fun DeleteInformationScreen(
    deleteInformationViewModel: DeleteInformationViewModel = hiltViewModel()
) {
    val personals by deleteInformationViewModel.personals.collectAsState()
    val descriptions by deleteInformationViewModel.descriptions.collectAsState()
    val deleteItems = deleteInformationViewModel.deleteItems
    var dialogState by rememberSaveable {
        mutableStateOf(false)
    }

    if (dialogState) {
        StmsDialog(
            updateDialogState = {
                dialogState = it
            },
            onDoneClick = {
                deleteInformationViewModel.deleteFromDatabase()
            }
        )
    }
    if (personals is RequestState.Success && descriptions is RequestState.Success)
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            DetailTopBar(text = stringResource(AppText.delete_information_screen))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(VERY_HIGH_PADDING),
                ) {
                    SectionsButtons(
                        onPersonalClick = {
                            deleteInformationViewModel.updateShowState(ShowState.PERSONAL)
                        },
                        onDescriptionClick = {
                            deleteInformationViewModel.updateShowState(ShowState.DESCRIPTION)
                        }
                    )
                    ResultSection(
                        data = when (deleteInformationViewModel.showState) {
                            ShowState.PERSONAL -> (personals as RequestState.Success<List<Personal>>).data
                            ShowState.DESCRIPTION -> (descriptions as RequestState.Success<List<Description>>).data
                        },
                        onItemClick = {
                            deleteInformationViewModel.addDeleteItem(it)
                        }
                    )
                }
                Divider(
                    androidx.compose.ui.Modifier
                        .height(HIGH_PADDING)
                        .width(NO_PADDING)
                )
                DeleteBox(
                    data = deleteItems,
                    onItemClick = {
                        deleteInformationViewModel.deleteItem(it)
                    }
                )
                Divider(
                    androidx.compose.ui.Modifier
                        .height(HIGH_PADDING)
                        .width(NO_PADDING))
                DeleteUploadButton(
                    onClick = {
                        dialogState = true
                    }
                )
            }
        }
}


@Composable
fun DeleteBox(
    data: List<DeleteListItem>,
    onItemClick: (DeleteListItem) -> Unit
) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .width(526.dp),
        elevation = SMALL_PADDING,
        shape = RoundedCornerShape(SMALL_PADDING),
        backgroundColor = LightestGray
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
        ) {
            items(items = data, key = { it.id }) { item: DeleteListItem ->
                Card(
                    modifier = Modifier
                        .padding(SMALL_PADDING)
                        .clickable {
                            onItemClick(item)
                        },
                    elevation = SMALL_PADDING,
                    backgroundColor = LightRed
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item.name,
                            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.SemiBold),
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ResultSection(
    data: List<DeleteListItem>,
    onItemClick: (DeleteListItem) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(HIGH_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DeleteSectionButton(
            text = AppText.results,
            enabled = false,
            disabledColor = LightYellow
        )
        Card(
            modifier = Modifier
                .height(250.dp)
                .width(250.dp),
            backgroundColor = LightestGray,
            shape = RoundedCornerShape(SMALL_PADDING),
            elevation = SMALL_PADDING,
        ) {
            LazyColumn(
                contentPadding = PaddingValues(MEDIUM_PADDING)
            ) {
                items(items = data, key = {
                    it.id
                }) { item ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick(item) },
                        text = item.name,
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun SectionsButtons(
    onPersonalClick: () -> Unit = {},
    onDescriptionClick: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(HIGH_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeleteSectionButton(
            text = AppText.sections,
            enabled = false,
            disabledColor = LightYellow
        )
        DeleteSectionButton(
            text = AppText.personals,
            onClick = onPersonalClick
        )
        DeleteSectionButton(
            text = AppText.descriptions,
            onClick = onDescriptionClick
        )
    }
}

