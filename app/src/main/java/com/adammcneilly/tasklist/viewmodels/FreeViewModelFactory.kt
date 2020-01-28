package com.adammcneilly.tasklist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adammcneilly.tasklist.base.BaseAction
import com.adammcneilly.tasklist.base.BaseStore
import com.adammcneilly.tasklist.base.Dispatcher
import com.adammcneilly.tasklist.mvi.AdAction
import com.adammcneilly.tasklist.mvi.FreeReducer
import com.adammcneilly.tasklist.mvi.FreeState
import com.adammcneilly.tasklist.mvi.TaskAction
import com.adammcneilly.tasklist.mvi.AdState
import com.adammcneilly.tasklist.mvi.TaskState
import com.adammcneilly.tasklist.repos.InMemoryAdsService
import com.adammcneilly.tasklist.repos.InMemoryTaskService

@Suppress("UNCHECKED_CAST")
object FreeViewModelFactory : ViewModelProvider.Factory {

    private val store: BaseStore<FreeState, BaseAction> =
        BaseStore(
            FreeState(TaskState.Loading(), AdState.Loading()),
            FreeReducer()
        )

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when {
            modelClass.isAssignableFrom(TaskListViewModel::class.java) -> {
                val repository = InMemoryTaskService()

                return TaskListViewModel(
                    repository,
                    store as Dispatcher<TaskAction>
                ) as T
            }
            modelClass.isAssignableFrom(AddsViewModel::class.java) -> {
                val repo = InMemoryAdsService()
                return AddsViewModel(
                    repo, store as Dispatcher<AdAction>
                ) as T
            }
            modelClass.isAssignableFrom(FreeStateViewModel::class.java) -> {
                return FreeStateViewModel(
                    store
                ) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }

}