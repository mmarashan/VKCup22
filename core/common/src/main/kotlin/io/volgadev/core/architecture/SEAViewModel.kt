package io.volgadev.core.architecture

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

/**
 * Base class for a view model that implements the MVI pattern + actions
 * @param S the base class for the screen state; it is recommended to use sealed-class or data-class if the screen is simple
 * @param E base class for external screen event (user click, getting permission); it is recommended to use sealed class or enum
 * @param A base class for one-time actions that need to be played (show popup, call system picker); it is recommended to use sealed class or enum
 */
abstract class SEAViewModel<S, E, A> : ViewModel() {

    private val stateFlow =
        MutableSharedFlow<S>(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    private val actionFlow = MutableSharedFlow<A>(
        replay = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        extraBufferCapacity = 1
    )

    /**
     * Current screen state
     */
    val state: SharedFlow<S> = stateFlow

    /**
     * One-time action to perform
     */
    val action: SharedFlow<A> = actionFlow

    /**
     * Callback to the [event] event. Must be called from UI
     */
    abstract fun onEvent(event: E)

    /**
     * Sets screen state [state] asynchronously
     */
    protected open fun setState(state: S) = stateFlow.tryEmit(state)

    /**
     * Sets the screen state [state] synchronously, suspending the current thread
     */
    protected open suspend fun setStateSync(state: S) = stateFlow.emit(state)

    /**
     * Returns the current value of [state] or null
     */
    protected fun getStateValueOrNull() = state.replayCache.firstOrNull()

    /**
     * Raises the [action] event
     */
    protected fun emitAction(action: A) {
        actionFlow.tryEmit(action)
    }
}
