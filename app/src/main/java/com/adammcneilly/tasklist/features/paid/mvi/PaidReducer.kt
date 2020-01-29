package com.adammcneilly.tasklist.features.paid.mvi

import com.adammcneilly.tasklist.base.Reducer
import com.adammcneilly.tasklist.data.Task
import com.adammcneilly.tasklist.features.tasks.mvi.TaskAction
import com.adammcneilly.tasklist.features.tasks.mvi.TaskState

class PaidReducer : Reducer<PaidState, TaskAction>() {

    override fun reduce(action: TaskAction, state: PaidState): PaidState {
        val newState = paidState(action, state)
        return newState
    }

    companion object {

        fun paidState(
            action: TaskAction,
            state: PaidState
        ): PaidState {
            return when (action) {
                is TaskAction.TasksLoading -> PaidState(
                    TaskState.Loading()
                )
                is TaskAction.TasksLoaded -> PaidState(
                    TaskState.Loaded(action.tasks)
                )
                is TaskAction.TaskAdded -> PaidState(
                    getStateWithNewTask(action.newTask, state.task)
                )
                is TaskAction.TasksErrored -> PaidState(
                    TaskState.Error()
                )
            }
        }

        fun getStateWithNewTask(
            newTask: Task,
            state: TaskState?
        ): TaskState.Loaded {
            val currentTasks = (state as? TaskState.Loaded)?.tasks.orEmpty()
            return TaskState.Loaded(currentTasks + newTask)
        }

    }

}