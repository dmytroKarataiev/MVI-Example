package com.adammcneilly.tasklist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adammcneilly.tasklist.base.BaseStore
import com.adammcneilly.tasklist.mvi.TaskList
import com.adammcneilly.tasklist.mvi.TaskListAction

class StateViewModel(
    store: BaseStore<TaskList, TaskListAction>
) : ViewModel() {

    val state = MutableLiveData<TaskList>()

    init {
        store.subscribe {
            state.postValue(it)
        }
    }

    fun init() {
    }

}