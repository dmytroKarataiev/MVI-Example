package com.adammcneilly.tasklist.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.tasklist.base.BaseStore
import com.adammcneilly.tasklist.mvi.TaskList
import com.adammcneilly.tasklist.mvi.TaskListAction
import com.adammcneilly.tasklist.repos.AdsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddsViewModel(
    private val repository: AdsRepository,
    private val store: BaseStore<TaskList, TaskListAction>
) : ViewModel() {

    init {
        Log.v(AddsViewModel::class.java.simpleName, "Init called")
        fetchAds()
    }

    private fun fetchAds() {
        store.dispatch(TaskListAction.AdLoading)

        try {
            viewModelScope.launch(Dispatchers.IO) {
                delay(500)
                val ad = repository.getAd()
                store.dispatch(TaskListAction.AdsLoaded(ad))
            }
        } catch (e: Throwable) {
            store.dispatch(TaskListAction.AdErrored(e))
        }
    }

    fun init() {

    }

}