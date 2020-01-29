package com.adammcneilly.tasklist.features.free.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.tasklist.base.Dispatcher
import com.adammcneilly.tasklist.features.free.mvi.AdAction
import com.adammcneilly.tasklist.features.free.repos.AdsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddsViewModel(
    private val repository: AdsRepository,
    private val dispatcher: Dispatcher<AdAction>
) : ViewModel() {

    init {
        Log.v(AddsViewModel::class.java.simpleName, "Init called")
        fetchAds()
    }

    private fun fetchAds() {
        dispatcher.dispatch(AdAction.AdLoading)

        try {
            viewModelScope.launch(Dispatchers.IO) {
                delay(500)
                val ad = repository.getAd()
                dispatcher.dispatch(
                    AdAction.AdsLoaded(
                        ad
                    )
                )
            }
        } catch (e: Throwable) {
            dispatcher.dispatch(
                AdAction.AdErrored(
                    e
                )
            )
        }
    }

    fun init() {

    }

}