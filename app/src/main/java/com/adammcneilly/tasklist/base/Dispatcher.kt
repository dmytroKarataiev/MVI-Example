package com.adammcneilly.tasklist.base

/**
 * Dispatcher interface that helps to separate ViewModels from the BaseStore and allows
 * re-use of ViewModels in different combinations
 * (single ViewModel can dispatch to completely different BaseStores).
 */
interface Dispatcher<T : BaseAction> {

    fun dispatch(action: T)

}