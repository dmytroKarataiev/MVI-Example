package com.adammcneilly.tasklist.mvi

import com.adammcneilly.tasklist.base.BaseAction
import com.adammcneilly.tasklist.base.Reducer
import com.adammcneilly.tasklist.data.Task
import java.lang.IllegalArgumentException

class PaidReducer : Reducer<PaidState, BaseAction>() {

    override fun reduce(action: BaseAction, state: PaidState): PaidState {
        val newState = when (action) {
            is TaskAction.TasksLoading -> PaidState(TaskState.Loading())
            is TaskAction.TasksLoaded -> PaidState(
                TaskState.Loaded(action.tasks)
            )
            is TaskAction.TaskAdded -> PaidState(
                getStateWithNewTask(action.newTask, state.task)
            )
            is TaskAction.TasksErrored -> PaidState(TaskState.Error())
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