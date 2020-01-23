package com.adammcneilly.tasklist.base

interface BaseAction

interface BaseState

abstract class Reducer<S : BaseState, T : BaseAction> {

    abstract fun reduce(action: T, state: S): S

}

class BaseStore<S : BaseState, T : BaseAction>(
    initialState: S,
    private val reducer: Reducer<S, T>
) {

    private var stateListener: ((S) -> Unit)? = null

    private var currentState: S = initialState
        set(value) {
            field = value
            stateListener?.invoke(value)
        }

    fun dispatch(action: T) {
        currentState = reducer.reduce(action, currentState)
    }

    fun subscribe(stateListener: ((S) -> Unit)?) {
        this.stateListener = stateListener
    }

}