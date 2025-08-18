package com.seyone22.shot.presentation.screen.shotscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Flag
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material3.Button
import androidx.wear.compose.material3.Icon
import androidx.wear.compose.material3.IconButton
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import androidx.wear.compose.material3.TimeText
import com.seyone22.shot.presentation.AppViewModelProvider

@Composable
fun ShotScreen(viewModel: ShotScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val state by viewModel.uiState.collectAsState()

    val haptic = LocalHapticFeedback.current

    Scaffold(
        timeText = { TimeText() }) {

        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Arrow Count Display
                Text(
                    text = "Shots: ${state.totalShots}",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // End Button
                    IconButton(
                        onClick = { viewModel.incrementEnd() }) {
                        Icon(
                            imageVector = Icons.Default.Flag, contentDescription = "End"
                        )
                    }

                    // Big Main Button
                    Button(
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            viewModel.incrementArrow()
                        },
                        modifier = Modifier.wrapContentSize(align = Alignment.Center),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.ArrowUpward, contentDescription = "Shot"
                        )
                    }

                    // Reset Button
                    IconButton(
                        onClick = { viewModel.reset() }) {
                        Icon(
                            imageVector = Icons.Default.Refresh, contentDescription = "Reset"
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // End Count
                Text(
                    text = "Ends: ${state.ends}", style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}