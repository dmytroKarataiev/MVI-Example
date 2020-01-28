package com.adammcneilly.tasklist.base

class BaseStore<S : BaseState, T : BaseAction>(
    initialState: S,
    private val reducer: Reducer<S, T>
) : Dispatcher<T> {

    private var stateListener: ((S) -> Unit)? = null

    private var currentState: S = initialState
        set(value) {
            field = value
            stateListener?.invoke(value)
        }

    override fun dispatch(action: T) {
        currentState = reducer.reduce(action, currentState)
    }

    fun subscribe(stateListener: ((S) -> Unit)?) {
        this.stateListener = stateListener
    }

}