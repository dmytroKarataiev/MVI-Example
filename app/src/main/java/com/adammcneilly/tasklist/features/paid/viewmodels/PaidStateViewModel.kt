package com.adammcneilly.tasklist.features.paid.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.tasklist.base.Subscriber
import com.adammcneilly.tasklist.features.paid.mvi.PaidState

class PaidStateViewModel(
    subscriber: Subscriber<PaidState>
) : ViewModel() {

    val state = MutableLiveData<PaidState>()

    init {
        subscriber.subscribe {
            state.postValue(it)
        }
    }

    fun init() {
    }

}