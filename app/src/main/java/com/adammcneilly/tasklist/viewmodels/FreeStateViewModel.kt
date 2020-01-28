package com.adammcneilly.tasklist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.tasklist.base.BaseAction
import com.adammcneilly.tasklist.base.BaseStore
import com.adammcneilly.tasklist.mvi.FreeState
import com.adammcneilly.tasklist.mvi.AdAction

class FreeStateViewModel(
    store: BaseStore<FreeState, BaseAction>
) : ViewModel() {

    val state = MutableLiveData<FreeState>()

    init {
        store.subscribe {
            state.postValue(it)
        }
    }

    fun init() {
    }

}