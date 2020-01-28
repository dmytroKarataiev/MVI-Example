package com.adammcneilly.tasklist.mvi

import com.adammcneilly.tasklist.base.BaseAction
import com.adammcneilly.tasklist.data.Task

sealed class TaskAction : BaseAction {

    object TasksLoading : TaskAction()
    class TasksLoaded(val tasks: List<Task>) : TaskAction()
    class TaskAdded(val newTask: Task) : TaskAction()
    class TasksErrored(val error: Throwable) : TaskAction()

}