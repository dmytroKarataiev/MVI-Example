package com.adammcneilly.tasklist.features.free.mvi

import com.adammcneilly.tasklist.base.BaseAction
import com.adammcneilly.tasklist.base.Reducer
import com.adammcneilly.tasklist.features.paid.mvi.PaidReducer.Companion.getStateWithNewTask
import com.adammcneilly.tasklist.features.tasks.mvi.TaskAction
import com.adammcneilly.tasklist.features.tasks.mvi.TaskState

class FreeReducer : Reducer<FreeState, BaseAction>() {

    override fun reduce(action: BaseAction, state: FreeState): FreeState {
        val newState = when (action) {
            is AdAction -> freeTaskState(action, state)
            is TaskAction -> freeAdState(action, state)
            else -> throw IllegalArgumentException("Impossible combination")
        }
        return newState
    }

    private fun freeAdState(
        action: TaskAction,
        state: FreeState
    ): FreeState {
        return when (action) {
            is TaskAction.TasksLoading -> FreeState(
                TaskState.Loading(),
                state.ad
            )
            is TaskAction.TasksLoaded -> FreeState(
                TaskState.Loaded(action.tasks),
                state.ad
            )
            is TaskAction.TaskAdded -> FreeState(
                getStateWithNewTask(action.newTask, state.task),
                state.ad
            )
            is TaskAction.TasksErrored -> FreeState(
                TaskState.Error(),
                state.ad
            )
        }
    }

    private fun freeTaskState(
        action: AdAction,
        state: FreeState
    ): FreeState {
        return when (action) {
            is AdAction.AdLoading -> FreeState(
                state.task,
                AdState.Loading()
            )
            is AdAction.AdsLoaded -> FreeState(
                state.task,
                AdState.Loaded(
                    action.ad
                )
            )
            is AdAction.AdErrored -> FreeState(
                state.task,
                AdState.Error()
            )
        }
    }

}