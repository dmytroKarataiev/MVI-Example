package com.adammcneilly.tasklist.features.tasks.mvi

import android.os.Parcelable
import com.adammcneilly.tasklist.base.BaseState
import com.adammcneilly.tasklist.data.Task
import kotlinx.android.parcel.Parcelize

sealed class TaskState : BaseState, Parcelable {

    @Parcelize
    class Loading : TaskState()

    @Parcelize
    class Loaded(val tasks: List<Task>) : TaskState()

    @Parcelize
    class Error : TaskState()

}