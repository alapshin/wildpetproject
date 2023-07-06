package com.alapshin.multiplayground.base

import androidx.compose.runtime.Composable
import app.cash.molecule.RecompositionClock
import app.cash.molecule.launchMolecule
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.StateFlow

abstract class Bloc<Event, State>(
    componentContext: ComponentContext,
) : ComponentContext by componentContext {
    private val scope = retainedCoroutineScope()
    private val events = MutableSharedFlow<Event>(extraBufferCapacity = 16)

    val state: StateFlow<State> by lazy(LazyThreadSafetyMode.NONE) {
        scope.launchMolecule(clock = RecompositionClock.Immediate) {
            state(events)
        }
    }

    fun accept(event: Event) {
        if (!events.tryEmit(event)) {
            error("Event buffer overflow")
        }
    }

    @Composable
    protected abstract fun state(events: Flow<Event>): State

    fun coroutineScope(): CoroutineScope {
        return CoroutineScope(Dispatchers.Main + SupervisorJob())
            .also { scope ->
                lifecycle.doOnDestroy {
                    scope.cancel()
                }
            }
    }

    @Suppress("UnusedPrivateMember")
    fun retainedCoroutineScope(): CoroutineScope {
        return instanceKeeper.getOrCreate { CoroutineScopeKeeper() }.scope
    }

    private class CoroutineScopeKeeper : InstanceKeeper.Instance {
        val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
        override fun onDestroy() {
            scope.cancel()
        }
    }
}
