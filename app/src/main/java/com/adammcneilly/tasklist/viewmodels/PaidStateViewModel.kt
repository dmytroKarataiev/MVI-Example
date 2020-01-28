package com.adammcneilly.tasklist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.tasklist.base.BaseAction
import com.adammcneilly.tasklist.base.BaseStore
import com.adammcneilly.tasklist.mvi.PaidState
import com.adammcneilly.tasklist.mvi.TaskAction

class PaidStateViewModel(
    store: BaseStore<PaidState, BaseAction>
) : ViewModel() {

    val state = MutableLiveData<PaidState>()

    init {
        store.subscribe {
            state.postValue(it)
        }
    }

    fun init() {
    }

}