package com.adammcneilly.tasklist.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adammcneilly.tasklist.base.BaseStore
import com.adammcneilly.tasklist.data.Task
import com.adammcneilly.tasklist.mvi.TaskList
import com.adammcneilly.tasklist.mvi.TaskListAction
import com.adammcneilly.tasklist.repos.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class TaskListViewModel(
    private val repository: TaskRepository,
    val store: BaseStore<TaskList, TaskListAction>
) : ViewModel() {

    init {
        Log.v(TaskListViewModel::class.java.simpleName, "Init called")
        fetchTasks()
    }

    fun addButtonClicked() = background {
        delay(1000)
        val taskNumber = Random.nextInt(0, 100)
        val newTask = Task(description = "Random Task $taskNumber")
        store.dispatch(TaskListAction.TaskAdded(newTask))
    }

    private fun fetchTasks() = background {
        store.dispatch(TaskListAction.TasksLoading)
        try {
            delay(500)
            val tasks = repository.getTasks()
            store.dispatch(TaskListAction.TasksLoaded(tasks))

        } catch (e: Throwable) {
            store.dispatch(TaskListAction.TasksErrored(e))
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
