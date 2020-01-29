package com.adammcneilly.tasklist.features.tasks.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.tasklist.base.Dispatcher
import com.adammcneilly.tasklist.data.Task
import com.adammcneilly.tasklist.features.tasks.mvi.TaskAction
import com.adammcneilly.tasklist.features.tasks.repos.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class TasksViewModel(
    private val repository: TaskRepository,
    private val dispatcher: Dispatcher<TaskAction>
) : ViewModel() {

    init {
        Log.v(TasksViewModel::class.java.simpleName, "Init called")
        fetchTasks()
    }

    fun addButtonClicked() = background {
        delay(1000)
        val taskNumber = Random.nextInt(0, 100)
        val newTask = Task(description = "Random Task $taskNumber")
        dispatcher.dispatch(TaskAction.TaskAdded(newTask))
    }

    private fun fetchTasks() = background {
        dispatcher.dispatch(TaskAction.TasksLoading)
        try {
            delay(500)
            val tasks = repository.getTasks()
            dispatcher.dispatch(TaskAction.TasksLoaded(tasks))

        } catch (e: Throwable) {
            dispatcher.dispatch(TaskAction.TasksErrored(e))
        }
    }

    fun init() {

    }

}

private fun ViewModel.background(function: suspend CoroutineScope.() -> Unit) {
    viewModelScope.launch(Dispatchers.IO) {
        function.invoke(this)
    }
}
