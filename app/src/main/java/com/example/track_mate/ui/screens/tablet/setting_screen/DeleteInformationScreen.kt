package com.example.track_mate.ui.screens.tablet.setting_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.AppDialog
import com.example.track_mate.common.composables.AppTopBar
import com.example.track_mate.common.composables.DeleteSectionButton
import com.example.track_mate.common.composables.DeleteUploadButton
import com.example.track_mate.core.model.DeleteListItem
import com.example.track_mate.core.model.Description
import com.example.track_mate.core.model.Personal
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.ui.screens.view_models.DeleteInformationViewModel
import com.example.track_mate.ui.screens.view_models.ShowState
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.MEDIUM_PADDING
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.util.Constants.SMALL_PADDING
import com.example.track_mate.util.Constants.VERY_HIGH_PADDING
import com.example.track_mate.R.string as AppText

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
        AppDialog(updateDialogState = {
            dialogState = it
        },
            onDoneClick = {
                deleteInformationViewModel.deleteFromDatabase()
            })
    }
    if (personals is RequestState.Success && descriptions is RequestState.Success) Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        AppTopBar(text = stringResource(AppText.delete_information_screen))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(VERY_HIGH_PADDING),
            ) {
                SectionsButtons(onPersonalClick = {
                    deleteInformationViewModel.updateShowState(ShowState.PERSONAL)
                },
                    onDescriptionClick = {
                        deleteInformationViewModel.updateShowState(ShowState.DESCRIPTION)
                    })
                ResultSection(data = when (deleteInformationViewModel.showState) {
                    ShowState.PERSONAL -> (personals as RequestState.Success<List<Personal>>).data
                    ShowState.DESCRIPTION -> (descriptions as RequestState.Success<List<Description>>).data
                },
                    onItemClick = {
                        deleteInformationViewModel.addDeleteItem(it)
                    })
            }
            Divider(
                Modifier
                    .height(HIGH_PADDING)
                    .width(NO_PADDING)
            )
            DeleteBox(data = deleteItems,
                onItemClick = {
                    deleteInformationViewModel.deleteItem(it)
                })
            Divider(
                Modifier
                    .height(HIGH_PADDING)
                    .width(NO_PADDING)
            )
            DeleteUploadButton(onClick = {
                dialogState = true
            })
        }
    }
}


@Composable
fun DeleteBox(
    data: List<DeleteListItem>, onItemClick: (DeleteListItem) -> Unit
) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .width(526.dp),
        shape = RoundedCornerShape(SMALL_PADDING),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
        ) {
            items(
                items = data,
                key = { it.id }) { item: DeleteListItem ->
                Card(modifier = Modifier
                    .padding(SMALL_PADDING)
                    .clickable {
                        onItemClick(item)
                    }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = item.name,
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ResultSection(
    data: List<DeleteListItem>, onItemClick: (DeleteListItem) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(HIGH_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        DeleteSectionButton(
            text = AppText.results,
            enabled = false,
        )
        Card(
            modifier = Modifier
                .height(250.dp)
                .width(250.dp),
            shape = RoundedCornerShape(SMALL_PADDING),
        ) {
            LazyColumn(
                contentPadding = PaddingValues(MEDIUM_PADDING)
            ) {
                items(
                    items = data,
                    key = {
                        it.id
                    }) { item ->
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onItemClick(item) },
                        text = item.name,
                    )
                }
            }
        }
    }
}

@Composable
fun SectionsButtons(
    onPersonalClick: () -> Unit = {}, onDescriptionClick: () -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(HIGH_PADDING),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DeleteSectionButton(
            text = AppText.sections,
            enabled = false,
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

