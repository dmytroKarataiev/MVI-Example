package com.adammcneilly.tasklist.mvi

import com.adammcneilly.tasklist.base.BaseAction
import com.adammcneilly.tasklist.base.Reducer
import com.adammcneilly.tasklist.data.Task
import java.lang.IllegalArgumentException

class FreeReducer : Reducer<FreeState, BaseAction>() {

    override fun reduce(action: BaseAction, state: FreeState): FreeState {
        val newState = when (action) {
            is AdAction -> {
                when (action) {
                    is AdAction.AdLoading -> FreeState(state.task, AdState.Loading())
                    is AdAction.AdsLoaded -> FreeState(state.task, AdState.Loaded(action.ad))
                    is AdAction.AdErrored -> FreeState(state.task, AdState.Error())
                }
            }
            is TaskAction -> {
                when (action) {
                    is TaskAction.TasksLoading -> FreeState(TaskState.Loading(), state.ad)
                    is TaskAction.TasksLoaded -> FreeState(
                        TaskState.Loaded(action.tasks),
                        state.ad
                    )
                    is TaskAction.TaskAdded -> FreeState(
                        getStateWithNewTask(action.newTask, state.task),
                        state.ad
                    )
                    is TaskAction.TasksErrored -> FreeState(TaskState.Error(), state.ad)
                }
            }
            else -> throw IllegalArgumentException("Impossible combination")
        }
        return newState
    }

    private fun getStateWithNewTask(
        newTask: Task,
        state: TaskState?
    ): TaskState.Loaded {
        val currentTasks = (state as? TaskState.Loaded)?.tasks.orEmpty()
        return TaskState.Loaded(currentTasks + newTask)
    }

}