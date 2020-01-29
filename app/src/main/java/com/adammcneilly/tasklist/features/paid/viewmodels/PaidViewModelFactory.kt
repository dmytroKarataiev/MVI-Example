package com.adammcneilly.tasklist.features.paid.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adammcneilly.tasklist.base.BaseStore
import com.adammcneilly.tasklist.features.paid.mvi.PaidReducer
import com.adammcneilly.tasklist.features.paid.mvi.PaidState
import com.adammcneilly.tasklist.features.tasks.mvi.TaskState
import com.adammcneilly.tasklist.features.tasks.mvi.TaskAction
import com.adammcneilly.tasklist.features.tasks.repos.InMemoryTaskService
import com.adammcneilly.tasklist.features.tasks.viewmodels.TasksViewModel

@Suppress("UNCHECKED_CAST")
object PaidViewModelFactory : ViewModelProvider.Factory {

    private val storeSimple: BaseStore<PaidState, TaskAction> =
        BaseStore(
            PaidState(TaskState.Loading()),
            PaidReducer()
        )

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        when {
            modelClass.isAssignableFrom(TasksViewModel::class.java) -> {
                val repository =
                    InMemoryTaskService()

                return TasksViewModel(
                    repository,
                    storeSimple
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