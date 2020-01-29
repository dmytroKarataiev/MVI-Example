package com.adammcneilly.tasklist.base

/**
 * Base class that you need to instantiate with the needed State and Reducer that does
 * the actual reduction of States and emits new States to observers.
 */
class BaseStore<S : BaseState, T : BaseAction>(
    initialState: S,
    private val reducer: Reducer<S, T>
) : Dispatcher<T>, Subscriber<S> {

    private var stateListener: ((S) -> Unit)? = null

    private var currentState: S = initialState
        set(value) {
            field = value
            stateListener?.invoke(value)
        }

    override fun dispatch(action: T) {
        currentState = reducer.reduce(action, currentState)
    }

    override fun subscribe(stateListener: ((S) -> Unit)?) {
        this.stateListener = stateListener
    }

}