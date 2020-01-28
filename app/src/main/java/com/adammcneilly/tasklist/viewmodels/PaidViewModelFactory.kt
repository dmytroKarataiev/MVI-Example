package com.adammcneilly.tasklist.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adammcneilly.tasklist.base.BaseAction
import com.adammcneilly.tasklist.base.BaseStore
import com.adammcneilly.tasklist.base.Dispatcher
import com.adammcneilly.tasklist.mvi.TaskState
import com.adammcneilly.tasklist.mvi.PaidState
import com.adammcneilly.tasklist.mvi.PaidReducer
import com.adammcneilly.tasklist.mvi.TaskAction
import com.adammcneilly.tasklist.repos.InMemoryTaskService

@Suppress("UNCHECKED_CAST")
object PaidViewModelFactory : ViewModelProvider.Factory {

    private val storeSimple =
        BaseStore(
            PaidState(TaskState.Loading()),
            PaidReducer()
        )

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when {
            modelClass.isAssignableFrom(TaskListViewModel::class.java) -> {
                val repository = InMemoryTaskService()

                return TaskListViewModel(
                    repository,
                    storeSimple as Dispatcher<TaskAction>
                ) as T
            }
            modelClass.isAssignableFrom(PaidStateViewModel::class.java) -> {
                return PaidStateViewModel(
                    storeSimple
                ) as T
            }
            else -> {
                throw IllegalArgumentException("ViewModel Not Found")
            }
        }
    }

}