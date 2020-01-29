package com.adammcneilly.tasklist.features.free.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.tasklist.base.Subscriber
import com.adammcneilly.tasklist.features.free.mvi.FreeState

class FreeStateViewModel(
    subscriber: Subscriber<FreeState>
) : ViewModel() {

    val state = MutableLiveData<FreeState>()

    init {
        subscriber.subscribe {
            state.postValue(it)
        }
    }

    fun init() {
    }

}