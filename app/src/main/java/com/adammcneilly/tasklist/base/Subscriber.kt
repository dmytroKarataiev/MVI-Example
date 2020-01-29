package com.adammcneilly.tasklist.base

/**
 * Interface that listens to State changes.
 */
interface Subscriber<T : BaseState> {

    fun subscribe(stateListener: ((T) -> Unit)?)

}