package com.seyone22.shot.presentation.screen.shotscreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ShotUiState(
    val shotsPerEnd: List<Int> = emptyList()
) {
    val totalShots: Int
        get() = shotsPerEnd.sum()

    val ends: Int
        get() = shotsPerEnd.size
}


class ShotScreenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ShotUiState())
    val uiState: StateFlow<ShotUiState> = _uiState.asStateFlow()

    /** Increment arrow shot count in the current end */
    fun incrementArrow() {
        _uiState.value = _uiState.value.copy(
            shotsPerEnd = _uiState.value.shotsPerEnd
                .let { list ->
                    if (list.isEmpty()) listOf(1) // first arrow in the first end
                    else list.dropLast(1) + (list.last() + 1)
                }
        )
    }

    /** Start a new end */
    fun incrementEnd() {
        _uiState.value = _uiState.value.copy(
            shotsPerEnd = _uiState.value.shotsPerEnd + 0 // start new end with 0 shots
        )
    }

    /** Reset all counters */
    fun reset() {
        _uiState.value = ShotUiState()
    }

}
