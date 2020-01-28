package com.adammcneilly.tasklist.base

abstract class Reducer<S : BaseState, T : BaseAction> {

    abstract fun reduce(action: T, state: S): S

}