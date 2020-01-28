package com.adammcneilly.tasklist.base

interface Dispatcher<T : BaseAction> {

    fun dispatch(action: T)

}