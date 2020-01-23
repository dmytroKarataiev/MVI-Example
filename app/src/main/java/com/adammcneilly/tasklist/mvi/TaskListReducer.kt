package com.adammcneilly.tasklist.mvi

import com.adammcneilly.tasklist.base.Reducer
import com.adammcneilly.tasklist.data.Task

class TaskListReducer : Reducer<TaskList, TaskListAction>() {

    override fun reduce(action: TaskListAction, state: TaskList): TaskList {
        val newState = when (action) {
            is TaskListAction.TasksLoading -> TaskList(TaskListItemState.Loading(), state.ad)
            is TaskListAction.TasksLoaded -> TaskList(
                TaskListItemState.Loaded(action.tasks),
                state.ad
            )
            is TaskListAction.TaskAdded -> TaskList(
                getStateWithNewTask(action.newTask, state.task),
                state.ad
            )
            is TaskListAction.TasksErrored -> TaskList(TaskListItemState.Error(), state.ad)
            is TaskListAction.AdLoading -> TaskList(state.task, TaskListAdState.Loading())
            is TaskListAction.AdsLoaded -> TaskList(state.task, TaskListAdState.Loaded(action.ad))
            is TaskListAction.AdErrored -> TaskList(state.task, TaskListAdState.Error())
        }
        return newState
    }

    private fun getStateWithNewTask(
        newTask: Task,
        state: TaskListItemState?
    ): TaskListItemState.Loaded {
        val currentTasks = (state as? TaskListItemState.Loaded)?.tasks.orEmpty()
        return TaskListItemState.Loaded(currentTasks + newTask)
    }

}