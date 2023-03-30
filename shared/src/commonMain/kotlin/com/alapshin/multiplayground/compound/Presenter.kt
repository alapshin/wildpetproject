package com.alapshin.multiplayground.compound

import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.Flow

fun interface Presenter<Event, State> {
    @Composable
    fun state(events: Flow<Event>): State
}
