package com.adammcneilly.tasklist.mvi

import com.adammcneilly.tasklist.base.BaseAction
import com.adammcneilly.tasklist.data.Ad
import com.adammcneilly.tasklist.data.Task

sealed class TaskListAction : BaseAction {

    object TasksLoading : TaskListAction()
    class TasksLoaded(val tasks: List<Task>) : TaskListAction()
    class TaskAdded(val newTask: Task) : TaskListAction()
    class TasksErrored(val error: Throwable) : TaskListAction()

    object AdLoading : TaskListAction()
    class AdsLoaded(val ad: Ad) : TaskListAction()
    class AdErrored(e: Throwable) : TaskListAction()

}