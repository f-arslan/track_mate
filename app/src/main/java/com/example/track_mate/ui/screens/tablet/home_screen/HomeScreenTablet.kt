package com.example.track_mate.ui.screens.tablet.home_screen

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.track_mate.common.composables.ActionGrid
import com.example.track_mate.common.composables.EmptyScreen
import com.example.track_mate.common.composables.TrackMateTopAppBar
import com.example.track_mate.model.Description
import com.example.track_mate.ui.screens.RequestState
import com.example.track_mate.ui.screens.view_models.HomeScreenViewModelApp
import com.example.track_mate.util.Constants.HIGH_PADDING
import com.example.track_mate.util.Constants.HOME
import com.example.track_mate.util.Constants.NO_PADDING
import com.example.track_mate.R.drawable as AppIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenTablet(
    homeScreenViewModel: HomeScreenViewModelApp = hiltViewModel()
) {
    val context = LocalContext.current
    val allActionsByToday by homeScreenViewModel.allActionsByToday.collectAsState()

    Scaffold(topBar = {
        StmsHeaderSection(onSaveClick = { homeScreenViewModel.writeToFileAsCsv(context) })
        TrackMateTopAppBar(
            titleRes = ,
            navigationIcon = ,
            navigationIconContentDescription = ,
            actionIcon = ,
            actionIconContentDescription = 
        )
    }) { values ->
        Column(modifier = Modifier.padding(values)) {
            when (val result = allActionsByToday) {
                is RequestState.Success -> {
                    ActionGrid(
                        data = result.data,
                        finishActionClick = homeScreenViewModel::finishAction,
                        deleteActionClick = homeScreenViewModel::deleteAction
                    )
                }

                else -> {
                    EmptyScreen()
                }
            }
        }
    }
}



@Composable
fun StmsHeaderSection(onSaveClick: () -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = HIGH_PADDING,
                    end = HIGH_PADDING
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(
                Modifier
                    .width(NO_PADDING)
                    .height(NO_PADDING)
            )
            Text(
                text = HOME,
                style = MaterialTheme.typography.titleLarge
            )
            HeaderButtonSection(onSaveClick = onSaveClick)
        }
    }
}

@Composable
fun HeaderButtonSection(onSaveClick: () -> Unit) {
    Row {
        IconButton(onClick = onSaveClick) {
            Icon(
                painter = painterResource(AppIcon.baseline_save_alt_24),
                contentDescription = null
            )
        }
    }
}