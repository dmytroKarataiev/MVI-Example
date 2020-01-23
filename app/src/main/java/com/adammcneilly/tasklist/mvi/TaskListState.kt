package com.adammcneilly.tasklist.mvi

import android.os.Parcelable
import com.adammcneilly.tasklist.base.BaseState
import com.adammcneilly.tasklist.data.Ad
import com.adammcneilly.tasklist.data.Task
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskList(val task: TaskListItemState? = null, val ad: TaskListAdState? = null) :
    BaseState, Parcelable

sealed class TaskListItemState : BaseState, Parcelable {

    @Parcelize
    class Loading : TaskListItemState()

    @Parcelize
    class Loaded(val tasks: List<Task>) : TaskListItemState()

    @Parcelize
    class Error : TaskListItemState()

}

sealed class TaskListAdState : BaseState, Parcelable {

    @Parcelize
    class Loading : TaskListAdState()

    @Parcelize
    class Loaded(val ad: Ad) : TaskListAdState()

    @Parcelize
    class Error : TaskListAdState()

}