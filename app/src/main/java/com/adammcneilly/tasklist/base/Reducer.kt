package com.adammcneilly.tasklist.base

/**
 * Abstract class that you inherit and implement logic to emit a single consistent state.
 * Probably needs support for synchronization.
 */
abstract class Reducer<S : BaseState, T : BaseAction> {

    abstract fun reduce(action: T, state: S): S

}