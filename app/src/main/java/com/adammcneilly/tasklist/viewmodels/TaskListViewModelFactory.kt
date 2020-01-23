package com.adammcneilly.tasklist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adammcneilly.tasklist.base.BaseStore
import com.adammcneilly.tasklist.mvi.TaskList
import com.adammcneilly.tasklist.mvi.TaskListAction
import com.adammcneilly.tasklist.mvi.TaskListAdState
import com.adammcneilly.tasklist.mvi.TaskListItemState
import com.adammcneilly.tasklist.mvi.TaskListReducer
import com.adammcneilly.tasklist.repos.InMemoryAdsService
import com.adammcneilly.tasklist.repos.InMemoryTaskService

@Suppress("UNCHECKED_CAST")
object TaskListViewModelFactory : ViewModelProvider.Factory {

    private val store: BaseStore<TaskList, TaskListAction> =
        BaseStore(
            TaskList(TaskListItemState.Loading(), TaskListAdState.Loading()),
            TaskListReducer()
        )

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when {
            modelClass.isAssignableFrom(TaskListViewModel::class.java) -> {
                val repository = InMemoryTaskService()

                return TaskListViewModel(
                    repository,
                    store
                ) as T
            }
            modelClass.isAssignableFrom(AddsViewModel::class.java) -> {
                val repo = InMemoryAdsService()
                return AddsViewModel(
                    repo, store
                ) as T
            }
            modelClass.isAssignableFrom(StateViewModel::class.java) -> {
                return StateViewModel(
                    store
                ) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }

}